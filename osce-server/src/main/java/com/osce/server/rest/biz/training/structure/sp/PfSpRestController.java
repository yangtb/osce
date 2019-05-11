package com.osce.server.rest.biz.training.structure.sp;

import com.osce.api.biz.training.structure.sp.PfSpService;
import com.osce.api.biz.training.structure.student.PfStudentService;
import com.osce.dto.biz.training.structure.student.StudentDepartDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.OrgSpDefine;
import com.osce.enums.OperationTypeEnum;
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
 * @ClassName: PfSpRestController
 * @Description: Sp管理
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@RestController
public class PfSpRestController {

    @Reference
    private PfStudentService pfStudentService;

    @Reference
    private PfSpService pfSpService;

    /**
     * 新增学届信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_01_004','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/sp/add")
    public ResultObject addStudent(@RequestBody StudentDepartDto dto) {
        /* 参数校验 */
        dto.getRegisterInfo().setOperator(CurrentUserUtils.getCurrentUsername());
        dto.getRegisterInfo().setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("addStudent", ResultObject.DATA_TYPE_OBJECT,
                pfStudentService.addStudent(dto));
    }

    /**
     * 编辑学届信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_01_004', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/sp/edit")
    public ResultObject editStudent(@RequestBody StudentDepartDto dto) {
        /* 参数校验 */
        dto.getRegisterInfo().setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.createSuccess("editStudent", ResultObject.DATA_TYPE_OBJECT,
                pfStudentService.addStudent(dto));

    }

    /**
     * 删除学届信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_01_004','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/sp/del")
    public ResultObject delStudent(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfStudentService.delStudent(dto) ? ResultObject.createSuccess("delStudent", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delQuestion", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 停用、启用学届信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_01_004','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/sp/updateStatus")
    public ResultObject updateStudentStatus(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setOperationType(OperationTypeEnum.UPDATE_STATUS.getCode());
        dto.setExtId(CurrentUserUtils.getCurrentUserIdOrg());
        return pfStudentService.delStudent(dto) ? ResultObject.createSuccess("updateStudentStatus", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("updateStudentStatus", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }


    /**
     * 新增标签
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_01_004','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/sp/tag/add")
    public ResultObject addSpTag(@RequestBody OrgSpDefine dto) {
        /* 参数校验 */
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("addSpTag", ResultObject.DATA_TYPE_OBJECT,
                pfSpService.addSpTag(dto));
    }

    /**
     * 删除标签
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_01_004','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/sp/tag/del")
    public ResultObject delSpTag(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfSpService.delSpTag(dto) ? ResultObject.createSuccess("delSpTag", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delSpTag", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

}
