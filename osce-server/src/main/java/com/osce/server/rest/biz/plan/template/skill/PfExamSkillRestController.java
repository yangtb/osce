package com.osce.server.rest.biz.plan.template.skill;

import com.osce.api.biz.plan.template.skill.PfExamSkillService;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdSkillDevice;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import com.sm.open.care.core.ErrorCode;
import com.sm.open.care.core.ErrorMessage;
import com.sm.open.care.core.ResultObject;
import com.sm.open.care.core.utils.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: PfSkillRestController
 * @Description: 技能病例库
 * @Author yangtongbin
 * @Date 2019-05-15
 */
@RestController
public class PfExamSkillRestController extends BaseController {

    @Reference
    private PfExamSkillService pfExamSkillService;

    /**
     * 新增设备
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/exam/skill/device/add")
    public ResultObject addSkillDevice(@RequestBody TdSkillDevice dto) {
        /* 参数校验 */
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("addSkillDevice", ResultObject.DATA_TYPE_OBJECT,
                pfExamSkillService.addSkillDevice(dto));
    }

    /**
     * 编辑设备
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/exam/skill/device/edit")
    public ResultObject editSkillDevice(@RequestBody TdSkillDevice dto) {
        /* 参数校验 */
        return ResultObject.createSuccess("editSkillDevice", ResultObject.DATA_TYPE_OBJECT,
                pfExamSkillService.addSkillDevice(dto));

    }

    /**
     * 删除设备
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/exam/skill/device/del")
    public ResultObject delSkillDevice(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfExamSkillService.delSkillDevice(dto) ? ResultObject.createSuccess("delSkillDevice", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delSkillDevice", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }
    
}
