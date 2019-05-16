package com.osce.server.portal.biz.training.caseku;

import com.osce.api.biz.training.caseku.PfCaseService;
import com.osce.dto.biz.training.caseku.CaseDto;
import com.osce.dto.biz.training.item.ItemDto;
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
public class PfCaseController extends BaseController {

    @Reference
    private PfCaseService pfCaseService;

    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_SUPER')")
    @RequestMapping("/pf/p/case/page")
    public String page() {
        return "pages/biz/training/case/casePage";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_SUPER')")
    @RequestMapping("/pf/p/case/form")
    public String form(String formType, Model model) {
        model.addAttribute("formType", formType);
        return "pages/biz/training/case/caseTagForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/case/list")
    @ResponseBody
    public PageResult pageCase(CaseDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfCaseService.pageCase(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_SUPER')")
    @RequestMapping("/pf/p/case/item/page")
    public String pageDevice(String idCase, Model model) {
        model.addAttribute("idCase", idCase);
        return "pages/biz/training/case/scoreItemPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_SUPER')")
    @RequestMapping("/pf/p/case/sheet/page")
    public String formSection(String idCase, Model model) {
        model.addAttribute("idCase", idCase);
        return "pages/biz/training/case/scoreSheetForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_SUPER')")
    @RequestMapping("/pf/p/case/item/form")
    public String formItem(String formType, String idCase, Long idScoreSheet,  Model model) {
        model.addAttribute("formType", formType);
        model.addAttribute("idCase", idCase);
        model.addAttribute("idScoreSheet", idScoreSheet);
        return "pages/biz/training/case/scoreItemForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/case/item/list")
    @ResponseBody
    public PageResult pageItem(CaseDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfCaseService.pageItem(dto);
    }

}
