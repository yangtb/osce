package com.osce.server.portal.biz.training.caseku;

import com.osce.api.biz.training.caseku.PfCaseService;
import com.osce.dto.biz.training.caseku.CaseDto;
import com.osce.dto.biz.training.caseku.CobEvaluateDto;
import com.osce.entity.CobEvaluate;
import com.osce.enums.SysDicGroupEnum;
import com.osce.result.PageResult;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import com.osce.server.utils.EnumUtil;
import com.sm.open.care.core.utils.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PfRoomController
 * @Description: PfSpController
 * @Author yangtongbin
 * @Date 2019-05-08
 */
@Controller
public class PfCaseController extends BaseController {

    @Resource
    private EnumUtil enumUtil;

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
        model.addAttribute("sdStationCaList", enumUtil.getEnumList(SysDicGroupEnum.SD_STATION_CA.getCode()));
        return "pages/biz/training/case/caseTagForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_02_01_001','ROLE_SUPER')")
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
    public String formItem(String formType, String idCase, Long idScoreSheet, Model model) {
        model.addAttribute("formType", formType);
        model.addAttribute("idCase", idCase);
        model.addAttribute("idScoreSheet", idScoreSheet);
        model.addAttribute("scoreItemCaList", enumUtil.getEnumList(SysDicGroupEnum.SD_SCORE_ITEM_CA.getCode()));
        return "pages/biz/training/case/scoreItemForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/case/item/list")
    @ResponseBody
    public PageResult pageItem(CaseDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfCaseService.pageItem(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_01_04', 'ROLE_01_05','ROLE_SUPER')")
    @RequestMapping("/pf/p/case/miniCex/page")
    public String miniCex(String cdCobEvaluate, Model model) {
        model.addAttribute("cdCobEvaluate", cdCobEvaluate);
        return "pages/biz/training/case/miniCexPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_01_05','ROLE_SUPER')")
    @RequestMapping("/pf/p/cob/evaluate/detail/page")
    public String cobEvaluateDetailPage(Model model, String idCobEvaluate) {
        model.addAttribute("idCobEvaluate", idCobEvaluate);
        return "pages/biz/training/case/cobEvaluateDetailPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_04', 'ROLE_01_05','ROLE_SUPER')")
    @RequestMapping("/pf/p/cob/evaluate/page")
    public String formCobEvaluate(String idCobEvaluate, String cdCobEvaluate, Model model) {
        model.addAttribute("idCobEvaluate", idCobEvaluate);
        model.addAttribute("cdCobEvaluate", cdCobEvaluate);
        return "pages/biz/training/case/cobEvaluateForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_04', 'ROLE_01_05','ROLE_SUPER')")
    @RequestMapping("/pf/p/cob/evaluate/detail/form")
    public String formCobEvaluateDetail(Long idCobEvaluate, Model model) {
        model.addAttribute("idCobEvaluate", idCobEvaluate);
        return "pages/biz/training/case/cobEvaluateDetailForm";
    }

}
