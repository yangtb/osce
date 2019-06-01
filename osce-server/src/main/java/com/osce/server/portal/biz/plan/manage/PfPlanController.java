package com.osce.server.portal.biz.plan.manage;

import com.osce.api.biz.training.structure.grade.PfGradeService;
import com.osce.server.portal.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: PfPlanController
 * @Description: 实训计划
 * @Author yangtongbin
 * @Date 2019-05-17
 */
@Controller
public class PfPlanController extends BaseController {

    @Reference
    private PfGradeService pfGradeService;

    @PreAuthorize("hasAnyRole('ROLE_02_01_002','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/manage/page")
    public String page(Model model) {
        return "pages/biz/plan/manage/planPage";
    }


}
