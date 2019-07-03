package com.osce.server.portal.biz.show;

import com.osce.server.portal.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: PfShowController
 * @Description: 显示屏
 * @Author yangtongbin
 * @Date 2019-07-01
 */
@Controller
public class PfShowController extends BaseController {

    @PreAuthorize("hasAnyRole('ROLE_03_01','ROLE_SUPER')")
    @RequestMapping("/pf/p/show/big/screen")
    public String page(Model model) {
        return "pages/biz/show/screen";
    }

}
