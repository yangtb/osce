package com.osce.server.portal.biz.training.structure.grade;

import com.osce.api.biz.training.structure.grade.PfGradeService;
import com.osce.dto.biz.training.structure.grade.GradeDto;
import com.osce.result.PageResult;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: PfGradeController
 * @Description: 学届管理
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@Controller
public class PfGradeController extends BaseController {

    @Reference
    private PfGradeService pfGradeService;

    @PreAuthorize("hasAnyRole('ROLE_01_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/grade/page")
    public String page(Model model) {
        return "pages/biz/training/structure/grade/grade";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/grade/form")
    public String form(String formType, Model model) {
        model.addAttribute("formType", formType);
        return "pages/biz/training/structure/grade/gradeForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/grade/list")
    @ResponseBody
    public PageResult listGrade(GradeDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfGradeService.pageGrades(dto);
    }

}
