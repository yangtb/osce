package com.osce.server.portal.biz.plan.template.score;

import com.osce.api.biz.plan.template.score.PfCaseScoreService;
import com.osce.dto.biz.training.caseku.CaseDto;
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
 * @ClassName: PfRoomController
 * @Description: 评分表
 * @Author yangtongbin
 * @Date 2019-05-08
 */
@Controller
public class PfCaseScoreController extends BaseController {

    @Resource
    private EnumUtil enumUtil;

    @Reference
    private PfCaseScoreService pfCaseScoreService;

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/paper/item/page")
    public String pageDevice(String idCase, Model model) {
        model.addAttribute("idCase", idCase);
        return "pages/biz/plan/template/score/scoreItemPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/paper/sheet/page")
    public String formSection(String idCase, Model model) {
        model.addAttribute("idCase", idCase);
        return "pages/biz/plan/template/score/scoreSheetForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/paper/item/form")
    public String formItem(String formType, String idCase, Long idScoreSheet,  Model model) {
        model.addAttribute("formType", formType);
        model.addAttribute("idCase", idCase);
        model.addAttribute("idScoreSheet", idScoreSheet);
        model.addAttribute("scoreItemCaList", enumUtil.getEnumList(SysDicGroupEnum.SD_SCORE_ITEM_CA.getCode()));
        return "pages/biz/plan/template/score/scoreItemForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/plan/paper/item/td/list")
    @ResponseBody
    public PageResult pageItem(CaseDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfCaseScoreService.pageItem(dto);
    }

}
