package com.osce.api.biz.monitor;

import com.osce.dto.biz.monitor.MonitorDto;
import com.osce.dto.biz.training.caseku.CaseDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdScoreItem;
import com.osce.vo.biz.monitor.MonitorAreaDetailVo;
import com.osce.vo.biz.monitor.MonitorAreaVo;
import com.osce.vo.biz.monitor.MonitorStuVo;

import java.util.List;

/**
 * @ClassName: PfAreaMonitorService
 * @Description: PfAreaMonitorService
 * @Author yangtongbin
 * @Date 2019-07-20
 */
public interface PfAreaMonitorService {

    /**
     * 考场监控
     *
     * @param dto
     * @return
     */
    List<MonitorAreaVo> listMonitorArea(MonitorDto dto);

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
     * @param dto
     * @return
     */
    List<MonitorStuVo> listToBeExaminedStu(MonitorDto dto);

    /**
     * 场内学员
     *
     * @param dto
     * @return
     */
    List<MonitorStuVo> listOnSiteStu(MonitorDto dto);

    /**
     * 结束学员
     *
     * @param dto
     * @return
     */
    List<MonitorStuVo> listEndStu(MonitorDto dto);

    /**
     * 删除学员
     *
     * @param dto
     * @return
     */
    boolean delAreaStu(PfBachChangeStatusDto dto);

    /**
     * 恢复考试
     *
     * @param dto
     * @return
     */
    boolean recoveryTest(PfBachChangeStatusDto dto);

    /**
     * 评分项列表
     *
     * @param dto
     * @return
     */
    List<TdScoreItem> pageItem(CaseDto dto);
}
