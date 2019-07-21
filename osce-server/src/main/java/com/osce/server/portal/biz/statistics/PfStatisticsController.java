package com.osce.server.portal.biz.statistics;

import com.osce.api.biz.statistics.PfStatisticsService;
import com.osce.dto.biz.statistics.StatisticsDto;
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
    @RequestMapping(value = "/pf/p/statistics/test/list")
    @ResponseBody
    public PageResult pageTests(StatisticsDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfStatisticsService.pageTests(dto);
    }

}
