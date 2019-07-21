package com.osce.server.portal.biz.monitor;

import com.osce.api.biz.show.PfShowService;
import com.osce.server.portal.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: PfMonitorController
 * @Description: 监控
 * @Author yangtongbin
 * @Date 2019-07-20
 */
@Controller
public class PfAreaMonitorController extends BaseController {

    @Reference
    private PfShowService pfShowService;

    @PreAuthorize("hasAnyRole('ROLE_05_01','ROLE_SUPER')")
    @RequestMapping("/pf/p/monitor/area/page")
    public String page(Model model) {
        return "pages/biz/monitor/monitor";
    }


}

