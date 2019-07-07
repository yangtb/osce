package com.osce.orm.user.login;

import com.osce.dto.user.PfUserDto;
import com.osce.entity.UserInfo;
import com.osce.vo.user.login.PfStudentVo;
import com.osce.vo.user.login.PfUsersVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfUserDao {

    /**
     * 获取用户列表
     *
     * @return
     */
    List<PfUsersVo> listUsers(PfUserDto dto);

    /**
     * 获取用户总数
     *
     * @return
     */
    Long countUsers(PfUserDto dto);

    /**
     * 保存用户信息
     *
     * @param user
     * @return
     */
    Integer saveUser(UserInfo user);

    /**
     * 删除用户角色
     *
     * @param userId 用户id
     * @return
     */
    boolean delUserRole(@Param("userId") Long userId);

    /**
     * 保存用户角色
     *
     * @param roles  角色集合
     * @param userId 用户id
     * @return
     */
    boolean saveUserRole(@Param("list") List<Long> roles,
                         @Param("userId") Long userId);

    /**
     * 判断是否存在相同的用户名
     *
     * @param userName
     * @return
     */
    boolean isExistUser(@Param("userName") String userName);

    /**
     * 编辑用户信息
     *
     * @param user
     * @return
     */
    Integer updateUser(UserInfo user);

    /**
     * 获取用户所在机构集合
     *
     * @param users
     * @return
     */
    List<Long> selectOrgId(@Param("list") List<Long> users);

    /**
     * 删除用户
     *
     * @param users 用户id集合
     * @return
     */
    int delUser(@Param("list") List<Long> users);

    /**
     * 冻结用户
     *
     * @param users 用户id集合
     * @return
     */
    int freezeUser(@Param("list") List<Long> users);

    /**
     * 根据用户获取用户信息
     *
     * @param userName
     * @return
     */
    UserInfo selectUser(@Param("userName") String userName);

    /**
     * 根据用户id获取用户信息
     *
     * @param userId
     * @return
     */
    UserInfo selectUserById(@Param("userId") Long userId);

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    Integer updatePsw(UserInfo user);

    /**
     * 查询学生信息
     *
     * @param idStudent 学生id
     * @return
     */
    PfStudentVo selectStudentInfo(Long idStudent);

}
