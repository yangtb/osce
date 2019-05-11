package com.osce.service.biz.training.structure.student;

import com.osce.api.biz.training.structure.student.PfStudentService;
import com.osce.dto.biz.training.structure.student.StudentDepartDto;
import com.osce.dto.biz.training.structure.student.StudentDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.dto.user.login.RegisterDto;
import com.osce.orm.biz.training.structure.student.PfStudentDao;
import com.osce.orm.user.role.PfRoleDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.service.user.login.PfUserServiceImpl;
import com.osce.vo.biz.training.structure.student.StudentDepartVo;
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
        if (registerDto != null) {
            List<Long> roles = new ArrayList<>(1);
            // 默认角色
            roles.add(1L);
            registerDto.setRoles(roles);
        }
        if (dto.getIdStudentDepart() == null) {
            Long usId = pfUserService.saveUser(registerDto);
            studentDepartVo.setIdUser(usId);
            // 增加学员部门关系
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

}
