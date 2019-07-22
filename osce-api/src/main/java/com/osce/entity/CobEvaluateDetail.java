package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: CobEvaluateDetail
 * @Description: 病例_评量明细
 * @Author yangtongbin
 * @Date 2019-07-22
 */
@Setter
@Getter
@ToString
public class CobEvaluateDetail implements Serializable {

    private static final long serialVersionUID = 1563762927968L;

    /**
     * 主键
     * 明细评分项ID
     */
    private Long idCobEvaluateDetail;

    /**
     * 评分项ID
     */
    private Long idCobEvaluate;

    /**
     * 明细评分项名称
     */
    private String naCobEvaluateDetail;

    /**
     * 排序
     */
    private Integer sort;

}
