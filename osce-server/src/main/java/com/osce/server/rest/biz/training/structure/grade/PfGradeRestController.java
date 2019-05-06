package com.osce.server.rest.biz.training.structure.grade;

import com.osce.api.biz.training.structure.grade.PfGradeService;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.OrgGrade;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: PfGradeRestController
 * @Description: 学届管理
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@RestController
public class PfGradeRestController extends BaseController {

    @Reference
    private PfGradeService pfGradeService;

    /**
     * 新增学届信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_01_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/grade/add")
    public ResultObject addGrade(@RequestBody OrgGrade dto) {
        /* 参数校验 */
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("addGrade", ResultObject.DATA_TYPE_OBJECT,
                pfGradeService.addGrade(dto));
    }

    /**
     * 编辑学届信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/grade/edit")
    public ResultObject editGrade(@RequestBody OrgGrade dto) {
        /* 参数校验 */
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.createSuccess("editGrade", ResultObject.DATA_TYPE_OBJECT, pfGradeService.addGrade(dto));

    }

    /**
     * 删除学届信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/grade/del")
    public ResultObject delGrade(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfGradeService.delGrade(dto) ? ResultObject.createSuccess("delGrade", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delQuestion", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 停用、启用学届信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/grade/updateStatus")
    public ResultObject updateGradeStatus(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setOperationType(OperationTypeEnum.UPDATE_STATUS.getCode());
        dto.setExtId(CurrentUserUtils.getCurrentUserIdOrg());
        return pfGradeService.delGrade(dto) ? ResultObject.createSuccess("updateGradeStatus", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("updateGradeStatus", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

}
