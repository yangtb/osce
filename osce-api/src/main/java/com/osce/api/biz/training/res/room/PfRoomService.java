package com.osce.api.biz.training.res.room;

import com.osce.dto.biz.training.res.room.RoomDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.ErpRoom;
import com.osce.entity.ErpRoomDevice;
import com.osce.result.PageResult;

import java.util.List;

/**
 * @ClassName: PfRoomService
 * @Description: 房间管理
 * @Author yangtongbin
 * @Date 2019-05-10
 */
public interface PfRoomService {

    /**
     * 房间列表
     *
     * @param dto
     * @return
     */
    PageResult<ErpRoom> pageRooms(RoomDto dto);

    /**
     * 增加
     *
     * @param dto
     * @return
     */
    Long addRoom(ErpRoom dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    boolean delRoom(PfBachChangeStatusDto dto);

    /**
     * 设备列表
     *
     * @param dto
     * @return
     */
    PageResult pageDevices(RoomDto dto);

    /**
     * 增加设备
     *
     * @param dto
     * @return
     */
    Long addRoomDevice(ErpRoomDevice dto);

    /**
     * 删除设备
     *
     * @param dto
     * @return
     */
    boolean delRoomDevice(PfBachChangeStatusDto dto);

    /**
     * 获取当前机构下所有房间号
     *
     * @param idOrg
     * @return
     */
    List<ErpRoom> listAllRooms(Long idOrg);

}
