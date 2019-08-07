package com.osce.vo.biz.plan.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfExamPaperSheetVo
 * @Description: 试卷对应评分表
 * @Author yangtongbin
 * @Date 2019-08-07
 */
@Setter
@Getter
@ToString
public class PfExamPaperSheetVo implements Serializable {

    private static final long serialVersionUID = 688155801601081911L;

    /**
     * 试评分表ID
     */
    private Long idScoreSheet;

    /**
     * 评分表名称
     */
    private String naScoreSheet;


}
