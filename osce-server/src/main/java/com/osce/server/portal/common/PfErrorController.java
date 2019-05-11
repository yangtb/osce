package com.osce.server.portal.common;

import com.osce.server.portal.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: PfCommonController
 * @Description:
 * @Author yangtongbin
 * @Date 2018/9/28
 */
@ControllerAdvice
@Controller
@RequestMapping("/error")
public class PfErrorController extends BaseController {

    @RequestMapping("/403")
    public String forbidden() {
        return "error/403";
    }

    @RequestMapping("/404")
    public String notFound() {
        return "error/404";
    }

    @RequestMapping("/500")
    public String internalServerError() {
        return "error/500";
    }

}
