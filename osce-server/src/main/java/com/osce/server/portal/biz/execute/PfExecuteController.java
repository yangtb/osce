package com.osce.server.portal.biz.execute;

import com.osce.server.portal.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: PfExecuteController
 * @Description: 考试执行
 * @Author yangtongbin
 * @Date 2019-07-08
 */
@Controller
public class PfExecuteController extends BaseController {

    @PreAuthorize("hasAnyRole('ROLE_03_01','ROLE_SUPER')")
    @RequestMapping("/pf/p/sho")
    public String page(Model model) {
        return "pages/biz/show/bigScreen";
    }


}
