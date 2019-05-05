package com.osce.server.portal.home;

import com.osce.api.home.PfHomeService;
import com.osce.dto.home.PfHomeDto;
import com.osce.entity.SysParam;
import com.osce.enums.SysParamEnum;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import com.osce.server.security.SecurityContext;
import com.osce.server.utils.ParamUtil;
import com.osce.vo.home.PfHomeVo;
import com.sm.open.care.core.enums.YesOrNo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @ClassName: PfHomeController
 * @Description: 主页
 * @Author yangtongbin
 * @Date 2017/9/4 15:20
 */
@Controller
public class PfHomeController extends BaseController {

    @Reference
    private PfHomeService pfHomeService;

    @Resource
    private ParamUtil paramUtil;

    /**
     * 网站名称
     */
    @Value("${website.name}")
    private String websiteName;

    /**
     * 版权信息
     */
    @Value("${website.copyright}")
    private String websiteCopyright;

    @Value("${website.approve}")
    private String websiteApprove;

    private static int ORG_EXPIRY_NOTICE_DEFAULT_DAY = 3;

    @PreAuthorize("isAnonymous() || isAuthenticated()")
    @RequestMapping("/index")
    public String index(Model model) {
        PfHomeDto homeDto = new PfHomeDto();
        homeDto.setSuper(SecurityContext.hasRole("ROLE_SUPER") ? true : false);
        homeDto.setAnonymousUser(SecurityContext.isAnonymousUser() ? true : false);
        homeDto.setUserId(SecurityContext.isAnonymousUser() ? null : CurrentUserUtils.getCurrentUserId());
        homeDto.setIdOrg(SecurityContext.isAnonymousUser() ? null : CurrentUserUtils.getCurrentUser().getIdOrg());

        // 游客开关判断
        if (SecurityContext.isAnonymousUser()) {
            SysParam sysParam = paramUtil.getParamInfo(SysParamEnum.VISITOR_SWITCH.getCode());
            if (sysParam == null || sysParam.getParamValue().equals(YesOrNo.NO.getCode())) {
                return "login";
            }
        }

        PfHomeVo pfHomeVo = pfHomeService.selectHomeInfo(homeDto);
        if (pfHomeVo == null) {
            pfHomeVo = new PfHomeVo();
        }
        pfHomeVo.setUsername(SecurityContext.isAnonymousUser() ?
                "匿名用户" : CurrentUserUtils.getCurrentUser().getUsername());
        pfHomeVo.setWebsiteName(websiteName);
        pfHomeVo.setWebsiteCopyright(websiteCopyright);
        model.addAttribute("homeInfo", pfHomeVo);
        model.addAttribute("showOrgPage", SecurityContext.hasRole("ROLE_OM"));
        model.addAttribute("showMessage", SecurityContext.isAnonymousUser() ? false : true);
        return "home/index";
    }

    @RequestMapping("/main")
    public String main(Model model) {
        model.addAttribute("websiteName", websiteName);
        return "pages/main";
    }

    @RequestMapping(value = {"/", "/logout/success"})
    public String homePage() {
        return "login";
    }
}
