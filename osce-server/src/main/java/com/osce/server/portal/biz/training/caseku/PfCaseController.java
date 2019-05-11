package com.osce.server.portal.biz.training.caseku;

import com.osce.api.biz.training.res.room.PfRoomService;
import com.osce.dto.biz.training.res.room.RoomDto;
import com.osce.result.PageResult;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * @ClassName: PfRoomController
 * @Description: PfSpController
 * @Author yangtongbin
 * @Date 2019-05-08
 */
@Controller
public class PfCaseController extends BaseController {

    @Reference
    private PfRoomService pfRoomService;

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
    public PageResult pageRooms(RoomDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return PageResult.create(new ArrayList<>());
    }

}
