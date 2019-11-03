package com.osce.service.user.login;

import com.osce.api.user.login.PfUserService;
import com.osce.dto.common.PfCommonListDto;
import com.osce.dto.user.PfUserDto;
import com.osce.dto.user.login.RegisterDto;
import com.osce.dto.user.login.UpdatePswDto;
import com.osce.entity.UserInfo;
import com.osce.exception.RestErrorCode;
import com.osce.exception.RestException;
import com.osce.orm.user.login.PfUserDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.vo.user.login.PfStudentVo;
import com.osce.vo.user.login.PfUsersVo;
import com.sm.open.care.core.ErrorCode;
import com.sm.open.care.core.ErrorMessage;
import com.sm.open.care.core.exception.BizRuntimeException;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component("pfUserService")
@Service
public class PfUserServiceImpl implements PfUserService {

    @Resource
    private PfUserDao pfUserDao;

    @Override
    public PageResult listUsers(PfUserDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfUserDao.countUsers(dto),
                pfUserDao.listUsers(dto));
    }

    @Override
    public Long countUsers(PfUserDto dto) {
        return pfUserDao.countUsers(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveUser(RegisterDto dto) {
        // 校验用户名是否已经注册
        if (pfUserDao.isExistUser(dto.getUsername())) {
            throw new RestException(RestErrorCode.USER_NAME_USED);
        }
        // 1.同一个手机号、身份证号，可以创建多个账号
        // 2.每个账号只能设置一种角色
        if (dto.isCheckFlag()) {
            if (pfUserDao.isExistUserByCondition(dto.getPhoneNo(), dto.getIdcard(), dto.getRoles().get(0), null)) {
                throw new RestException(RestErrorCode.USER_PHONE_CARD_ROLE_USED);
            }
        }

        UserInfo user = new UserInfo();
        BeanUtils.copyProperties(dto, user);
        // 密码加密
        user.setPassword(genEncriptPwd(dto.getPassword()));
        // 新增用户
        pfUserDao.saveUser(user);
        // 插入用户角色
        pfUserDao.saveUserRole(dto.getRoles(), user.getUserId());
        return user.getUserId();
    }

    @Override
    public boolean isExistUser(String userName) {
        return pfUserDao.isExistUser(userName);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(RegisterDto dto) {
        // 1.同一个手机号、身份证号，可以创建多个账号
        // 2.每个账号只能设置一种角色
        if (dto.isCheckFlag()) {
            if (pfUserDao.isExistUserByCondition(dto.getPhoneNo(), dto.getIdcard(), dto.getRoles().get(0), dto.getUsername())) {
                throw new RestException(RestErrorCode.USER_PHONE_CARD_ROLE_USED);
            }
        }
        UserInfo user = new UserInfo();
        BeanUtils.copyProperties(dto, user);
        // 修改用户信息
        pfUserDao.updateUser(user);
        // 删除用户角色
        pfUserDao.delUserRole(dto.getUserId());
        // 插入用户角色
        pfUserDao.saveUserRole(dto.getRoles(), dto.getUserId());
        return true;
    }

    @Override
    public boolean delUser(PfCommonListDto dto) {
        if (!dto.isPlatOrSuper()) {
            // 校验用户机构
            List<Long> orgList = pfUserDao.selectOrgId(dto.getList()).stream()
                    .filter(idOrg -> !idOrg.equals(dto.getCurrentUserOrgId())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(orgList)) {
                throw new RestException(ErrorCode.USER_AUTH_EXCEPTION, ErrorMessage.USER_AUTH_EXCEPTION_MSG);
            }
        }
        return pfUserDao.delUser(dto.getList()) >= 1 ? true : false;
    }

    @Override
    public boolean freezeUser(PfCommonListDto dto) {
        if (!dto.isPlatOrSuper()) {
            // 校验用户机构
            List<Long> orgList = pfUserDao.selectOrgId(dto.getList()).stream()
                    .filter(idOrg -> !idOrg.equals(dto.getCurrentUserOrgId())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(orgList)) {
                throw new RestException(ErrorCode.USER_AUTH_EXCEPTION, ErrorMessage.USER_AUTH_EXCEPTION_MSG);
            }
        }
        return pfUserDao.freezeUser(dto.getList()) >= 1 ? true : false;
    }

    @Override
    public UserInfo selectUser(String userName) {
        return pfUserDao.selectUser(userName);
    }

    @Override
    public UserInfo selectUserById(Long userId) {
        return pfUserDao.selectUserById(userId);
    }

    @Override
    public boolean updatePsw(UpdatePswDto dto) {
        // 密码加密
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(dto.getUserId());
        userInfo.setPassword(genEncriptPwd(dto.getNewPassword()));
        return pfUserDao.updatePsw(userInfo) == 1 ? true : false;
    }

    @Override
    public boolean matchPassword(String rawPwd, String encriptPwd) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPwd, encriptPwd);
    }

    @Override
    public PfStudentVo selectStudentInfo(Long idStudent) {
        return pfUserDao.selectStudentInfo(idStudent);
    }

    @Override
    public String genEncriptPwd(String rawPwd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPwd);
        return encodedPassword;
    }

}
