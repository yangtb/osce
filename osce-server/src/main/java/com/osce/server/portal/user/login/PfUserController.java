package com.osce.server.portal.user.login;

import com.osce.api.system.org.PfOrgService;
import com.osce.api.system.set.PfSetService;
import com.osce.api.user.login.PfUserService;
import com.osce.api.user.role.PfRoleService;
import com.osce.dto.user.PfUserDto;
import com.osce.entity.PfEmailSet;
import com.osce.entity.SysOrg;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import com.osce.server.security.SecurityContext;
import com.osce.server.security.User;
import com.osce.server.security.rsa.RsaKeyPairQueue;
import com.osce.server.utils.SysUserAuthUtils;
import com.osce.vo.user.role.PfRoleVo;
import com.sm.open.care.core.ResultObject;
import com.sm.open.care.core.enums.YesOrNo;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.ExcelUtils;
import com.sm.open.care.core.utils.ImportExcel;
import com.sm.open.care.core.utils.rsa.RsaKeyPair;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: PfUserController
 * @Description: 登陆
 * @Author yangtongbin
 * @Date 2018-12-26
 */
@Controller
@RequestMapping(value = "/pf/p/user")
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

    @RequestMapping("/register/page")
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
    @RequestMapping("/page")
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
    @RequestMapping("/modifyPass")
    public String modifyPass(Model model, HttpServletRequest request) {
        RsaKeyPair keyPair = rsaKeyPairQueue.getRsaKeyQueue(request);
        model.addAttribute(PUBLIC_KEY, keyPair.getPublicKey());
        return "pages/user/modifyPass";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER_MG','ROLE_SUPER')")
    @RequestMapping("/form")
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
    @RequestMapping("/resetPassword")
    public String resetPassword(Model model, HttpServletRequest request) {
        RsaKeyPair keyPair = rsaKeyPairQueue.getRsaKeyQueue(request);
        model.addAttribute(PUBLIC_KEY, keyPair.getPublicKey());
        return "pages/user/passReset";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER_MG','ROLE_SUPER')")
    @RequestMapping("/userDetail")
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
    @RequestMapping(value = "/list")
    @ResponseBody
    public PageResult listUsers(PfUserDto dto) {
        User user = CurrentUserUtils.getCurrentUser();
        if (!SysUserAuthUtils.isPlatOrSuper()) {
            dto.setIdOrg(user.getIdOrg());
        }
        dto.setUserId(user.getUserId());
        dto.setSuper(SysUserAuthUtils.isSuper());
        return ResultFactory.initPageResultWithSuccess(pfUserService.countUsers(dto),
                pfUserService.listUsers(dto));
    }

    @PreAuthorize("hasAnyRole('ROLE_USER_MG','ROLE_SUPER')")
    @RequestMapping("/download/userExcel")
    @ResponseBody
    public String downloadUserExcel(HttpServletResponse response, HttpServletRequest request) {
        List<List<String>> sheets = new ArrayList<>();
        String[] titles = {"姓名", "手机号", "邮箱", "真实姓名"};
        int[] size = {1000, 1000, 1000, 1000};
        String fileName = "用户注册批量导入表格模版";

        ExcelUtils.setFileDownloadHeader(response, request, fileName + ".xls");
        ExcelUtils.createXlsFile(response, titles, sheets, size);
        return null;
    }


    @PreAuthorize("hasAnyRole('ROLE_USER_MG','ROLE_SUPER')")
    @RequestMapping(value = "/upload/userExcel")
    @ResponseBody
    public ResultObject uploadUserExcel(HttpServletRequest request) {
        // 转型为MultipartHttpRequest：
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获得文件：
        MultipartFile file = multipartRequest.getFile("file");

        Assert.isTrue(!file.isEmpty(), "请选择要上传文件");

        InputStream is = null;
        try {
            is = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String originalFilename = file.getOriginalFilename();


        ImportExcel importExcel = new ImportExcel();
        List<Object> list = importExcel.importDataFromExcel(is, originalFilename);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }

        return ResultObject.createSuccess("/uploadFile", ResultObject.DATA_TYPE_OBJECT, null);
    }
}
