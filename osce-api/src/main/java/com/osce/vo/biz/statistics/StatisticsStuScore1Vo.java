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
public class StatisticsStuScore1Vo implements Serializable {

    private static final long serialVersionUID = 1013439257397874866L;

    /**
     * 执行ID
     */
    private Long idWeItem;

    /**
     * 题干
     */
    private String mainItem;

    /**
     * 1 A1 2 A2 3 B1
     */
    private String sdItemCa;

    /**
     * 1 易 2 较易 3 中 4 难 5 较难
     */
    private String sdItemLevel;

    /**
     * 分值
     */
    private Integer score;

    /**
     * 得分结果
     */
    private Integer scoreResult;


}
