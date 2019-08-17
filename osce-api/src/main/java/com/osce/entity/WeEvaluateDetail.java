package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: WeEvaluateDetail
 * @Description: 执行_评量明细
 * @Author yangtongbin
 * @Date 2019-08-17
 */
@Setter
@Getter
@ToString
public class WeEvaluateDetail implements Serializable {

    private static final long serialVersionUID = 1566052292679L;


    /**
     * 主键
     * 评量明细结果ID
     */
    private Long idWeEvaluateDetail;

    /**
     * 评量结果ID
     */
    private Long idWeEvaluate;

    /**
     * 明细评分项ID
     */
    private Long idCobEvaluateDetail;

    /**
     * 分值
     */
    private Integer score;

}
