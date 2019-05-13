package com.osce.orm.biz.training.res.room;

import com.osce.dto.biz.training.res.room.RoomDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.ErpRoom;
import com.osce.entity.ErpRoomDevice;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 是否存在该房间
     *
     * @param naRoom 房间号
     * @param idOrg  机构id
     * @return
     */
    boolean isExistRoom(@Param("naRoom") String naRoom,
                        @Param("idOrg") Long idOrg);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long addRoom(ErpRoom dto);

    /**
     * 编辑
     *
     * @param dto
     * @return
     */
    int editRoom(ErpRoom dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    int delRoom(PfBachChangeStatusDto dto);

    /**
     * 设备列表
     *
     * @param dto
     * @return
     */
    List<ErpRoomDevice> listDevices(RoomDto dto);

    /**
     * 设备总数
     *
     * @param dto
     * @return
     */
    Long countDevice(RoomDto dto);

    /**
     * 新增设备
     *
     * @param dto
     * @return
     */
    Long addDevice(ErpRoomDevice dto);

    /**
     * 编辑设备
     *
     * @param dto
     * @return
     */
    int editDevice(ErpRoomDevice dto);

    /**
     * 删除设备
     *
     * @param dto
     * @return
     */
    int delDevice(PfBachChangeStatusDto dto);
}
