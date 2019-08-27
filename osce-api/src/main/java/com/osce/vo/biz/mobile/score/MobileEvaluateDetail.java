package com.osce.vo.biz.mobile.score;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: MobileEvaluateDetail
 * @Description: 病例_评量明细
 * @Author yangtongbin
 * @Date 2019-07-22
 */
@Setter
@Getter
@ToString
public class MobileEvaluateDetail implements Serializable {

    private static final long serialVersionUID = -1204591568493143616L;

    /**
     * 明细评分项ID
     */
    private Long idCobEvaluateDetail;

    /**
     * 评量明细结果ID
     */
    private Long idWeEvaluateDetail;

    /**
     * 明细评分项名称
     */
    private String naCobEvaluateDetail;

    /**
     * 排序
     */
    private Integer score;

}
