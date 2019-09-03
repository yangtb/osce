package com.osce.orm.biz.show;

import com.osce.dto.biz.show.PfAioStationDto;
import com.osce.dto.biz.show.PfAioStuRegisterDto;
import com.osce.dto.biz.show.ShowDto;
import com.osce.vo.biz.show.*;

import java.util.List;

/**
 * @ClassName: PfShowDao
 * @Description: 显示屏
 * @Author yangtongbin
 * @Date 2019-06-30
 */
public interface PfShowDao {

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
     * 显示大屏总数
     *
     * @param dto
     * @return
     */
    Long countBigScreenDetail(ShowDto dto);

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
    void aioStudentRegister(PfAioStuRegisterDto dto);

    /**
     * 待考区- 已登记
     *
     * @param dto
     * @return
     */
    List<ShowAioRegisteredVo> listAioRegistered(ShowDto dto);

    /**
     * 已登记数目
     *
     * @param dto
     * @return
     */
    Integer countRegisterNum(ShowDto dto);

    /**
     * 学生数目
     *
     * @param dto
     * @return
     */
    Integer countStuTotalNum(ShowDto dto);

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
    List<ShowRoomStudentVo> listRoomStudent(PfAioStationDto dto);

    /**
     * 房间等待学员数
     *
     * @param dto
     * @return
     */
    Integer countRoomStudent(PfAioStationDto dto);

    /**
     * 获取入场序号
     *
     * @param dto
     * @return
     */
    String selectNoReg(PfAioStuRegisterDto dto);

}
