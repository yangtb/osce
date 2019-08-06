package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: TdItem
 * @Description: 模板_题目管理
 * @Author yangtongbin
 * @Date 2019-06-08
 */
@Setter
@Getter
@ToString
public class TdItem implements Serializable {

    private static final long serialVersionUID = 1559979973603L;


    /**
     * 主键
     * 题目ID
     */
    private Long idItem;

    /**
     * 题集ID
     */
    private Long idItemStore;

    /**
     * 目录ID
     */
    private Long idItemSection;

    /**
     * 1 题库试题 2 私有试题 3 导入试题
     */
    private String sdItemFrom;

    /**
     * 1 A1 2 A2 3 B1
     */
    private String sdItemCa;

    /**
     * 题干
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
     * 必选标志
     */
    private String fgMust;

    /**
     * 启用标志
     */
    private String fgActive;

    /**
     * 选项
     */
    private List<TdItemOption> itemOptions;

}
