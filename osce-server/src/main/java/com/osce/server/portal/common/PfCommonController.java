package com.osce.server.portal.common;

import com.osce.server.portal.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: PfCommonController
 * @Description:
 * @Author yangtongbin
 * @Date 2018/9/28
 */
@Controller
public class PfCommonController extends BaseController {

    @RequestMapping("/video/form")
    public String videoForm(Model model, String path) {
        model.addAttribute("path", path);
        return "pages/common/videoForm";
    }

    @RequestMapping("/empty/page")
    public String emptyPage() {
        return "pages/common/emptyPage";
    }

    /**
     * 空接口
     *
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_03_01','ROLE_SUPER')")
    @PostMapping(value = "/keep/on/line")
    public String keepOnline() {
        return null;
    }

}
