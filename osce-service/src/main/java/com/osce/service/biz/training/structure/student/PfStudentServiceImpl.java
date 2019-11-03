package com.osce.service.biz.training.structure.student;

import com.osce.api.biz.training.structure.student.PfStudentService;
import com.osce.dto.biz.training.structure.student.StudentDepartDto;
import com.osce.dto.biz.training.structure.student.StudentDto;
import com.osce.dto.biz.training.structure.student.StudentMoveDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.dto.user.login.RegisterDto;
import com.osce.enums.PfRoleEnum;
import com.osce.orm.biz.training.structure.student.PfStudentDao;
import com.osce.orm.user.role.PfRoleDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.service.user.login.PfUserServiceImpl;
import com.osce.vo.biz.training.structure.student.StudentDepartVo;
import com.osce.vo.biz.training.structure.student.StudentVo;
import com.osce.vo.user.role.PfRoleVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PfStudentServiceImpl
 * @Description: 学员管理实现
 * @Author yangtongbin
 * @Date 2019-05-07
 */
@Service
public class PfStudentServiceImpl implements PfStudentService {

    @Resource
    private PfUserServiceImpl pfUserService;

    @Resource
    private PfRoleDao pfRoleDao;

    @Resource
    private PfStudentDao pfStudentDao;


    @Override
    public PageResult pageStudents(StudentDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfStudentDao.countStudent(dto),
                pfStudentDao.listStudents(dto));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public StudentDepartVo addStudent(StudentDepartDto dto) {
        StudentDepartVo studentDepartVo = new StudentDepartVo();
        RegisterDto registerDto = dto.getRegisterInfo();
        registerDto.setEnabled(true);
        if (registerDto != null) {
            List<Long> roles = new ArrayList<>(1);
            // 默认角色
            PfRoleVo pfRoleVo = pfRoleDao.selectRoleInfoByCode(PfRoleEnum.MCST.getCode());
            roles.add(pfRoleVo != null ? pfRoleVo.getRoleId() : null);
            registerDto.setRoles(roles);
        }
        registerDto.setCheckFlag(false);
        if (dto.getIdStudentDepart() == null) {
            // 密码为空，默认身份证后4位
            if (StringUtils.isBlank(registerDto.getPassword()) && StringUtils.isNotBlank(registerDto.getIdcard())) {
                registerDto.setPassword(StringUtils.rightPad(registerDto.getIdcard(), 6));
            }
            registerDto.setUsername(registerDto.getPhoneNo());
            registerDto.setRealName(registerDto.getRealName().trim());
            Long usId = pfUserService.saveUser(registerDto);
            studentDepartVo.setIdUser(usId);
            // 增加学员部门关系
            dto.setIdUser(usId);
            pfStudentDao.addStudent(dto);
            studentDepartVo.setIdStudentDepart(dto.getIdStudentDepart());
        } else {
            pfUserService.updateUser(registerDto);
            pfStudentDao.editStudent(dto);
        }
        return studentDepartVo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delStudent(PfBachChangeStatusDto dto) {
        pfStudentDao.delStudent(dto);
        pfStudentDao.delUser(dto);
        return true;
    }

    @Override
    public List<StudentVo> listStudentByIdGrade(StudentDto dto) {
        return pfStudentDao.listStudentByIdGrade(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean moveStudent(StudentMoveDto dto) {
        List<Long> idDeparts = dto.getIdDeparts();
        List<Long> idUsers = dto.getIdUsers();
        for (Long idDepart : idDeparts) {
            for (Long idUser : idUsers) {
                if (pfStudentDao.isExistDeptStu(idDepart, idUser)) {
                    continue;
                }
                pfStudentDao.moveStudent(idDepart, idUser);
            }
        }
        return true;
    }

}
