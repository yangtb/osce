package com.osce.server.portal.system.org;

import com.osce.api.system.org.PfOrgService;
import com.osce.server.portal.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: PfOrgRestController
 * @Description: 机构rest服务
 * @Author yangtongbin
 * @Date 2018/9/20 13:59
 */
@Controller
public class PfOrgController extends BaseController {

    @Reference
    private PfOrgService pfOrgService;

    @PreAuthorize("hasAnyRole('ROLE_ORG_MG','ROLE_SUPER')")
    @RequestMapping("/pf/p/org/page")
    public String page() {
        return "pages/system/org/orgPage";
    }

}
