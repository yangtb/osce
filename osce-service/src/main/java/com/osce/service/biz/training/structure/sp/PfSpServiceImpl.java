package com.osce.service.biz.training.structure.sp;

import com.osce.api.biz.training.structure.sp.PfSpService;
import com.osce.dto.biz.training.structure.sp.SpDto;
import com.osce.dto.biz.training.structure.sp.UserSpDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.dto.user.login.RegisterDto;
import com.osce.entity.OrgSpDefine;
import com.osce.entity.OrgUserSp;
import com.osce.enums.PfRoleEnum;
import com.osce.orm.biz.training.structure.sp.PfSpDao;
import com.osce.orm.user.login.PfUserDao;
import com.osce.orm.user.role.PfRoleDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.service.user.login.PfUserServiceImpl;
import com.osce.vo.biz.training.structure.sp.SpVo;
import com.osce.vo.user.role.PfRoleVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PfSpServiceImpl
 * @Description: Sp接口实现
 * @Author yangtongbin
 * @Date 2019-05-10
 */
@Service
public class PfSpServiceImpl implements PfSpService {

    @Resource
    private PfSpDao pfSpDao;

    @Resource
    private PfRoleDao pfRoleDao;

    @Resource
    private PfUserDao pfUserDao;

    @Resource
    private PfUserServiceImpl pfUserService;

    @Override
    public SpVo selectSp(SpDto dto) {
        return pfSpDao.selectSp(dto);
    }

    @Override
    public PageResult pageSp(SpDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfSpDao.countSp(dto),
                pfSpDao.listSp(dto));
    }

    @Override
    public PageResult pageSpCache(SpDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfSpDao.countSpCache(dto),
                pfSpDao.listSpCache(dto));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long addSp(UserSpDto dto) {
        RegisterDto registerDto = dto.getRegisterInfo();
        registerDto.setEnabled(true);
        if (registerDto != null) {
            List<Long> roles = new ArrayList<>(1);
            // 默认角色
            PfRoleVo pfRoleVo = pfRoleDao.selectRoleInfoByCode(PfRoleEnum.SP.getCode());
            roles.add(pfRoleVo != null ? pfRoleVo.getRoleId() : null);
            registerDto.setRoles(roles);
        }
        Long usId;
        List<OrgUserSp> userSpList = dto.getUserSpList();
        if (registerDto.getUserId() == null) {
            // 密码为空，默认身份证后4位
            if (StringUtils.isBlank(registerDto.getPassword()) && StringUtils.isNotBlank(registerDto.getIdcard())) {
                registerDto.setPassword(StringUtils.rightPad(registerDto.getIdcard(), 6));
            }
            registerDto.setUsername(registerDto.getPhoneNo());
            usId = pfUserService.saveUser(registerDto);
        } else {
            usId = registerDto.getUserId();
            pfUserService.updateUser(registerDto);
            pfSpDao.delSpByusId(usId);
        }
        // SP标签记录
        if (!CollectionUtils.isEmpty(userSpList)) {
            pfSpDao.addSp(userSpList, usId);
        }
        return registerDto.getUserId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delSp(PfBachChangeStatusDto dto) {
        pfUserDao.delUser(dto.getList());
        pfSpDao.delSp(dto);
        return true;
    }

    @Override
    public List<Map<Long, String>> listSpTagValue(Long userId) {
        return pfSpDao.listSpTagValue(userId);
    }

    @Override
    public List<OrgSpDefine> listSpTag(Long idOrg) {
        return pfSpDao.listSpTag(idOrg);
    }

    @Override
    public Long addSpTag(OrgSpDefine dto) {
        return pfSpDao.addSpTag(dto) == 1 ? dto.getIdSpTag() : null;
    }

    @Override
    public boolean delSpTag(PfBachChangeStatusDto dto) {
        return pfSpDao.delSpTag(dto) >= 1 ? true : false;
    }

}
