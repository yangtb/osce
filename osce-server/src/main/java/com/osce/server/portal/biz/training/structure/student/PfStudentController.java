package com.osce.server.portal.biz.training.structure.student;

import com.osce.api.biz.training.structure.dept.PfDeptService;
import com.osce.api.biz.training.structure.grade.PfGradeService;
import com.osce.api.biz.training.structure.student.PfStudentService;
import com.osce.api.system.org.PfOrgService;
import com.osce.dto.biz.training.structure.student.StudentDto;
import com.osce.result.PageResult;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import com.osce.server.security.User;
import com.osce.server.security.rsa.RsaKeyPairQueue;
import com.osce.server.utils.DownloadFileUtil;
import com.osce.server.utils.SysUserAuthUtils;
import com.osce.vo.PfTreeSelectVo;
import com.osce.vo.biz.training.structure.grade.GradeVo;
import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.care.core.utils.rsa.RsaKeyPair;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: PfDeptController
 * @Description: 学员管理
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@Controller
public class PfStudentController extends BaseController {

    @Reference
    private PfGradeService pfGradeService;

    @Reference
    private PfDeptService pfDeptService;

    @Reference
    private PfStudentService pfStudentService;

    @Reference
    private PfOrgService pfOrgService;

    @Resource(name = "rsaKeyPairQueue")
    private RsaKeyPairQueue rsaKeyPairQueue;

    @PreAuthorize("hasAnyRole('ROLE_01_01_003','ROLE_SUPER')")
    @RequestMapping("/pf/p/student/page")
    public String page(Model model) {
        List<GradeVo> list = pfGradeService.listAllGrades(CurrentUserUtils.getCurrentUserIdOrg());
        model.addAttribute("allGrade", list);

        List<GradeVo> currentGradeList = list.stream().filter(gradeVo
                -> gradeVo.getFgActive().equals(YesOrNoNum.YES.getCode())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(currentGradeList)) {
            model.addAttribute("currentGrade", currentGradeList.get(0).getIdGrade());
        }
        return "pages/biz/training/structure/student/studentPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_01_003','ROLE_SUPER')")
    @RequestMapping("/pf/p/student/form")
    public String form(String formType, Model model, HttpServletRequest request) {
        // 机构处理
        User user = CurrentUserUtils.getCurrentUser();

        model.addAttribute("formType", formType);
        model.addAttribute("idOrg", user.getIdOrg());
        model.addAttribute("allGrade", pfGradeService.listAllGrades(user.getIdOrg()));
        model.addAttribute("allStudent", pfDeptService.listAllDept(user.getIdOrg()));

        if (SysUserAuthUtils.isPlatOrSuper()) {
            model.addAttribute("allOrg", pfOrgService.selectOrgTreeSelect(null));
        } else {
            List<PfTreeSelectVo> myOrgList = pfOrgService.selectOrgTreeSelect(null).stream()
                    .filter(sysOrg -> sysOrg.getId().equals(user.getIdOrg())).collect(Collectors.toList());
            model.addAttribute("allOrg", myOrgList);
        }
        model.addAttribute("userOrgId", user.getIdOrg());
        RsaKeyPair keyPair;
        try {
            keyPair = rsaKeyPairQueue.takeQueue(request);
            model.addAttribute("publicKey", keyPair.getPublicKey());
        } catch (InterruptedException e) {
            logger.error("rsa公私钥队列相关操作异常", e);
        }
        return "pages/biz/training/structure/student/studentForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_01_002', 'ROLE_02_02_001','ROLE_01_01_003','ROLE_05_01','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/student/list")
    @ResponseBody
    public PageResult listStudent(StudentDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfStudentService.pageStudents(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_01_03_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/student/template/download")
    public Object templateDownLoad() {
        ResponseEntity<InputStreamResource> response = null;
        try {
            response = DownloadFileUtil.download("user", "userTemplate.xlsx", "OSCE学员批量导入模板1.0");
        } catch (Exception e) {
            logger.error("下载模板失败");
        }
        return response;
    }

    @PreAuthorize("hasAnyRole('ROLE_01_01_003','ROLE_SUPER')")
    @RequestMapping("/pf/p/student/move/page")
    public String moveStudentPage(Model model) {
        return "pages/biz/training/structure/student/studentMovePage";
    }

}
