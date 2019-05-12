package com.osce.server.rest.biz.training.res.room;

import com.osce.api.biz.training.res.room.PfRoomService;
import com.osce.dto.common.PfBachChangeStatusDto;
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
 * @ClassName: PfRoomRestController
 * @Description: 房间管理
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@RestController
public class PfRoomRestController extends BaseController {

    @Reference
    private PfRoomService pfRoomService;

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/room/add")
    public ResultObject addRoom(@RequestBody ErpRoom dto) {
        /* 参数校验 */
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("addRoom", ResultObject.DATA_TYPE_OBJECT,
                pfRoomService.addRoom(dto));
    }

    /**
     * 编辑
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_02_002', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/room/edit")
    public ResultObject editRoom(@RequestBody ErpRoom dto) {
        /* 参数校验 */
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.createSuccess("editRoom", ResultObject.DATA_TYPE_OBJECT, pfRoomService.addRoom(dto));

    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/room/del")
    public ResultObject delRoom(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfRoomService.delRoom(dto) ? ResultObject.createSuccess("delRoom", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delQuestion", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 停用、启用
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/room/updateStatus")
    public ResultObject updateRoomStatus(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setOperationType(OperationTypeEnum.UPDATE_STATUS.getCode());
        dto.setExtId(CurrentUserUtils.getCurrentUserIdOrg());
        return pfRoomService.delRoom(dto) ? ResultObject.createSuccess("updateRoomStatus", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("updateRoomStatus", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 新增设备
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/device/add")
    public ResultObject addDevice(@RequestBody ErpRoom dto) {
        /* 参数校验 */
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("addDevice", ResultObject.DATA_TYPE_OBJECT,
                pfRoomService.addRoom(dto));
    }

    /**
     * 编辑设备
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_02_002', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/device/edit")
    public ResultObject editDevice(@RequestBody ErpRoom dto) {
        /* 参数校验 */
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.createSuccess("editDevice", ResultObject.DATA_TYPE_OBJECT, pfRoomService.addRoom(dto));

    }

    /**
     * 删除设备
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_02_002','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/device/del")
    public ResultObject delDevice(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfRoomService.delRoom(dto) ? ResultObject.createSuccess("delDevice", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delDevice", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

}
