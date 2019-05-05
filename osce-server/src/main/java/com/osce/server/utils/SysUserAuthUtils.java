package com.osce.server.utils;

import com.osce.server.security.CurrentUserUtils;
import com.osce.server.security.SecurityContext;
import com.osce.server.security.User;
import com.sm.open.care.core.enums.YesOrNoNum;

public class SysUserAuthUtils {

    /**
     * 拥有平台权限或者超级管理员
     *
     * @return
     */
    public static boolean isPlatOrSuper() {
        User user = CurrentUserUtils.getCurrentUser();
        if (SecurityContext.hasRole("ROLE_SUPER") || user.getFgPlat().equals(YesOrNoNum.YES.getCode())) {
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
