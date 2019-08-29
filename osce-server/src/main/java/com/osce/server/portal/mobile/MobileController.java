package com.osce.server.portal.mobile;

import com.osce.server.portal.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: MobileController
 * @Description: 移动端入口
 * @Author yangtongbin
 * @Date 2019-08-28
 */
@Controller
public class MobileController extends BaseController {

    @Value("${mobile.url}")
    private String mobileUrl;

    @RequestMapping(value = "/mobile")
    public String home(Model model,
                       Long idPlan,
                       Long idArea,
                       String timeSection,
                       Long idRoom) {
        model.addAttribute("mobileUrl", mobileUrl);
        model.addAttribute("idPlan", idPlan);
        model.addAttribute("idArea", idArea);
        model.addAttribute("timeSection", timeSection);
        model.addAttribute("idRoom", idRoom);
        return "mobile";
    }

}
