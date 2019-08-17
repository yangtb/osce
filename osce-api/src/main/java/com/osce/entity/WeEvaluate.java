package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: WeEvaluate
 * @Description: 执行_评量表
 * @Author yangtongbin
 * @Date 2019-08-17
 */
@Setter
@Getter
@ToString
public class WeEvaluate implements Serializable {

    private static final long serialVersionUID = 1566052290303L;

    /**
     * 主键
     * 评量结果ID
     */
    private Long idWeEvaluate;

    /**
     * 执行ID
     */
    private Long idExec;

    /**
     * 评分项ID
     */
    private Long idCobEvaluate;

    /**
     * 分值
     */
    private Integer score;

}
