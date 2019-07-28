package com.osce.server.portal.user.login;

import com.osce.api.system.org.PfOrgService;
import com.osce.api.system.set.PfSetService;
import com.osce.api.user.login.PfUserService;
import com.osce.api.user.role.PfRoleService;
import com.osce.dto.user.PfUserDto;
import com.osce.entity.PfEmailSet;
import com.osce.entity.SysOrg;
import com.osce.result.PageResult;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import com.osce.server.security.SecurityContext;
import com.osce.server.security.User;
import com.osce.server.security.rsa.RsaKeyPairQueue;
import com.osce.server.utils.SysUserAuthUtils;
import com.osce.vo.user.role.PfRoleVo;
import com.sm.open.care.core.enums.YesOrNo;
import com.sm.open.care.core.utils.rsa.RsaKeyPair;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: PfUserController
 * @Description: 登陆
 * @Author yangtongbin
 * @Date 2018-12-26
 */
@Controller
public class PfUserController extends BaseController {

    @Reference
    private PfUserService pfUserService;

    @Reference
    private PfRoleService pfRoleService;

    @Reference
    private PfOrgService pfOrgService;

    @Reference
    private PfSetService pfSetService;

    @Resource(name = "rsaKeyPairQueue")
    private RsaKeyPairQueue rsaKeyPairQueue;

    @Value("${website.name}")
    private String websiteName;

    @Value("${website.copyright}")
    private String websiteCopyright;

    @Value("${website.approve}")
    private String websiteApprove;

    /**
     * rsa公钥常量变量名
     */
    private static final String PUBLIC_KEY = "publicKey";

    @RequestMapping("/pf/p/user/register/page")
    public String registerPage(Model model, HttpServletRequest request) {
        RsaKeyPair keyPair = rsaKeyPairQueue.getRsaKeyQueue(request);
        model.addAttribute(PUBLIC_KEY, keyPair.getPublicKey());
        model.addAttribute("websiteName", websiteName);

        PfEmailSet pfEmailSet = pfSetService.selectEmailSet();
        String sendSwitch = pfEmailSet == null || StringUtils.isBlank(pfEmailSet.getSendSwitch()) ? YesOrNo.NO.getCode() : pfEmailSet.getSendSwitch();
        model.addAttribute("sendSwitch", sendSwitch);
        return "pages/user/register/register";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER_MG','ROLE_SUPER')")
    @RequestMapping("/pf/p/user/page")
    public String page(Model model) {
        // 机构处理, 平台级别保留全部，平台以下级别只保留当前机构
        User user = CurrentUserUtils.getCurrentUser();
        if (SysUserAuthUtils.isPlatOrSuper()) {
            model.addAttribute("allOrg", pfOrgService.listAllOrg());
        } else {
            List<SysOrg> myOrgList = pfOrgService.listAllOrg().stream()
                    .filter(sysOrg -> sysOrg.getIdOrg().equals(user.getIdOrg())).collect(Collectors.toList());
            model.addAttribute("allOrg", myOrgList);
        }
        model.addAttribute("userOrgId", user.getIdOrg());
        return "pages/user/user";
    }

    @PreAuthorize("hasAnyRole('ROLE_MODIFY_PASS','ROLE_SUPER')")
    @RequestMapping("/pf/p/user/modifyPass")
    public String modifyPass(Model model, HttpServletRequest request) {
        RsaKeyPair keyPair = rsaKeyPairQueue.getRsaKeyQueue(request);
        model.addAttribute(PUBLIC_KEY, keyPair.getPublicKey());
        return "pages/user/modifyPass";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER_MG','ROLE_SUPER')")
    @RequestMapping("/pf/p/user/form")
    public String form(String formType, Long userId, Model model, HttpServletRequest request) {
        model.addAttribute("formType", formType);
        RsaKeyPair keyPair = rsaKeyPairQueue.getRsaKeyQueue(request);
        model.addAttribute(PUBLIC_KEY, keyPair.getPublicKey());
        // 角色处理
        if (SecurityContext.hasRole("ROLE_SUPER")) {
            if (StringUtils.equals(formType, "edit")) {
                model.addAttribute("roles", pfRoleService.listUserRole(userId));
            } else {
                model.addAttribute("roles", pfRoleService.list());
            }
        } else {
            List<PfRoleVo> roleVos;
            List<PfRoleVo> currentRoleVos = pfRoleService.listUserRole(CurrentUserUtils.getCurrentUserId());
            // 当前用户拥有最大角色权限
            int level = currentRoleVos.stream()
                    .filter(pfRoleVo -> pfRoleVo.isChecked())
                    .mapToInt(PfRoleVo::getLevel).min().getAsInt();

            if (StringUtils.equals(formType, "edit")) {
                roleVos = pfRoleService.listUserRole(userId).stream()
                        .filter(pfRoleVo -> pfRoleVo.getLevel() > level).collect(Collectors.toList());
                model.addAttribute("roles", roleVos);
            } else {
                roleVos = pfRoleService.listUserRole(CurrentUserUtils.getCurrentUserId())
                        .stream().filter(pfRoleVo -> pfRoleVo.getLevel() > level).collect(Collectors.toList());
                roleVos.forEach(pfRoleVo -> pfRoleVo.setChecked(false));
                model.addAttribute("roles", roleVos);
            }
        }

        // 机构处理
        User user = CurrentUserUtils.getCurrentUser();
        if (SysUserAuthUtils.isPlatOrSuper()) {
            model.addAttribute("allOrg", pfOrgService.listAllOrg());
        } else {
            List<SysOrg> myOrgList = pfOrgService.listAllOrg().stream()
                    .filter(sysOrg -> sysOrg.getIdOrg().equals(user.getIdOrg())).collect(Collectors.toList());
            model.addAttribute("allOrg", myOrgList);
        }
        model.addAttribute("userOrgId", user.getIdOrg());
        return "pages/user/userForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER_RESET_PSW','ROLE_SUPER')")
    @RequestMapping("/pf/p/user/resetPassword")
    public String resetPassword(Model model, HttpServletRequest request) {
        RsaKeyPair keyPair = rsaKeyPairQueue.getRsaKeyQueue(request);
        model.addAttribute(PUBLIC_KEY, keyPair.getPublicKey());
        return "pages/user/passReset";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER_MG','ROLE_SUPER')")
    @RequestMapping("/pf/p/user/userDetail")
    public String userDetail(Model model) {
        return "pages/user/userDetail";
    }


    /**
     * 获取用户列表
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_USER_MG','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/user/list")
    @ResponseBody
    public PageResult listUsers(PfUserDto dto) {
        User user = CurrentUserUtils.getCurrentUser();
        if (!SysUserAuthUtils.isPlatOrSuper()) {
            dto.setIdOrg(user.getIdOrg());
        }
        dto.setUserId(user.getUserId());
        dto.setSuper(SysUserAuthUtils.isSuper());
        return pfUserService.listUsers(dto);
    }


}
