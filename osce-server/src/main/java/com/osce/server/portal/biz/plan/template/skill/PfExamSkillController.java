package com.osce.server.portal.biz.plan.template.skill;

import com.osce.api.biz.plan.template.skill.PfExamSkillService;
import com.osce.api.biz.training.skill.PfSkillService;
import com.osce.dto.biz.training.skill.SkillDto;
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
 * @ClassName: PfRoomController
 * @Description: PfSpController
 * @Author yangtongbin
 * @Date 2019-05-08
 */
@Controller
public class PfExamSkillController extends BaseController {

    @Reference
    private PfExamSkillService pfExamSkillService;


    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/exam/skill/device/page")
    public String pageDevice(String idSkillCase, Model model) {
        model.addAttribute("idSkillCase", idSkillCase);
        return "pages/biz/plan/template/skill/skillDevicePage";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/exam/skill/device/form")
    public String formDevice(String formType,String idSkillCase, Model model) {
        model.addAttribute("formType", formType);
        model.addAttribute("idSkillCase", idSkillCase);
        return "pages/biz/plan/template/skill/skillDeviceForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/exam/skill/device/list")
    @ResponseBody
    public PageResult pageSkillDevice(SkillDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfExamSkillService.pageSkillDevice(dto);
    }

}
