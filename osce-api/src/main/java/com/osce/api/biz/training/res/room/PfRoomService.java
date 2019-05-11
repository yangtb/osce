package com.osce.api.biz.training.res.room;

import com.osce.dto.biz.training.res.room.RoomDto;
import com.osce.result.PageResult;

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
    PageResult pageRooms(RoomDto dto);

}
