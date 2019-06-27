package com.osce.vo.biz.plan.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfExamPaperVo
 * @Description: 试卷列表
 * @Author yangtongbin
 * @Date 2019-06-08
 */
@Setter
@Getter
@ToString
public class PfExamPaperVo implements Serializable {

    private static final long serialVersionUID = 4768172736047445405L;

    /**
     * 试卷ID
     */
    private Long id;

    /**
     * 试卷名称
     */
    private String paperName;

    /**
     * 试卷描述
     */
    private String paperDes;

}
