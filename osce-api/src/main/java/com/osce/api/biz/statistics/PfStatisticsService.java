package com.osce.api.biz.statistics;

import com.osce.dto.biz.statistics.StatisticsAssistantDto;
import com.osce.dto.biz.statistics.StatisticsDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.WeExec;
import com.osce.result.PageResult;
import com.osce.vo.biz.statistics.StatisticsAssistantStuScoreVo;
import com.osce.vo.biz.statistics.StatisticsStuScore1Vo;

import java.util.List;

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

    /**
     * 理论试题得分明细
     *
     * @param dto
     * @return
     */
    List<StatisticsStuScore1Vo> listStuScore1(StatisticsDto dto);

    /**
     * 考官评分明细
     *
     * @param dto
     * @return
     */
    List<StatisticsAssistantStuScoreVo> listAssistantStuScore(StatisticsAssistantDto dto);

    /**
     * 查询学生成绩
     * @param idExec 执行id
     * @return
     */
    WeExec selectTestScore(Long idExec);
}
