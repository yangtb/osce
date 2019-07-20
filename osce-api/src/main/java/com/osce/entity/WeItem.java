package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: WeItem
 * @Description: 执行_理论题明细
 * @Author yangtongbin
 * @Date 2019-07-19
 */
@Setter
@Getter
@ToString
public class WeItem implements Serializable {

    private static final long serialVersionUID = 1563535169105L;


    /**
     * 主键
     * 理论题明细ID
     */
    private Long idWeItem;

    /**
     * 执行ID
     */
    private Long idExec;

    /**
     * 多个选项';'分割
     */
    private String cdIteStr;

    /**
     * 题目ID
     */
    private Long idItem;

    /**
     * 分值
     */
    private Integer score;

    /**
     * 所得分数
     */
    private Integer scoreResult;

}
