package com.osce.service.biz.training.res.room;

import com.osce.api.biz.training.res.room.PfRoomService;
import com.osce.dto.biz.training.res.room.RoomDto;
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

}
