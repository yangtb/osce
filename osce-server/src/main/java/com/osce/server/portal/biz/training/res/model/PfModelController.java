package com.osce.server.portal.biz.training.res.model;

import com.osce.api.biz.training.res.model.PfModelService;
import com.osce.dto.biz.training.res.model.ModelDto;
import com.osce.enums.SysDicGroupEnum;
import com.osce.result.PageResult;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import com.osce.server.utils.EnumUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @ClassName: PfModelController
 * @Description: 教学模型
 * @Author yangtongbin
 * @Date 2019-05-08
 */
@Controller
public class PfModelController extends BaseController {

    @Reference
    private PfModelService pfModelService;

    @Resource
    private EnumUtil enumUtil;

    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_SUPER')")
    @RequestMapping("/pf/p/model/page")
    public String page(Model model) {
        return "pages/biz/training/res/model/modelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_SUPER')")
    @RequestMapping("/pf/p/model/form")
    public String form(String formType, Model model) {
        model.addAttribute("formType", formType);
        model.addAttribute("deviceUnitList", enumUtil.getEnumList(SysDicGroupEnum.SD_DEVICE_UNIT.getCode()));
        return "pages/biz/training/res/model/modelTagForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_SUPER')")
    @RequestMapping("/pf/p/model/device/form")
    public String formDevice(Long idDevice, Model model) {
        model.addAttribute("idDevice", idDevice);
        return "pages/biz/training/res/model/modelDeviceForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_01_05','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/model/list")
    @ResponseBody
    public PageResult listModel(ModelDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfModelService.pageModels(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/model/device/list")
    @ResponseBody
    public PageResult pageModelDevice(ModelDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfModelService.pageModelDevice(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_SUPER')")
    @RequestMapping("/pf/p/model/device/fault/form")
    public String formDeviceFault(Long idDeviceCase, Model model) {
        model.addAttribute("idDeviceCase", idDeviceCase);
        return "pages/biz/training/res/model/faultForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_SUPER')")
    @RequestMapping("/pf/p/model/device/repair/form")
    public String formDeviceRepair(Long idDeviceCase, Model model) {
        model.addAttribute("idDeviceCase", idDeviceCase);
        return "pages/biz/training/res/model/repairForm";
    }

}
