package com.osce.orm.biz.monitor;

import com.osce.dto.biz.monitor.MonitorDto;
import com.osce.dto.biz.training.caseku.CaseDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.CobScoreItem;
import com.osce.entity.TdScoreItem;
import com.osce.vo.biz.monitor.MonitorAreaDetailVo;
import com.osce.vo.biz.monitor.MonitorAreaVo;
import com.osce.vo.biz.monitor.MonitorRoomDevice;
import com.osce.vo.biz.monitor.MonitorStuVo;
import com.osce.vo.biz.show.ShowAioMainVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: PfAreaMonitorDao
 * @Description: 监控
 * @Author yangtongbin
 * @Date 2019-07-20
 */
public interface PfAreaMonitorDao {

    /**
     * 查出当天计划、考场、时段
     *
     * @param dto
     * @return
     */
    List<ShowAioMainVo> listCurrentPlan(MonitorDto dto);

    /**
     * 考场监控
     *
     * @param idPlan
     * @param idArea
     * @param timeSection
     * @return
     */
    List<MonitorAreaVo> listMonitorArea(@Param("idPlan") Long idPlan,
                                        @Param("idArea") Long idArea,
                                        @Param("timeSection") float timeSection);

    /**
     * 考场监控 - 站点详情
     *
     * @param dto
     * @return
     */
    MonitorAreaDetailVo selectMonitorAreaDetail(MonitorDto dto);

    /**
     * 待考学员
     *
     * @param idPlan
     * @param idArea
     * @param timeSection
     * @return
     */
    List<MonitorStuVo> listToBeExaminedStu(@Param("idPlan") Long idPlan,
                                           @Param("idArea") Long idArea,
                                           @Param("timeSection") float timeSection);

    /**
     * 场内学员
     *
     * @param idPlan
     * @param idArea
     * @param timeSection
     * @return
     */
    List<MonitorStuVo> listOnSiteStu(@Param("idPlan") Long idPlan,
                                     @Param("idArea") Long idArea,
                                     @Param("timeSection") float timeSection);

    /**
     * 结束学员
     *
     * @param idPlan
     * @param idArea
     * @param timeSection
     * @return
     */
    List<MonitorStuVo> listEndStu(@Param("idPlan") Long idPlan,
                                  @Param("idArea") Long idArea,
                                  @Param("timeSection") float timeSection);

    /**
     * 删除学员
     *
     * @param dto
     * @return
     */
    int delAreaStu(PfBachChangeStatusDto dto);

    /**
     * 恢复考试
     *
     * @param dto
     * @return
     */
    int recoveryTest(PfBachChangeStatusDto dto);

    /**
     * 删除执行记录
     *
     * @param dto
     * @return
     */
    int delExecRecord(PfBachChangeStatusDto dto);

    /**
     * 评分项列表
     *
     * @param dto
     * @return
     */
    List<TdScoreItem> listItem(CaseDto dto);

    /**
     * 考场监控 - 监控设备列表
     *
     * @param idInsStation 排站ID
     * @return
     */
    List<MonitorRoomDevice> listRoomDevice(@Param("idInsStation") Long idInsStation);

}
