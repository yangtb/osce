package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: WeScore
 * @Description: 执行_评分明细
 * @Author yangtongbin
 * @Date 2019-08-17
 */
@Setter
@Getter
@ToString
public class WeScore implements Serializable {

    private static final long serialVersionUID = -6494326409916041576L;

    /**
     * 主键
     * 评分明细ID
     */
    private Long idWeScore;

    /**
     * 执行ID
     */
    private Long idExec;

    /**
     * 1 主考官
     * 2 考官
     * 3 中控考官
     */
    private String cdAssistantCa;

    /**
     * 考官ID
     */
    private Long idUser;

    /**
     * 评分表ID
     */
    private Long idScoreSheet;

    /**
     * 评分项ID
     */
    private Long idScoreItem;

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
     * 评分项解析
     */
    private String scoreItemAnalysis;

    /**
     * 参考问题
     */
    private String refQuestion;

    /**
     * 分值
     */
    private Integer score;

    /**
     * 所得分数
     */
    private Integer scoreResult;


}
