package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: IbmItem
 * @Description: IbmItem
 * @Author yangtongbin
 * @Date 2019-05-14
 */
@Setter
@Getter
@ToString
public class IbmItem implements Serializable {

    private static final long serialVersionUID = 1557804849366L;

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
     * 1 多选 2 单选
     */
    private String sdItemCa;

    /**
     * 题目来源
     */
    private String sdItemFrom;

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
     * 0 未激活 1 已激活
     */
    private String fgActive;

    /**
     * 0 正常 1 删除
     */
    private String fgValid;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建人员
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改人员
     */
    private String operator;

    /**
     * 修改时间
     */
    private Date gmtModify;

    /**
     * 选项
     */
    private List<IbmItemOption> itemOptions;

}
