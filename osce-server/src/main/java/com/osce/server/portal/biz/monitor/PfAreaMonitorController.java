package com.osce.server.portal.biz.monitor;

import com.osce.api.biz.monitor.PfAreaMonitorService;
import com.osce.dto.biz.monitor.MonitorDto;
import com.osce.dto.biz.training.caseku.CaseDto;
import com.osce.entity.TdScoreItem;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import com.sm.open.care.core.utils.Assert;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: PfMonitorController
 * @Description: 监控
 * @Author yangtongbin
 * @Date 2019-07-20
 */
@Controller
public class PfAreaMonitorController extends BaseController {

    @Reference
    private PfAreaMonitorService pfAreaMonitorService;

    @PreAuthorize("hasAnyRole('ROLE_05_01','ROLE_SUPER')")
    @RequestMapping("/pf/p/monitor/area/page")
    public String page(Model model) {
        return "pages/biz/monitor/monitor";
    }

    @PreAuthorize("hasAnyRole('ROLE_05_01','ROLE_SUPER')")
    @RequestMapping("/pf/p/monitor/area/hmi/page")
    public String page1(Model model, Long idInsStation) {
        model.addAttribute("idInsStation", idInsStation);
        return "pages/biz/monitor/hmi";
    }

    @PreAuthorize("hasAnyRole('ROLE_05_01','ROLE_SUPER')")
    @RequestMapping("/pf/p/monitor/addStu/page")
    public String addStuPage(Model model) {
        return "pages/biz/monitor/addStudent";
    }

    /**
     * 待考学员
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_05_01','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/monitor/area/list/toBeExamined")
    @ResponseBody
    public PageResult listToBeExaminedStu(MonitorDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return PageResult.create(pfAreaMonitorService.listToBeExaminedStu(dto));
    }

    /**
     * 场内学员
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_05_01','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/monitor/area/list/onSite")
    @ResponseBody
    public PageResult listOnSiteStu(MonitorDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return PageResult.create(pfAreaMonitorService.listOnSiteStu(dto));
    }

    /**
     * 结束学员
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_05_01','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/monitor/area/list/end")
    @ResponseBody
    public PageResult listEndStu(MonitorDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return PageResult.create(pfAreaMonitorService.listEndStu(dto));
    }

    /**
     * 评分项
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_05_01','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/monitor/area/case/item/list")
    @ResponseBody
    public PageResult pageItem(CaseDto dto) {
        Assert.isTrue(dto.getIdScoreSheet() != null, "idScoreSheet");
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        List<TdScoreItem> items = pfAreaMonitorService.pageItem(dto);
        return ResultFactory.initPageResultWithSuccess(0L, items);
    }

}

