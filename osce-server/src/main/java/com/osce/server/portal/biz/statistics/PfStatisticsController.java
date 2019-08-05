package com.osce.server.portal.biz.statistics;

import com.osce.api.biz.statistics.PfStatisticsService;
import com.osce.dto.biz.statistics.StatisticsAssistantDto;
import com.osce.dto.biz.statistics.StatisticsDto;
import com.osce.result.PageResult;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import com.sm.open.care.core.utils.Assert;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: PfStatisticsController
 * @Description: 统计
 * @Author yangtongbin
 * @Date 2019-07-20
 */
@Controller
public class PfStatisticsController extends BaseController {

    @Reference
    private PfStatisticsService pfStatisticsService;

    @PreAuthorize("hasAnyRole('ROLE_05_01','ROLE_SUPER')")
    @RequestMapping("/pf/p/statistics/test/page")
    public String page(Model model) {
        return "pages/biz/statistics/testResult";
    }

    @PreAuthorize("hasAnyRole('ROLE_05_01','ROLE_SUPER')")
    @RequestMapping("/pf/p/statistics/test/score/page")
    public String pageTestStuScore(Model model, Long idPlan) {
        model.addAttribute("idPlan", idPlan);
        return "pages/biz/statistics/testStuScore";
    }

    @PreAuthorize("hasAnyRole('ROLE_05_01','ROLE_SUPER')")
    @RequestMapping("/pf/p/statistics/test/score/detail/page")
    public String pageTestStuScoreDetail(Model model, Long idExec) {
        model.addAttribute("idExec", idExec);
        return "pages/biz/statistics/testStuScoreDetail";
    }

    @PreAuthorize("hasAnyRole('ROLE_05_01','ROLE_SUPER')")
    @RequestMapping("/pf/p/statistics/test/score/detail1/page")
    public String pageTestStuScoreDetail1(Model model, Long idExec) {
        model.addAttribute("idExec", idExec);
        return "pages/biz/statistics/testStuScoreDetail1";
    }

    @PreAuthorize("hasAnyRole('ROLE_05_01','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/statistics/test/list")
    @ResponseBody
    public PageResult pageTests(StatisticsDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfStatisticsService.pageTests(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_05_01','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/statistics/test/score/list")
    @ResponseBody
    public PageResult pageTestScore(StatisticsDto dto) {
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfStatisticsService.pageTestScore(dto);
    }

    /**
     * 理论试题得分明细
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_05_01','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/statistics/stu/score1/list")
    @ResponseBody
    public PageResult pageStuScore1(StatisticsDto dto) {
        Assert.isTrue(dto.getIdExec() != null, "idExec");
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return PageResult.create(pfStatisticsService.listStuScore1(dto));
    }

    /**
     * 考官评分明细
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_05_01','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/statistics/assistant/score/list")
    @ResponseBody
    public PageResult listAssistantStuScore(StatisticsAssistantDto dto) {
        Assert.isTrue(dto.getIdExec() != null, "idExec");
        Assert.isTrue(StringUtils.isNotBlank(dto.getCdAssistantCa()), "cdAssistantCa");
        return PageResult.create(pfStatisticsService.listAssistantStuScore(dto));
    }

}
