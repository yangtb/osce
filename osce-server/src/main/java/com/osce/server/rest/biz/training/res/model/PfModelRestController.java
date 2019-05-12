package com.osce.server.rest.biz.training.res.model;

import com.osce.api.biz.training.res.model.PfModelService;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.ErpDevice;
import com.osce.entity.ErpDeviceCase;
import com.osce.entity.ErpRoom;
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
 * @ClassName: PfModelRestController
 * @Description: 教学模型管理
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@RestController
public class PfModelRestController extends BaseController {

    @Reference
    private PfModelService pfModelService;

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/model/add")
    public ResultObject addModel(@RequestBody ErpDevice dto) {
        /* 参数校验 */
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("addModel", ResultObject.DATA_TYPE_OBJECT,
                pfModelService.addModel(dto));
    }

    /**
     * 编辑
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_02_002', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/model/edit")
    public ResultObject editModel(@RequestBody ErpDevice dto) {
        /* 参数校验 */
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.createSuccess("editModel", ResultObject.DATA_TYPE_OBJECT, pfModelService.addModel(dto));

    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/model/del")
    public ResultObject delModel(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfModelService.delModel(dto) ? ResultObject.createSuccess("delModel", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delQuestion", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 停用、启用
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/model/updateStatus")
    public ResultObject updateModelStatus(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setOperationType(OperationTypeEnum.UPDATE_STATUS.getCode());
        dto.setExtId(CurrentUserUtils.getCurrentUserIdOrg());
        return pfModelService.delModel(dto) ? ResultObject.createSuccess("updateModelStatus", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("updateModelStatus", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 新增设备
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/model/device/save")
    public ResultObject addModelDevice(@RequestBody ErpDeviceCase dto) {
        /* 参数校验 */
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("addModelDevice", ResultObject.DATA_TYPE_OBJECT,
                pfModelService.addModelDevice(dto));
    }


    /**
     * 删除设备
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/model/device/del")
    public ResultObject delDevice(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfModelService.delModelDevice(dto) ? ResultObject.createSuccess("delModelDevice", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delModelDevice", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }


}
