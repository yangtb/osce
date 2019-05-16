package com.osce.server.portal.biz.training.skill;

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
public class PfSkillController extends BaseController {

    @Reference
    private PfSkillService pfSkillService;

    @PreAuthorize("hasAnyRole('ROLE_01_05','ROLE_SUPER')")
    @RequestMapping("/pf/p/skill/page")
    public String page() {
        return "pages/biz/training/skill/skillPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_05','ROLE_SUPER')")
    @RequestMapping("/pf/p/skill/form")
    public String form(String formType, Model model) {
        model.addAttribute("formType", formType);
        return "pages/biz/training/skill/skillTagForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_05','ROLE_SUPER')")
    @RequestMapping("/pf/p/skill/device/page")
    public String pageDevice(String idSkillCase, Model model) {
        model.addAttribute("idSkillCase", idSkillCase);
        return "pages/biz/training/skill/skillDevicePage";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_05','ROLE_SUPER')")
    @RequestMapping("/pf/p/skill/device/form")
    public String formDevice(String formType,String idSkillCase, Model model) {
        model.addAttribute("formType", formType);
        model.addAttribute("idSkillCase", idSkillCase);
        return "pages/biz/training/skill/skillDeviceForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_05','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/skill/list")
    @ResponseBody
    public PageResult pageSkill(SkillDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfSkillService.pageSkill(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_01_05','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/skill/device/list")
    @ResponseBody
    public PageResult pageSkillDevice(SkillDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfSkillService.pageSkillDevice(dto);
    }

}
