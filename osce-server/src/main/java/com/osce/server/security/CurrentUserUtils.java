package com.osce.server.security;

import com.osce.exception.RestErrorCode;
import com.sm.open.care.core.exception.BizRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrentUserUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserUtils.class);

    /**
     * 获取当前用户信息
     *
     * @return
     */
    public static User getCurrentUser() {
        User user = SecurityContext.currentUser();
        if (null == user) {
            LOGGER.error("session_user为空，访问被拒绝");
            throw new BizRuntimeException(RestErrorCode.ACCESS_RESTRICTED_ERROR.getCode(), "session_user为空，访问被拒绝");
        }
        return user;

    }

    /**
     * 获取当前用户id
     *
     * @return
     */
    public static Long getCurrentUserId() {
        User user = SecurityContext.currentUser();
        if (null == user) {
            LOGGER.error("session_user为空，访问被拒绝");
            throw new BizRuntimeException(RestErrorCode.ACCESS_RESTRICTED_ERROR.getCode(), "session_user为空，访问被拒绝");
        }
        return user.getUserId();
    }

    /**
     * 获取当前用户名
     *
     * @return
     */
    public static String getCurrentUsername() {
        User user = SecurityContext.currentUser();
        if (null == user) {
            LOGGER.error("session_user为空，访问被拒绝");
            throw new BizRuntimeException(RestErrorCode.ACCESS_RESTRICTED_ERROR.getCode(), "session_user为空，访问被拒绝");
        }
        return user.getUsername();
    }

    /**
     * 获取当前用户所在机构
     *
     * @return
     */
    public static Long getCurrentUserIdOrg() {
        User user = SecurityContext.currentUser();
        if (null == user) {
            LOGGER.error("session_user为空，访问被拒绝");
            throw new BizRuntimeException(RestErrorCode.ACCESS_RESTRICTED_ERROR.getCode(), "session_user为空，访问被拒绝");
        }
        return user.getIdOrg();
    }
}
