package com.osce.server.portal.biz.show;

import com.osce.api.biz.show.PfShowService;
import com.osce.dto.biz.show.ShowDto;
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

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @ClassName: PfShowController
 * @Description: 显示屏
 * @Author yangtongbin
 * @Date 2019-07-01
 */
@Controller
public class PfShowController extends BaseController {

    @Reference
    private PfShowService pfShowService;

    @PreAuthorize("hasAnyRole('ROLE_03_01','ROLE_SUPER')")
    @RequestMapping("/pf/p/show/big/screen")
    public String page(Model model) {
        return "pages/biz/show/bigScreen";
    }

    @PreAuthorize("hasAnyRole('ROLE_03_01','ROLE_SUPER')")
    @RequestMapping("/pf/p/aio/main")
    public String aioMainPage(Model model) {
        return "pages/biz/show/aioMain";
    }


    /**
     * 待考区- 已登记列表
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_03_01','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/aio/student/registered")
    @ResponseBody
    public PageResult pagePick(ShowDto dto) {
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        Assert.isTrue(dto.getIdArea() != null, "idArea");
        Assert.isTrue(dto.getTimeSection() >= 0, "timeSection");
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return PageResult.create(pfShowService.listAioRegistered(dto));
    }

    /**
     * 考站壁挂式一体机-非理论
     *
     * @param model
     * @param idRoom 房间号
     * @param naRoom 房间名称
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_03_01','ROLE_SUPER')")
    @RequestMapping("/pf/p/aio/station/show")
    public String aioStationPage(Model model, String idRoom, String naRoom) {
        model.addAttribute("idRoom", idRoom);
        model.addAttribute("naRoom", naRoom);
        return "pages/biz/show/aioStation";
    }

}
