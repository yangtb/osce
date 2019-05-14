package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: IbmItemSection
 * @Description: IbmItemSection
 * @Author yangtongbin
 * @Date 2019-05-14
 */
@Setter
@Getter
@ToString
public class IbmItemSection implements Serializable {

    private static final long serialVersionUID = 1557804843035L;

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

}
