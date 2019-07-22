package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: CobEvaluate
 * @Description: 病例_评量表
 * @Author yangtongbin
 * @Date 2019-07-22
 */
@Setter
@Getter
@ToString
public class CobEvaluate implements Serializable {

    private static final long serialVersionUID = 1563762925360L;


    /**
     * 主键
     * 评分项ID
     */
    private Long idCobEvaluate;

    /**
     * 1 临床技能操作评量
     * 2 迷你临床演练评量
     */
    private String cdCobEvaluate;

    /**
     * 评分项名称
     */
    private String naCobEvaluate;

    /**
     * 排序
     */
    private Integer sort;


}
