package com.osce.server.utils;

import com.osce.server.security.SecurityContext;

public class SysUserAuthUtils {

    /**
     * 拥有平台权限或者超级管理员
     *
     * @return
     */
    public static boolean isPlatOrSuper() {
        if (SecurityContext.hasRole("ROLE_SUPER")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 超级管理员
     *
     * @return
     */
    public static boolean isSuper() {
        return SecurityContext.hasRole("ROLE_SUPER") ? true : false;
    }
}
