package com.osce.orm.biz.training.res.room;

import com.osce.dto.biz.training.res.room.RoomDto;
import com.osce.entity.ErpRoom;

import java.util.List;

/**
 * @ClassName: PfRoomDao
 * @Description: 房间管理
 * @Author yangtongbin
 * @Date 2019-05-10
 */
public interface PfRoomDao {

    /**
     * 房间列表
     *
     * @param dto
     * @return
     */
    List<ErpRoom> listRooms(RoomDto dto);

    /**
     * 房间总数
     *
     * @param dto
     * @return
     */
    Long countRoom(RoomDto dto);
}
