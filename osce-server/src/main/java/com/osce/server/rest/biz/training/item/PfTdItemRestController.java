package com.osce.server.rest.biz.training.item;

import com.osce.api.biz.training.item.PfTdItemService;
import com.osce.dto.biz.training.item.ItemBachDto;
import com.osce.dto.biz.training.item.ItemDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdItem;
import com.osce.entity.TdItemSection;
import com.osce.entity.TdItemStore;
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
 * @ClassName: PfTdItemRestController
 * @Description: 模板——题库管理
 * @Author yangtongbin
 * @Date 2019-08-06
 */
@RestController
public class PfTdItemRestController extends BaseController {

    @Reference
    private PfTdItemService pfTdItemService;

    /**
     * 新增信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/td/item/add")
    public ResultObject addItem(@RequestBody TdItemStore dto) {
        /* 参数校验 */
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("addItem", ResultObject.DATA_TYPE_OBJECT,
                pfTdItemService.addItem(dto));
    }

    /**
     * 编辑信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/td/item/edit")
    public ResultObject editItem(@RequestBody TdItemStore dto) {
        /* 参数校验 */
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.createSuccess("editItem", ResultObject.DATA_TYPE_OBJECT, pfTdItemService.addItem(dto));

    }

    /**
     * 删除信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/td/item/del")
    public ResultObject delItem(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfTdItemService.delItem(dto) ? ResultObject.createSuccess("delItem", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delQuestion", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 停用、启用信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/td/item/updateStatus")
    public ResultObject updateItemStatus(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setOperationType(OperationTypeEnum.UPDATE_STATUS.getCode());
        dto.setExtId(CurrentUserUtils.getCurrentUserIdOrg());
        return pfTdItemService.delItem(dto) ? ResultObject.createSuccess("updateItemStatus", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("updateItemStatus", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 目录list
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/td/item/section/list")
    public ResultObject listSection(@RequestBody ItemDto dto) {
        return ResultObject.createSuccess("listSection", ResultObject.DATA_TYPE_LIST,
                pfTdItemService.listSection(dto.getIdItemStore()));
    }

    /**
     * 新增目录
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/td/item/section/save")
    public ResultObject saveSection(@RequestBody TdItemSection dto) {
        return ResultObject.createSuccess("saveSection", ResultObject.DATA_TYPE_OBJECT,
                pfTdItemService.saveSection(dto));

    }

    /**
     * 删除目录
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/td/item/section/del")
    public ResultObject delSection(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfTdItemService.delSection(dto) ? ResultObject.createSuccess("delSection", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delSection", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 保存题目
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/td/item/exam/save")
    public ResultObject saveExam(@RequestBody TdItem dto) {
        return ResultObject.createSuccess("saveExam", ResultObject.DATA_TYPE_OBJECT,
                pfTdItemService.saveExam(dto));

    }

    /**
     * 删除题目
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/td/item/exam/del")
    public ResultObject delExam(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfTdItemService.delExam(dto) ? ResultObject.createSuccess("delExam", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delExam", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 目录list
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/td/item/option/list")
    public ResultObject listItemOption(@RequestBody ItemDto dto) {
        return ResultObject.createSuccess("listItemOption", ResultObject.DATA_TYPE_LIST,
                pfTdItemService.listItemOption(dto));
    }

    /**
     * 删除题目
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/td/item/option/del")
    public ResultObject delOption(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfTdItemService.delOption(dto) ? ResultObject.createSuccess("delOption", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delOption", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 新增信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/td/item/batch/add")
    public ResultObject addBachItem(@RequestBody ItemBachDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getItems()), "items");

        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("addBachItem", ResultObject.DATA_TYPE_LIST,
                pfTdItemService.addBachItem(dto));
    }


}
