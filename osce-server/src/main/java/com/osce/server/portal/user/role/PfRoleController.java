package com.osce.server.portal.user.role;

import com.osce.api.user.role.PfRoleService;
import com.osce.dto.user.role.PfRoleDto;
import com.osce.result.PageResult;
import com.osce.server.portal.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: PfRoleRestController
 * @Description: 角色模块
 * @Author yangtongbin
 * @Date 2017/9/17 23:13
 */
@Controller
public class PfRoleController extends BaseController {

    @Reference
    private PfRoleService pfRoleService;

    @PreAuthorize("hasAnyRole('ROLE_ROLE_MG','ROLE_SUPER')")
    @RequestMapping("/pf/p/role/page")
    public String page() {
        return "pages/user/role/role";
    }

    @PreAuthorize("hasAnyRole('ROLE_ROLE_MG','ROLE_SUPER')")
    @RequestMapping("/pf/p/role/form")
    public String form(String formType, Model model) {
        model.addAttribute("formType", formType);
        return "pages/user/role/roleForm";
    }

    /**
     * 获取所有角色
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ROLE_MG','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/role/list")
    @ResponseBody
    public PageResult listRoles(PfRoleDto dto) {
        return pfRoleService.listRoles(dto);
    }

}
