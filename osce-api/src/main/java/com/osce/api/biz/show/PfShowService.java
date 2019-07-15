package com.osce.api.biz.show;

import com.osce.dto.biz.show.PfAioStationDto;
import com.osce.dto.biz.show.PfAioStuRegisterDto;
import com.osce.dto.biz.show.ShowDto;
import com.osce.vo.biz.show.*;

import java.util.List;

/**
 * @ClassName: PfShowService
 * @Description: 显示屏
 * @Author yangtongbin
 * @Date 2019-06-30
 */
public interface PfShowService {

    /**
     * 显示大屏main
     *
     * @param dto
     * @return
     */
    ShowBigScreenMainVo selectBigScreenMain(ShowDto dto);

    /**
     * 显示大屏detail
     *
     * @param dto
     * @return
     */
    List<ShowBigScreenDetailVo> selectBigScreenDetail(ShowDto dto);

    /**
     * 待考区一体机首页
     *
     * @param dto
     * @return
     */
    ShowAioMainVo selectAioMain(ShowDto dto);

    /**
     * 待考区一体机首页 - 列表
     *
     * @param dto
     * @return
     */
    List<ShowAioMainVo> listAioMain(ShowDto dto);

    /**
     * 待考登记
     *
     * @param dto
     * @return
     */
    ShowStuVo aioStudentRegister(PfAioStuRegisterDto dto);

    /**
     * 学员登记数目
     *
     * @param dto
     * @return
     */
    ShowStuVo countAioStuRegisterNum(ShowDto dto);

    /**
     * 待考区- 已登记
     *
     * @param dto
     * @return
     */
    List<ShowAioRegisteredVo> listAioRegistered(ShowDto dto);

    /**
     * 学员待考队列
     *
     * @param dto
     * @return
     */
    List<ShowAioExecQueueVo> listAioExecQueue(ShowDto dto);

    /**
     * 查询房间的考试信息
     *
     * @param dto
     * @return
     */
    ShowStationVo selectStationRoomInfo(PfAioStationDto dto);

    /**
     * 房间学员列表
     *
     * @param dto
     * @return
     */
    ShowStationRightVo listRoomStudent(PfAioStationDto dto);

}
