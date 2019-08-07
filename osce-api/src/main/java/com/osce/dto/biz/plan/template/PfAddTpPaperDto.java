package com.osce.dto.biz.plan.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfAddTpPaperDto
 * @Description: 试卷参数
 * @Author yangtongbin
 * @Date 2019-06-08
 */
@Setter
@Getter
@ToString
public class PfAddTpPaperDto implements Serializable {

    private static final long serialVersionUID = 1559975708033L;

    /**
     * 排站ID
     */
    private Long idInsStation;

    /**
     * 试卷id
     */
    private Long idPaper;

    /**
     * 评分表id
     */
    private Long idScoreSheet;

    /**
     * 是否应用到该考站的所有时段
     */
    private boolean allFlag;

}
