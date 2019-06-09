package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TdItemSection
 * @Description: 模板_目录管理
 * @Author yangtongbin
 * @Date 2019-06-09
 */
@Setter
@Getter
@ToString
public class TdItemSection implements Serializable {

    private static final long serialVersionUID = 1560011068322L;

    /**
     * 主键
     * 目录ID
     */
    private Long idItemSection;

    /**
     * 题集ID
     */
    private Long idItemStore;

    /**
     * 目录名称
     */
    private String naItemSection;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 0 未激活 1 已激活
     */
    private String fgActive;

}
