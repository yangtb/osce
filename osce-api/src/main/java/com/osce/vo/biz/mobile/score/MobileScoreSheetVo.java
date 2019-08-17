package com.osce.vo.biz.mobile.score;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: MobileScoreSheetVo
 * @Description: 评分表（第一个sheet页面）
 * @Author yangtongbin
 * @Date 2019-08-17
 */
@Setter
@Getter
@ToString
public class MobileScoreSheetVo implements Serializable {

    private static final long serialVersionUID = 6163872166817838272L;

    /**
     * 评分表ID
     */
    private Long idScoreSheet;

    /**
     * 评分ID
     */
    private Long idScoreItem;

    /**
     * 评分项名称
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
     * 评分明细ID
     */
    private Long idWeScore;

    /**
     * 评分
     */
    private Integer scoreResult;


}
