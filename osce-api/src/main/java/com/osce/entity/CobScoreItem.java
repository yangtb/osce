package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CobScoreItem
 * @Description: CobScoreItem
 * @Author yangtongbin
 * @Date 2019-05-15
 */
@Setter
@Getter
@ToString
public class CobScoreItem implements Serializable {

    private static final long serialVersionUID = 1557887017859L;


    /**
     * 主键
     * 评分项ID
     */
    private Long idScoreItem;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 评分表ID
     */
    private Long idScoreSheet;

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
     * 0 未激活 1 已激活
     */
    private String fgActive;

    /**
     * 0 正常 1 删除
     */
    private String fgValid;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建人员
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改人员
     */
    private String operator;

    /**
     * 修改时间
     */
    private Date gmtModify;


}
