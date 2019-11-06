package com.osce.server.portal.biz.plan.manage;

import com.osce.api.biz.plan.manage.PfPlanStationService;
import com.osce.api.biz.training.structure.sp.PfSpService;
import com.osce.dto.biz.plan.manage.AssistantDto;
import com.osce.dto.biz.plan.manage.PlanSpDto;
import com.osce.result.PageResult;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import com.sm.open.care.core.utils.Assert;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: PfPlanStationController
 * @Description: 计划 - 排站
 * @Author yangtongbin
 * @Date 2019-05-17
 */
@Controller
public class PfPlanStationController extends BaseController {

    @Reference
    private PfSpService pfSpService;

    @Reference
    private PfPlanStationService pfPlanStationService;

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/station/order")
    public String planOrder(Model model, String idPlan, Long idModel, Long idModelFrom) {
        model.addAttribute("idPlan", idPlan);
        model.addAttribute("idModel", idModel);
        model.addAttribute("idModelFrom", idModelFrom);
        return "pages/biz/plan/manage/stationPreview";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/station/sp")
    public String planSp(Model model, String idPlan) {
        model.addAttribute("idPlan", idPlan);
        return "pages/biz/plan/manage/planSp";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/station/assistant")
    public String planAssistant(Model model, String idPlan) {
        model.addAttribute("idPlan", idPlan);
        return "pages/biz/plan/manage/planAssistant";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/sp/page")
    public String planSpPage(Model model, String idPlan) {
        model.addAttribute("idPlan", idPlan);
        model.addAttribute("allSpTag", pfSpService.listSpTag(CurrentUserUtils.getCurrentUserIdOrg()));
        return "pages/biz/plan/manage/planSpPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/station/assigned/sp")
    public String assignedSp(Model model, String idPlan, String sq) {
        model.addAttribute("idPlan", idPlan);
        model.addAttribute("sq", sq);
        return "pages/biz/plan/manage/assignedSp";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/station/assigned/assistant")
    public String assignedAssistant(Model model, String idPlan, String sq) {
        model.addAttribute("idPlan", idPlan);
        model.addAttribute("sq", sq);
        return "pages/biz/plan/manage/assignedAssistant";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_01_004','ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/assistant/list")
    @ResponseBody
    public PageResult listAssistant(AssistantDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfPlanStationService.pageAssistant(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/station/publish/item/page")
    public String pagePublishItem(Model model, String idPlan) {
        model.addAttribute("idPlan", idPlan);
        return "pages/biz/plan/manage/publishItemPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/plan/publish/item/student/list")
    @ResponseBody
    public PageResult listStudentItem(String idPlan) {
        return PageResult.create(pfPlanStationService.listStudentItem(idPlan));
    }

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/plan/publish/item/sp/list")
    @ResponseBody
    public PageResult listSpItem(String idPlan) {
        return PageResult.create(pfPlanStationService.listSpItem(idPlan));
    }

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/plan/publish/item/assistant/list")
    @ResponseBody
    public PageResult listAssistantItem(String idPlan) {
        return PageResult.create(pfPlanStationService.listAssistantItem(idPlan));
    }

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/sp/station/list")
    @ResponseBody
    public PageResult listPlanSpStation(String idPlan) {
        Assert.isTrue(idPlan != null, "idPlan");
        return PageResult.create(pfPlanStationService.listPlanSpStation(idPlan));
    }

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/sp/list")
    @ResponseBody
    public PageResult listPlanSp(PlanSpDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfPlanStationService.listPlanSp1(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/sp/cache/list")
    @ResponseBody
    public PageResult listPlanSpCache(PlanSpDto dto) {
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return PageResult.create(pfPlanStationService.listPlanSpCache(dto));
    }

}
