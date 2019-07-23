package com.osce.orm.biz.monitor;

import com.osce.dto.biz.monitor.MonitorDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.vo.biz.monitor.MonitorStuVo;

import java.util.List;

/**
 * @ClassName: PfAreaMonitorDao
 * @Description: 监控
 * @Author yangtongbin
 * @Date 2019-07-20
 */
public interface PfAreaMonitorDao {

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
    int delAreaStu(PfBachChangeStatusDto dto);

    /**
     * 恢复考试
     *
     * @param dto
     * @return
     */
    int recoveryTest(PfBachChangeStatusDto dto);
}
