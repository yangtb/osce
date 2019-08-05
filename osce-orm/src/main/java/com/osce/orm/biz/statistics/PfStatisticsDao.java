package com.osce.orm.biz.statistics;

import com.osce.dto.biz.statistics.StatisticsAssistantDto;
import com.osce.dto.biz.statistics.StatisticsDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.WeExec;
import com.osce.vo.biz.statistics.StatisticsAssistantStuScoreVo;
import com.osce.vo.biz.statistics.StatisticsStuScore1Vo;
import com.osce.vo.biz.statistics.StatisticsStuScoreVo;
import com.osce.vo.biz.statistics.StatisticsTestResultVo;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 学生成绩总数
     *
     * @param dto
     * @return
     */
    Long countStuScore(StatisticsDto dto);

    /**
     * 学生成绩
     *
     * @param dto
     * @return
     */
    List<StatisticsStuScoreVo> listStuScore(StatisticsDto dto);

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
    WeExec selectTestScore(@Param("idExec") Long idExec);

}
