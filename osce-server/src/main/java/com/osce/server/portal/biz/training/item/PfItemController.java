package com.osce.server.portal.biz.training.item;

import com.osce.api.biz.training.item.PfItemService;
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
public class PfItemController extends BaseController {

    @Reference
    private PfItemService pfItemService;

    @PreAuthorize("hasAnyRole('ROLE_01_03_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/item/page")
    public String page() {
        return "pages/biz/training/item/itemPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_03_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/item/form")
    public String form(String formType, Model model) {
        model.addAttribute("formType", formType);
        return "pages/biz/training/item/itemTagForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_03_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/item/list")
    @ResponseBody
    public PageResult pageRooms(ItemDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfItemService.pageItem(dto);
    }

}
