package com.osce.server.rest.biz.training.skill;

import com.osce.api.biz.training.skill.PfSkillService;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.CobSkillCase;
import com.osce.entity.CobSkillDevice;
import com.osce.enums.OperationTypeEnum;
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
public class PfSkillRestController extends BaseController {

    @Reference
    private PfSkillService pfSkillService;

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_05','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/skill/add")
    public ResultObject addSkill(@RequestBody CobSkillCase dto) {
        /* 参数校验 */
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("addSkill", ResultObject.DATA_TYPE_OBJECT,
                pfSkillService.addSkill(dto));
    }

    /**
     * 编辑
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_05', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/skill/edit")
    public ResultObject editSkill(@RequestBody CobSkillCase dto) {
        /* 参数校验 */
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.createSuccess("editSkill", ResultObject.DATA_TYPE_OBJECT,
                pfSkillService.addSkill(dto));

    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_05','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/skill/del")
    public ResultObject delSkill(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfSkillService.delSkill(dto) ? ResultObject.createSuccess("delSkill", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delSkill", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 停用、启用
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_05','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/skill/updateStatus")
    public ResultObject updateSkillStatus(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setOperationType(OperationTypeEnum.UPDATE_STATUS.getCode());
        dto.setExtId(CurrentUserUtils.getCurrentUserIdOrg());
        return pfSkillService.delSkill(dto) ? ResultObject.createSuccess("updateSkillStatus", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("updateSkillStatus", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 新增设备
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_05','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/skill/device/add")
    public ResultObject addSkillDevice(@RequestBody CobSkillDevice dto) {
        /* 参数校验 */
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("addSkillDevice", ResultObject.DATA_TYPE_OBJECT,
                pfSkillService.addSkillDevice(dto));
    }

    /**
     * 编辑设备
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_05', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/skill/device/edit")
    public ResultObject editSkillDevice(@RequestBody CobSkillDevice dto) {
        /* 参数校验 */
        return ResultObject.createSuccess("editSkillDevice", ResultObject.DATA_TYPE_OBJECT,
                pfSkillService.addSkillDevice(dto));

    }

    /**
     * 删除设备
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_05','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/skill/device/del")
    public ResultObject delSkillDevice(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfSkillService.delSkillDevice(dto) ? ResultObject.createSuccess("delSkillDevice", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delSkillDevice", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }
    
}
