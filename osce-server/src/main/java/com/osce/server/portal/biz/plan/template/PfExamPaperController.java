package com.osce.server.portal.biz.plan.template;

import com.osce.server.portal.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: PfExamPaperController
 * @Description: 设计考卷
 * @Author yangtongbin
 * @Date 2019-06-01
 */
@Controller
public class PfExamPaperController extends BaseController {

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/paper/page")
    public String page(Model model, Long idModel) {
        model.addAttribute("idModel", idModel);
        return "pages/biz/plan/template/paperPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/paper/skill/one")
    public String pageSdSkillCaOne(Model model, Long idModel) {
        model.addAttribute("idModel", idModel);
        return "pages/biz/plan/template/paperPage1";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/paper/skill/two")
    public String pageSdSkillCaTwo(Model model, Long idModel) {
        model.addAttribute("idModel", idModel);
        return "pages/biz/plan/template/paperPage2";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/device/page")
    public String pageImpactionDevice(String idSkillCase, Model model) {
        model.addAttribute("idSkillCase", idSkillCase);
        return "pages/biz/plan/template/skillDeviceImpactionPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/paper/skill/third")
    public String pageSdSkillCaThird(Model model, Long idModel) {
        model.addAttribute("idModel", idModel);
        return "pages/biz/plan/template/paperPage3";
    }

}
