package com.osce.service.biz.training.res.room;

import com.osce.api.biz.training.res.room.PfRoomService;
import com.osce.dto.biz.training.res.room.RoomDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.ErpRoom;
import com.osce.entity.ErpRoomDevice;
import com.osce.exception.RestErrorCode;
import com.osce.exception.RestException;
import com.osce.orm.biz.training.res.room.PfRoomDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @ClassName: PfRoomServiceImpl
 * @Description: 房间管理实现
 * @Author yangtongbin
 * @Date 2019-05-10
 */
@Service
public class PfRoomServiceImpl implements PfRoomService {

    @Resource
    private PfRoomDao pfRoomDao;

    @Override
    public PageResult pageRooms(RoomDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfRoomDao.countRoom(dto),
                pfRoomDao.listRooms(dto));
    }

    @Override
    public Long addRoom(ErpRoom dto) {
        if (dto.getIdRoom() == null) {
            //部门下如果有学员，则不允许删除
            if (pfRoomDao.isExistRoom(dto.getNaRoom(), dto.getIdOrg())) {
                throw new RestException(RestErrorCode.ROOM_IS_EXIST);
            }
            pfRoomDao.addRoom(dto);
        } else {
            pfRoomDao.editRoom(dto);
        }
        return dto.getIdRoom();
    }

    @Override
    public boolean delRoom(PfBachChangeStatusDto dto) {
        int num = pfRoomDao.delRoom(dto);
        return num >= 1 ? true : false;
    }

    @Override
    public PageResult pageDevices(RoomDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfRoomDao.countDevice(dto),
                pfRoomDao.listDevices(dto));
    }

    @Override
    public Long addRoomDevice(ErpRoomDevice dto) {
        if (dto.getIdRoomDevice() == null) {
            pfRoomDao.addDevice(dto);
        } else {
            pfRoomDao.editDevice(dto);
        }
        return dto.getIdRoomDevice();
    }

    @Override
    public boolean delRoomDevice(PfBachChangeStatusDto dto) {
        int num = pfRoomDao.delDevice(dto);
        return num >= 1 ? true : false;
    }

}
