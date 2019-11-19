package com.osce.server.portal.biz.plan.template;

import com.osce.api.biz.plan.template.PfPaperService;
import com.osce.dto.biz.plan.template.PfPaperDto;
import com.osce.dto.biz.plan.template.TemplateDto;
import com.osce.enums.SysDicGroupEnum;
import com.osce.result.PageResult;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import com.osce.server.utils.EnumUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @ClassName: PfExamPaperController
 * @Description: 设计考卷
 * @Author yangtongbin
 * @Date 2019-06-01
 */
@Controller
public class PfExamPaperController extends BaseController {

    @Resource
    private EnumUtil enumUtil;

    @Reference
    private PfPaperService pfPaperService;

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/paper/page")
    public String page(Model model, String idModel) {
        model.addAttribute("idModel", idModel);
        return "pages/biz/plan/template/paperPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/paper/skill/one")
    public String pageSdSkillCaOne(Model model, String idModel) {
        model.addAttribute("idModel", idModel);
        return "pages/biz/plan/template/paperPage1";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/paper/skill/two")
    public String pageSdSkillCaTwo(Model model, String idModel) {
        model.addAttribute("idModel", idModel);
        model.addAttribute("sdStationCaList", enumUtil.getEnumList(SysDicGroupEnum.SD_STATION_CA.getCode()));
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
    public String pageSdSkillCaThird(Model model, String idModel) {
        model.addAttribute("idModel", idModel);
        model.addAttribute("sdStationCaList", enumUtil.getEnumList(SysDicGroupEnum.SD_STATION_CA.getCode()));
        return "pages/biz/plan/template/paperPage3";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/plan/paper/item/list")
    @ResponseBody
    public PageResult listItem(PfPaperDto dto) {
        return pfPaperService.listItem(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/plan/paper/list")
    @ResponseBody
    public PageResult listPaper(PfPaperDto dto) {
        return pfPaperService.listPaper(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/plan/paper/sp/list")
    @ResponseBody
    public PageResult listSp(PfPaperDto dto) {
        return pfPaperService.listSp(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/plan/paper/skill/list")
    @ResponseBody
    public PageResult listSkill(PfPaperDto dto) {
        return pfPaperService.listSkill(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/plan/exam/paper/list")
    @ResponseBody
    public PageResult pagePaper(PfPaperDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfPaperService.pagePaper(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/plan/exam/skill/list")
    @ResponseBody
    public PageResult pagePaperSkill(PfPaperDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfPaperService.pagePaperSkill(dto);
    }
}
