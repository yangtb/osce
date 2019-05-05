package com.osce.orm.user.role;

import com.osce.dto.user.role.PfRoleDto;
import com.osce.entity.SysRole;
import com.osce.entity.SysRoleMenu;
import com.osce.entity.UserRole;
import com.osce.vo.user.role.PfRoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfRoleDao {

    /**
     * 获取所有角色
     *
     * @return
     */
    List<PfRoleVo> listRoles(PfRoleDto dto);

    /**
     * 查询角色列表
     *
     * @return
     */
    List<PfRoleVo> list();

    /**
     * 根据用户id获取角色level
     *
     * @param userId 用户id
     * @return
     */
    PfRoleVo selectRoleLevel(Long userId);

    /**
     * 获取用户所有角色
     *
     * @param userId 用户id
     * @return
     */
    List<PfRoleVo> listUserRole(@Param("userId") Long userId);

    /**
     * 获取角色总数
     *
     * @return
     */
    Long countRoles(PfRoleDto dto);

    /**
     * 新增菜单
     *
     * @param dto
     * @return
     */
    Integer addRole(SysRole dto);

    /**
     * 判断是否存在该角色
     *
     * @param roleName 角色名称
     * @return
     */
    boolean isExistRole(@Param("roleName") String roleName);

    /**
     * 删除角色所有菜单
     *
     * @param roleId 角色id
     * @return
     */
    int delRoleMenu(@Param("roleId") Long roleId);

    /**
     * 保存角色所有菜单
     *
     * @param list
     * @return
     */
    int saveRoleMenu(@Param("list") List<SysRoleMenu> list);

    /**
     * 获取用户角色
     *
     * @param userId
     * @return
     */
    List<UserRole> listRole(@Param("userId") Long userId);

    /**
     * 修改菜单
     *
     * @param dto
     * @return
     */
    Integer updateRole(SysRole dto);

    /**
     * 删除菜单
     *
     * @param roles
     * @return
     */
    Integer delRole(@Param("list") List<Long> roles);

    /**
     * 作废/恢复角色
     *
     * @param state  状态
     * @param roleId 角色id
     * @return
     */
    Integer cancelRole(@Param("state") Integer state,
                       @Param("roleId") Long roleId);


    /**
     * 根据角色编码获取角色信息
     *
     * @param code 角色编码
     * @return
     */
    PfRoleVo selectRoleInfoByCode(@Param("code") String code);

    /**
     * 需要过期提醒
     *
     * @param userId 用户id
     * @return
     */
    boolean needExpireNotice(@Param("userId") Long userId);

    /**
     * 用户拥有角色编码集合
     *
     * @param userId 用户id
     * @return
     */
    List<String> selectUserRoleCode(@Param("userId") Long userId);
}
