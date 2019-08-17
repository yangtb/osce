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
public class MobileScoreAddDto implements Serializable {

    private static final long serialVersionUID = 1453578101438676753L;

    /**
     * 执行ID
     */
    private Long idExec;

    /**
     * 评分明细ID
     */
    private Long idWeScore;

    /**
     * 评分项ID
     */
    private Long idScoreItem;

    /**
     * 1 主考官 2 考官 3 中控考官
     */
    private String cdAssistantCa;

    /**
     * 所得分数
     */
    private Integer scoreResult;

}
