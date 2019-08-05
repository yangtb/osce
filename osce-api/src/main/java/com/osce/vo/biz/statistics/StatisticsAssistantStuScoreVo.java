package com.osce.vo.biz.statistics;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: StatisticsStuScore1Vo
 * @Description: 学生成绩 - 理论考试成绩
 * @Author yangtongbin
 * @Date 2019-08-05
 */
@Setter
@Getter
@ToString
public class StatisticsAssistantStuScoreVo implements Serializable {

    private static final long serialVersionUID = 2304388015052439046L;

    /**
     * 评分表ID
     */
    private Long idScoreSheet;

    /**
     * 指标类型
     */
    private String naScoreItem;

    /**
     * 评分项分类
     */
    private String sdScoreItemCa;

    /**
     * 评分项内容
     */
    private String desScoreItem;

    /**
     * 得分结果
     */
    private Integer scoreResult;


}
