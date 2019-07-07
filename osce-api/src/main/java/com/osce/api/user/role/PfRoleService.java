package com.osce.api.user.role;

import com.osce.dto.user.role.PfRoleDto;
import com.osce.entity.SysRole;
import com.osce.entity.SysRoleMenu;
import com.osce.result.PageResult;
import com.osce.vo.user.role.PfRoleVo;

import java.util.List;

/**
 * @ClassName: PfRoleService
 * @Description: 用户角色
 * @Author yangtongbin
 * @Date 2017/9/9 15:52
 */
public interface PfRoleService {

    /**
     * 获取角色列表
     *
     * @return
     */
    PageResult listRoles(PfRoleDto dto);

    /**
     * 查询角色列表
     *
     * @return
     */
    List<PfRoleVo> list();

    /**
     * 获取用户所有角色
     *
     * @param userId 用户id
     * @return
     */
    List<PfRoleVo> listUserRole(Long userId);

    /**
     * 角色总数
     */
    Long countRoles(PfRoleDto dto);

    /**
     * 新增菜单
     *
     * @param dto
     * @return
     */
    boolean addRole(SysRole dto);

    /**
     * 判断是否存在该角色
     *
     * @param roleName 角色名称
     * @return
     */
    boolean isExistRole(String roleName);

    /**
     * 修改菜单
     *
     * @param dto
     * @return
     */
    boolean editRole(SysRole dto);

    /**
     * 删除角色所有菜单
     *
     * @param roleId 角色ID
     * @return
     */
    boolean delRoleMenu(Long roleId);

    /**
     * 保存角色菜单
     *
     * @param dto
     * @return
     */
    boolean saveRoleMenu(List<SysRoleMenu> dto);

    /**
     * 删除菜单
     *
     * @param roles
     * @return
     */
    boolean delRole(List<Long> roles);

    /**
     * 作废/恢复角色
     *
     * @param roles
     * @return
     */
    boolean cancelRole(List<SysRole> roles);

    /**
     * 根据角色编码获取角色信息
     *
     * @param code 角色编码
     * @return
     */
    PfRoleVo selectRoleInfoByCode(String code);

    /**
     * 根据用户id获取角色level
     *
     * @param userId 用户id
     * @return
     */
    PfRoleVo selectRoleLevel(Long userId);

    /**
     * 需要过期提醒
     *
     * @param userId 用户id
     * @return
     */
    boolean needExpireNotice(Long userId);

    /**
     * 用户拥有角色编码集合
     *
     * @param userId   用户id
     * @return
     */
    List<String> selectUserRoleCode(Long userId);
}
