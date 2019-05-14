package com.osce.server.rest.biz.training.item;

import com.osce.api.biz.training.item.PfItemService;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.IbmItemStore;
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
 * @ClassName: PfItemRestController
 * @Description: 题库管理
 * @Author yangtongbin
 * @Date 2019-05-14
 */
@RestController
public class PfItemRestController extends BaseController {

    @Reference
    private PfItemService pfItemService;

    /**
     * 新增信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_03_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/item/add")
    public ResultObject addItem(@RequestBody IbmItemStore dto) {
        /* 参数校验 */
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("addItem", ResultObject.DATA_TYPE_OBJECT,
                pfItemService.addItem(dto));
    }

    /**
     * 编辑信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_03_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/item/edit")
    public ResultObject editItem(@RequestBody IbmItemStore dto) {
        /* 参数校验 */
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.createSuccess("editItem", ResultObject.DATA_TYPE_OBJECT, pfItemService.addItem(dto));

    }

    /**
     * 删除信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_03_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/item/del")
    public ResultObject delItem(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfItemService.delItem(dto) ? ResultObject.createSuccess("delItem", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delQuestion", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 停用、启用信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_03_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/item/updateStatus")
    public ResultObject updateItemStatus(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setOperationType(OperationTypeEnum.UPDATE_STATUS.getCode());
        dto.setExtId(CurrentUserUtils.getCurrentUserIdOrg());
        return pfItemService.delItem(dto) ? ResultObject.createSuccess("updateItemStatus", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("updateItemStatus", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

}
