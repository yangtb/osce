package com.osce.server.portal.user.menu;

import com.osce.api.user.menu.PfMenuService;
import com.osce.dto.user.menu.MenuDto;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.server.portal.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @ClassName: PfMenuRestController
 * @Description: 菜单
 * @Author yangtongbin
 * @Date 2017/9/8 09:57
 */
@Controller
public class PfMenuController extends BaseController {

    @Reference
    private PfMenuService pfMenuService;

    @PreAuthorize("hasAnyRole('ROLE_MENU_MG','ROLE_SUPER')")
    @RequestMapping("/pf/p/menu/page")
    public String menu() {
        return "pages/user/menu/menu";
    }

    @PreAuthorize("hasAnyRole('ROLE_MENU_MG','ROLE_SUPER')")
    @RequestMapping("/pf/p/menu/form")
    public String form(String formType, Model model) {
        model.addAttribute("formType", formType);
        return "pages/user/menu/menuForm";
    }

    /**
     * 获取系统菜单
     */
    @PreAuthorize("hasAnyRole('ROLE_MENU_MG','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/menu/list")
    @ResponseBody
    public PageResult listMenus(MenuDto dto) {
        return ResultFactory.initPageResultWithSuccess(pfMenuService.countMenus(dto),
                pfMenuService.listMenus(dto));
    }

}
