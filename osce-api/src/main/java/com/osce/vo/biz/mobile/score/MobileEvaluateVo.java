package com.osce.vo.biz.mobile.score;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: MobileEvaluateVo
 * @Description: 病例_评量表
 * @Author yangtongbin
 * @Date 2019-07-22
 */
@Setter
@Getter
@ToString
public class MobileEvaluateVo implements Serializable {

    private static final long serialVersionUID = -2147208618250797209L;

    /**
     * 评分项ID
     */
    private Long idCobEvaluate;

    /**
     * 评量结果ID
     */
    private Long idWeEvaluate;

    /**
     * 评分项名称
     */
    private String naCobEvaluate;

    /**
     * 排序
     */
    private Integer score;


}
