package com.osce.dto.user.role;

import com.osce.entity.SysRoleMenu;

import java.io.Serializable;
import java.util.List;

public class PfRoleMenuDto implements Serializable {

    private static final long serialVersionUID = 5703704673324231853L;

    private List<SysRoleMenu> roleMenus;

    public List<SysRoleMenu> getRoleMenus() {
        return roleMenus;
    }

    public void setRoleMenus(List<SysRoleMenu> roleMenus) {
        this.roleMenus = roleMenus;
    }
}
