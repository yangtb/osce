package com.osce.server.portal.system.monitor;

import com.osce.api.system.monitor.PfMonitorService;
import com.osce.server.portal.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: PfMonitorRestController
 * @Description: 系统监控接口
 * @Author yangtongbin
 * @Date 2017/10/9 09:44
 */
@Controller
public class PfMonitorController extends BaseController {

    @Reference
    private PfMonitorService pfMonitorService;

    @PreAuthorize("hasAnyRole('ROLE_SYS_MONITOR','ROLE_SUPER')")
    @RequestMapping("/pf/p/monitor/server/page")
    public String page(Model model) {
        model.addAttribute("serverInfo", pfMonitorService.selectServerInfo());
        return "pages/system/monitor/serverInfo";
    }
}
