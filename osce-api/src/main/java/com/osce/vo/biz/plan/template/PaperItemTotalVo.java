package com.osce.vo.biz.plan.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PaperItemTotalVo
 * @Description: 题目总数
 * @Author yangtongbin
 * @Date 2019-06-08
 */
@Setter
@Getter
@ToString
public class PaperItemTotalVo implements Serializable {

    private static final long serialVersionUID = 1559994295226L;

    /**
     * 1=A1, 2=A2, 3=B1
     */
    private String sdItemCa;

    /**
     * 类型数量
     */
    private Integer total;

}
