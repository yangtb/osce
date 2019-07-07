package com.osce.api.user.login;

import com.osce.dto.common.PfCommonListDto;
import com.osce.dto.user.PfUserDto;
import com.osce.dto.user.login.RegisterDto;
import com.osce.dto.user.login.UpdatePswDto;
import com.osce.entity.UserInfo;
import com.osce.result.PageResult;
import com.osce.vo.user.login.PfStudentVo;
import com.osce.vo.user.login.PfUsersVo;

import java.util.List;

/**
 * @ClassName: PfUserService
 * @Description: 用户相关接口
 * @Author yangtongbin
 * @Date 2017/10/11 11:11
 */
public interface PfUserService {

    /**
     * 获取用户列表
     *
     * @param dto
     * @return
     */
    PageResult listUsers(PfUserDto dto);

    /**
     * 用户总数
     *
     * @param dto
     * @return
     */
    Long countUsers(PfUserDto dto);

    /**
     * 保存用户信息
     *
     * @param dto
     * @return
     */
    Long saveUser(RegisterDto dto);


    /**
     * 判断用户名是否存在
     *
     * @param userName
     * @return
     */
    boolean isExistUser(String userName);

    /**
     * 修改用户信息
     *
     * @param dto
     * @return
     */
    boolean updateUser(RegisterDto dto);

    /**
     * 删除用户
     *
     * @param dto 用户id集合
     * @return
     */
    boolean delUser(PfCommonListDto dto);

    /**
     * 删除用户
     *
     * @param dto 用户id集合
     * @return
     */
    boolean freezeUser(PfCommonListDto dto);

    /**
     * 根据用户获取用户信息
     *
     * @param userName
     * @return
     */
    UserInfo selectUser(String userName);

    /**
     * 根据用户id获取用户信息
     *
     * @param userId 用户id
     * @return
     */
    UserInfo selectUserById(Long userId);

    /**
     * 修改密码
     *
     * @param dto
     * @return
     */
    boolean updatePsw(UpdatePswDto dto);

    /**
     * 密码加密
     *
     * @param rawPwd 原始密码
     * @return
     */
    String genEncriptPwd(String rawPwd);

    /**
     * 验证密码
     *
     * @param rawPwd     原密码
     * @param encriptPwd 加密密码
     * @return
     */
    boolean matchPassword(String rawPwd, String encriptPwd);

    /**
     * 查询学生信息
     *
     * @param idStudent 学生id
     * @return
     */
    PfStudentVo selectStudentInfo(Long idStudent);

}
