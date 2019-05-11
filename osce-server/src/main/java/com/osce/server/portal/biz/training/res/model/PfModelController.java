package com.osce.server.portal.biz.training.res.model;

import com.osce.api.biz.training.structure.student.PfStudentService;
import com.osce.dto.biz.training.structure.student.StudentDto;
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
import java.util.ArrayList;

/**
 * @ClassName: PfModelController
 * @Description: 教学模型
 * @Author yangtongbin
 * @Date 2019-05-08
 */
@Controller
public class PfModelController extends BaseController {

    @Reference
    private PfStudentService pfStudentService;

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
    public String formDevice(String formType, Model model) {
        model.addAttribute("formType", formType);
        return "pages/biz/training/res/model/modelDeviceForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/model/list")
    @ResponseBody
    public PageResult listModel(StudentDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return PageResult.create(new ArrayList<>());
    }

}
