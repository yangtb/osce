package com.osce.orm.biz.statistics;

import com.osce.dto.biz.statistics.StatisticsDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.vo.biz.statistics.StatisticsTestResultVo;

import java.util.List;

/**
 * @ClassName: PfStatisticsDao
 * @Description: 统计
 * @Author yangtongbin
 * @Date 2019-07-20
 */
public interface PfStatisticsDao {

    /**
     * 实训记录列表
     *
     * @param dto
     * @return
     */
    List<StatisticsTestResultVo> listStatistics(StatisticsDto dto);

    /**
     * 实训记录总数
     *
     * @param dto
     * @return
     */
    Long countStatistics(StatisticsDto dto);

    /**
     * 删除实训记录
     *
     * @param dto
     * @return
     */
    int delTest(PfBachChangeStatusDto dto);

}
