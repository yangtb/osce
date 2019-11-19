package com.osce.server.portal.biz.training.structure.sp;

import com.osce.api.biz.training.structure.sp.PfSpService;
import com.osce.dto.biz.training.structure.sp.SpDto;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import com.osce.server.security.rsa.RsaKeyPairQueue;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: PfSpController
 * @Description: sp管理
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@Controller
public class PfSpController extends BaseController {

    @Reference
    private PfSpService pfSpService;

    @Resource(name = "rsaKeyPairQueue")
    private RsaKeyPairQueue rsaKeyPairQueue;

    @PreAuthorize("hasAnyRole('ROLE_01_01_004','ROLE_SUPER')")
    @RequestMapping("/pf/p/sp/page")
    public String page(Model model, Integer showFlag) {
        if (showFlag == null) {
            showFlag = 1;
        }
        model.addAttribute("showFlag", showFlag);
        return "pages/biz/training/structure/sp/spPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_01_004','ROLE_SUPER')")
    @RequestMapping("/pf/p/sp/form")
    public String form(String formType, String userId, Model model, HttpServletRequest request) {
        model.addAttribute("formType", formType);
        model.addAttribute("userId", userId);
        model.addAttribute("idOrg", CurrentUserUtils.getCurrentUserIdOrg());
        model.addAttribute("allSpTag", pfSpService.listSpTag(CurrentUserUtils.getCurrentUserIdOrg()));
        return "pages/biz/training/structure/sp/spForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_01_004','ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/sp/list")
    @ResponseBody
    public PageResult listSp(SpDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfSpService.pageSp(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_01_01_004','ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/sp/cache/list")
    @ResponseBody
    public PageResult listSpCache(SpDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfSpService.pageSpCache(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_01_01_004','ROLE_SUPER')")
    @RequestMapping("/pf/p/sp/tag/form")
    public String tagForm() {
        return "pages/biz/training/structure/sp/spTagForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_01_004','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/sp/tag/list")
    @ResponseBody
    public PageResult listTag() {
        return ResultFactory.initPageResultWithSuccess(0L,
                pfSpService.listSpTag(CurrentUserUtils.getCurrentUserIdOrg()));
    }

}
