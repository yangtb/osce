package com.osce.server.exception;

import com.osce.ResultObject;
import com.osce.exception.RestErrorCode;
import com.osce.exception.RestException;
import com.sm.open.care.core.exception.BizRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: GlobalHttpExceptionHandler
 * @Description: spring mvc 全局异常处理
 * @Author yangtongbin
 * @Date 2019-05-01
 */
@ControllerAdvice
public class GlobalHttpExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalHttpExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultObject globalExceptionResolver(HttpServletRequest request, Exception e) {
        String requestUrl = request.getServletPath();
        LOGGER.info("【GlobalExceptionResolver】服务器内部错误, url:{}, msg:{}", requestUrl, e.getMessage());
        return ResultObject.create(requestUrl, RestErrorCode.UNKNOWN_ERROR.getCode(), RestErrorCode.UNKNOWN_ERROR.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(BizRuntimeException.class)
    public ResultObject bizRuntimeExceptionHandler(HttpServletRequest request, Exception e) {
        String requestUrl = request.getServletPath();
        BizRuntimeException bizEx = (BizRuntimeException) e;
        return ResultObject.create(requestUrl, bizEx.getErrorCode(), bizEx.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(RestException.class)
    public ResultObject restExceptionHandler(HttpServletRequest request, Exception e) {
        String requestUrl = request.getServletPath();
        RestException restException = (RestException) e;
        return ResultObject.create(requestUrl, restException.getCode(), restException.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public ResultObject accessDeniedExceptionHandler(HttpServletRequest request, Exception e) {
        if (isAjaxReq(request)) {
            String requestUrl = request.getServletPath();
            return ResultObject.create(requestUrl, RestErrorCode.ACCESS_RESTRICTED_ERROR.getMessage(), "没有访问权限");
        }
        // 无权限异常抛到上层
        throw new AccessDeniedException(e.getMessage());
    }

    /**
     * 判断是否是ajax请求
     *
     * @param request
     * @return
     */
    private boolean isAjaxReq(HttpServletRequest request) {
        return request.getHeader("x-requested-with") != null
                && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"));
    }

}
