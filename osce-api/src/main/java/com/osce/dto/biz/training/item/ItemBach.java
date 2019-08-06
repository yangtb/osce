package com.osce.dto.biz.training.item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: ItemBach
 * @Description:
 * @Author yangtongbin
 * @Date 2019-08-02
 */
@Setter
@Getter
@ToString
public class ItemBach implements Serializable {

    private static final long serialVersionUID = -1318875385874691256L;

    /**
     * 题集id
     */
    private Long idItemStore;

    /**
     * 题集名称
     */
    private String naItemStore;

    /**
     * 目录名称
     */
    private String naItemSection;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 1 多选 2 单选
     */
    private String sdItemCa;

    /**
     * 题目主干
     */
    private String mainItem;

    /**
     * 1 易 2 较易 3 中 4 难 5 较难
     */
    private String sdItemLevel;

    /**
     * 题目解析
     */
    private String itemAnalysis;

    /**
     * 默认分值
     */
    private Integer scoreDefault;

    /**
     * 选项号码
     */
    private String cdIteStr_A;

    /**
     * 选项
     */
    private String naOption_A;

    /**
     * 正确答案标志
     */
    private String fgRight_A;

    /**
     * 选项号码
     */
    private String cdIteStr_B;

    /**
     * 选项
     */
    private String naOption_B;

    /**
     * 正确答案标志
     */
    private String fgRight_B;

    /**
     * 选项号码
     */
    private String cdIteStr_C;

    /**
     * 选项
     */
    private String naOption_C;

    /**
     * 正确答案标志
     */
    private String fgRight_C;

    /**
     * 选项号码
     */
    private String cdIteStr_D;

    /**
     * 选项
     */
    private String naOption_D;

    /**
     * 正确答案标志
     */
    private String fgRight_D;

}
