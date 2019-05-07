package com.osce.server.portal.biz.training.structure.dept;

import com.osce.api.biz.training.structure.grade.PfGradeService;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import com.osce.vo.biz.training.structure.grade.GradeVo;
import com.sm.open.care.core.enums.YesOrNo;
import com.sm.open.care.core.enums.YesOrNoNum;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

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
        List<GradeVo> list = pfGradeService.listAllGrades(CurrentUserUtils.getCurrentUserIdOrg());
        model.addAttribute("allGrade", list);
        List<GradeVo> currentGradeList = list.stream().filter(gradeVo
                -> gradeVo.getFgActive().equals(YesOrNoNum.YES.getCode())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(currentGradeList)) {
            model.addAttribute("currentGrade", currentGradeList.get(0).getIdGrade());
        }
        return "pages/biz/training/structure/dept/deptPage";
    }

}
