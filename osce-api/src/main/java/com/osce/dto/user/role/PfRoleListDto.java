package com.osce.dto.user.role;


import com.osce.entity.SysRole;

import java.io.Serializable;
import java.util.List;

public class PfRoleListDto implements Serializable {

    private static final long serialVersionUID = 5703704673324231853L;

    private List<SysRole> roles;

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
