package com.osce.vo.biz.plan.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfPaperVo
 * @Description: 试卷列表
 * @Author yangtongbin
 * @Date 2019-06-08
 */
@Setter
@Getter
@ToString
public class PfPaperVo implements Serializable {

    private static final long serialVersionUID = 4768172736047445405L;

    /**
     * 题集ID
     */
    private Long idItemStore;

    /**
     * 试卷名称
     */
    private String naItemStore;

    /**
     * 题集名称
     */
    private String naItemStoreFrom;

    /**
     * 题目数量
     */
    private Integer itemNum;

    /**
     * 总分值
     */
    private Integer totalScore;

    /**
     * 创建时间
     */
    private String gmtCreate;

}
