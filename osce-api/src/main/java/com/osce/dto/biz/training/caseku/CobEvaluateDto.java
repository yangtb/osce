package com.osce.dto.biz.training.caseku;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: CobEvaluateDto
 * @Description: 评量表dto
 * @Author yangtongbin
 * @Date 2019-07-22
 */
@Setter
@Getter
@ToString
public class CobEvaluateDto implements Serializable {

    private static final long serialVersionUID = -4235601814686770817L;

    /**
     * 评分项ID
     */
    private Long idCobEvaluate;

    /**
     * 1 临床技能操作评量
     * 2 迷你临床演练评量
     */
    private String cdCobEvaluate;

    /**
     * 明细评分项ID
     */
    private Long idCobEvaluateDetail;
}
