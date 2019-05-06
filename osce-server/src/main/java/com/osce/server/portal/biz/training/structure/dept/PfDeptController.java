package com.osce.server.portal.biz.training.structure.dept;

import com.osce.api.biz.training.structure.grade.PfGradeService;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: PfDeptController
 * @Description: 部门管理
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@Controller
public class PfDeptController extends BaseController {

    @Reference
    private PfGradeService pfGradeService;

    @PreAuthorize("hasAnyRole('ROLE_01_01_002','ROLE_SUPER')")
    @RequestMapping("/pf/p/dept/page")
    public String page(Model model) {
        model.addAttribute("allGrade", pfGradeService.listAllGrades(CurrentUserUtils.getCurrentUserIdOrg()));
        return "pages/biz/training/structure/dept/deptPage";
    }

}
