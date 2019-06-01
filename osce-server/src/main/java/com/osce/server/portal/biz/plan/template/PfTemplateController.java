package com.osce.server.portal.biz.plan.template;

import com.osce.api.biz.plan.template.PfTemplateService;
import com.osce.dto.biz.plan.template.TemplateDto;
import com.osce.dto.biz.training.caseku.CaseDto;
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
 * @ClassName: PfTemplateController
 * @Description: 实训模板管理
 * @Author yangtongbin
 * @Date 2019-05-17
 */
@Controller
public class PfTemplateController extends BaseController {

    @Reference
    private PfTemplateService pfTemplateService;

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/template/page")
    public String page(Model model) {
        return "pages/biz/plan/template/template";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/plan/template/form")
    public String pageForm(Model model) {
        return "pages/biz/plan/template/templateForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/plan/template/list")
    @ResponseBody
    public PageResult pageTemplate(TemplateDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfTemplateService.pageTemplate(dto);
    }

}
