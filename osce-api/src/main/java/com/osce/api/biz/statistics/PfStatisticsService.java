package com.osce.api.biz.statistics;

import com.osce.dto.biz.statistics.StatisticsDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.result.PageResult;

/**
 * @ClassName: PfShowService
 * @Description: 显示屏
 * @Author yangtongbin
 * @Date 2019-06-30
 */
public interface PfStatisticsService {

    /**
     * 实训记录
     *
     * @param dto
     * @return
     */
    PageResult pageTests(StatisticsDto dto);

    /**
     * 删除实训记录
     *
     * @param dto
     * @return
     */
    boolean delTest(PfBachChangeStatusDto dto);

    /**
     * 学生成绩
     *
     * @param dto
     * @return
     */
    PageResult pageTestScore(StatisticsDto dto);

}
