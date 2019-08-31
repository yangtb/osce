package com.osce.dto.biz.mobile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: MobileScoreDto
 * @Description: 评分
 * @Author yangtongbin
 * @Date 2019-08-17
 */
@Setter
@Getter
@ToString
public class MobileScoreDto extends MobileDto implements Serializable {

    private static final long serialVersionUID = 1275705148836209606L;

    /**
     * 序列ID
     */
    private Long idExecQueue;

    /**
     * 执行ID
     */
    private Long idExec;

    /**
     * 评量ID
     */
    private Long idCobEvaluate;

    /**
     * 1 主考官 2 考官 3 中控考官
     */
    private String cdAssistantCa;
}
