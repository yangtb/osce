package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TdItemOption
 * @Description: 题库_题目选项
 * @Author yangtongbin
 * @Date 2019-08-06
 */
@Setter
@Getter
@ToString
public class TdItemOption implements Serializable {

    private static final long serialVersionUID = 1565073352088L;

    /**
     * 主键
     * 选项ID
     */
    private Long idItemOption;

    /**
     * 题目ID
     */
    private Long idItem;

    /**
     * 选项编码
     */
    private String cdIte;

    /**
     * 选项内容
     */
    private String naOption;

    /**
     * 正确答案标志
     */
    private String fgRight;

}
