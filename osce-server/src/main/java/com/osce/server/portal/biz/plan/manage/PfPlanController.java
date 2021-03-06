package com.osce.server.portal.biz.plan.manage;

import com.osce.api.biz.training.structure.grade.PfGradeService;
import com.osce.dto.biz.plan.manage.PlanDto;
import com.osce.dto.biz.plan.template.TemplateDto;
import com.osce.result.PageResult;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

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

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/manage/page")
    public String page(Model model) {
        return "pages/biz/plan/manage/planPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/manage/form")
    public String planForm(Model model) {
        return "pages/biz/plan/manage/planForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/plan/manage/list")
    @ResponseBody
    public PageResult pagePlan(PlanDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return PageResult.create(new ArrayList<>());
    }

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/manage/assigned/student/page")
    public String assignedStudentPage(Model model) {
        return "pages/biz/plan/manage/assignedStudent";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/manage/tpPicking/page")
    public String tpPickingPage(Model model) {
        return "pages/biz/plan/manage/tpPickingPage";
    }


}
