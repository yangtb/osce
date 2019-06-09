package com.osce.dto.biz.plan.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PfPaperMustDto
 * @Description: 设置必考题
 * @Author yangtongbin
 * @Date 2019-06-08
 */
@Setter
@Getter
@ToString
public class PfPaperMustDto implements Serializable {

    private static final long serialVersionUID = 1559975708033L;

    /**
     * 1 全选 2 全不选
     */
    private Integer status;

    /**
     * 题集ID
     */
    private Long idItemStore;

    /**
     * 题目ID
     */
    private List<Long> idItems;

}
