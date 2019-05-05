package com.osce.server.rest.user.role;

import com.osce.api.user.menu.PfMenuService;
import com.osce.api.user.role.PfRoleService;
import com.osce.dto.user.role.PfRoleCommonDto;
import com.osce.dto.user.role.PfRoleListDto;
import com.osce.dto.user.role.PfRoleMenuDto;
import com.osce.entity.SysRole;
import com.osce.server.security.CurrentUserUtils;
import com.sm.open.care.core.ResultObject;
import com.sm.open.care.core.utils.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: PfRoleRestController
 * @Description: 角色模块
 * @Author yangtongbin
 * @Date 2017/9/17 23:13
 */
@Controller
@RequestMapping(value = "/pf/r/role")
public class PfRoleRestController {

    @Reference
    private PfRoleService pfRoleService;
    @Reference
    private PfMenuService pfMenuService;

    /**
     * 新增角色
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ROLE_ADD','ROLE_SUPER')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject addRole(@RequestBody SysRole dto) {
        /* 参数校验 */
        Assert.isTrue(StringUtils.isNotBlank(dto.getName()), "name");
        Assert.isTrue(StringUtils.isNotBlank(dto.getResume()), "resume");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.create("saveRole", ResultObject.SUCCESS_CODE, ResultObject.MSG_SUCCESS,
                ResultObject.DATA_TYPE_OBJECT, pfRoleService.addRole(dto));
    }

    /**
     * 编辑角色
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ROLE_EDIT','ROLE_SUPER')")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject editRole(@RequestBody SysRole dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getRoleId() != null, "roleId");
        Assert.isTrue(StringUtils.isNotBlank(dto.getName()), "name");
        Assert.isTrue(StringUtils.isNotBlank(dto.getResume()), "resume");
        return ResultObject.create("editRole", ResultObject.SUCCESS_CODE, ResultObject.MSG_SUCCESS,
                ResultObject.DATA_TYPE_OBJECT, pfRoleService.editRole(dto));
    }

    /**
     * 删除角色
     *
     * @param roles 角色id集合
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ROLE_DEL','ROLE_SUPER')")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject delRole(@RequestBody List<Long> roles) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(roles), "入参不能为空");
        return ResultObject.create("delRole", ResultObject.SUCCESS_CODE, ResultObject.MSG_SUCCESS,
                ResultObject.DATA_TYPE_OBJECT, pfRoleService.delRole(roles));
    }

    /**
     * 作废/恢复角色
     *
     * @param roles 角色id集合
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ROLE_CANCEL','ROLE_SUPER')")
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject cancelRole(@RequestBody PfRoleListDto roles) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(roles.getRoles()), "入参不能为空");
        return ResultObject.create("cancelRole", ResultObject.SUCCESS_CODE, ResultObject.MSG_SUCCESS,
                ResultObject.DATA_TYPE_OBJECT, pfRoleService.cancelRole(roles.getRoles()));
    }

    /**
     * 保存角色菜单
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ROLE_SAVE_ROLEMENU','ROLE_SUPER')")
    @RequestMapping(value = "/save/roleMenu", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject saveRoleMenu(@RequestBody PfRoleMenuDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getRoleMenus()), "roleMenus");
        return ResultObject.create("saveRoleMenu", ResultObject.SUCCESS_CODE, ResultObject.MSG_SUCCESS,
                ResultObject.DATA_TYPE_OBJECT, pfRoleService.saveRoleMenu(dto.getRoleMenus()));
    }

    /**
     * 获取系统菜单【tree】
     */
    @PreAuthorize("hasAnyRole('ROLE_ROLE_ALL_MENU','ROLE_SUPER')")
    @RequestMapping(value = "/list/tree", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject listMenuTree() {
        return ResultObject.create("listMenuTree", ResultObject.SUCCESS_CODE, ResultObject.MSG_SUCCESS,
                ResultObject.DATA_TYPE_LIST, pfMenuService.listMenuTree());
    }

    /**
     * 获取角色拥有菜单
     */
    @PreAuthorize("hasAnyRole('ROLE_ROLE_OWN_MENU','ROLE_SUPER')")
    @RequestMapping(value = "/list/role/tree", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject listMenuRoleTree(@RequestBody PfRoleCommonDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getRoleId() != null, "roleId");
        Long roleId = dto.getRoleId();
        return ResultObject.create("listMenuRoleTree", ResultObject.SUCCESS_CODE, ResultObject.MSG_SUCCESS,
                ResultObject.DATA_TYPE_LIST, pfMenuService.listMenuRoleTree(roleId));
    }

}
