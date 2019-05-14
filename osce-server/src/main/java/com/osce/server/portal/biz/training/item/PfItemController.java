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
    @RequestMapping("/pf/p/item/manage/page")
    public String pageDevice(Long idItemStore, Model model) {
        model.addAttribute("idItemStore", idItemStore);
        return "pages/biz/training/item/itemManagePage";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_03_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/item/section/form")
    public String formSection(Long idItemStore, Model model) {
        model.addAttribute("idItemStore", idItemStore);
        return "pages/biz/training/item/sectionForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_03_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/item/detail/form")
    public String formItem(String formType, Long idItemStore, Long idItemSection,  Model model) {
        model.addAttribute("formType", formType);
        model.addAttribute("idItemStore", idItemStore);
        model.addAttribute("idItemSection", idItemSection);
        model.addAttribute("idItemStoreList", pfItemService.listSection(idItemStore));
        return "pages/biz/training/item/itemForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_03_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/item/list")
    @ResponseBody
    public PageResult pageItems(ItemDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfItemService.pageItem(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_01_03_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/item/manage/list")
    @ResponseBody
    public PageResult pageItemManage(ItemDto dto) {
        return pfItemService.pageItemManage(dto);
    }

}
