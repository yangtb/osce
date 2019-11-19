package com.osce.server.portal.biz.training.item;

import com.osce.api.biz.training.item.PfTdItemService;
import com.osce.dto.biz.training.item.ItemDto;
import com.osce.result.PageResult;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: PfTdItemController
 * @Description: 模板_题集管理
 * @Author yangtongbin
 * @Date 2019-08-06
 */
@Controller
public class PfTdItemController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(PfTdItemController.class);

    @Reference
    private PfTdItemService pfTdItemService;

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/td/item/page")
    public String page(String idItemStore, Model model) {
        model.addAttribute("idItemStore", idItemStore);
        return "pages/biz/training/item/td/itemPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/td/item/form")
    public String form(String formType, Model model) {
        model.addAttribute("formType", formType);
        return "pages/biz/training/item/td/itemTagForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/td/item/manage/page")
    public String pageDevice(String idItemStore, Model model) {
        model.addAttribute("idItemStore", idItemStore);
        return "pages/biz/training/item/td/itemManagePage";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/td/item/section/form")
    public String formSection(String idItemStore, Model model) {
        model.addAttribute("idItemStore", idItemStore);
        return "pages/biz/training/item/td/sectionForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/td/item/detail/form")
    public String formItem(String formType, String idItemStore, String idItemSection, Model model) {
        model.addAttribute("formType", formType);
        model.addAttribute("idItemStore", idItemStore);
        model.addAttribute("idItemSection", idItemSection);
        model.addAttribute("idItemStoreList", pfTdItemService.listSection(Long.valueOf(idItemStore)));
        return "pages/biz/training/item/td/itemForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/td/item/list")
    @ResponseBody
    public PageResult pageItems(ItemDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfTdItemService.pageItem(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/td/item/manage/list")
    @ResponseBody
    public PageResult pageItemManage(ItemDto dto) {
        return pfTdItemService.pageItemManage(dto);
    }


}
