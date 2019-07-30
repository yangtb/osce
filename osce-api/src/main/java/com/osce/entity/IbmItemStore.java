package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: IbmItemStore
 * @Description: IbmItemStore
 * @Author yangtongbin
 * @Date 2019-05-14
 */
@Setter
@Getter
@ToString
public class IbmItemStore implements Serializable {

    private static final long serialVersionUID = 1557804840313L;


    /**
     * 主键
     * 题集ID
     */
    private Long idItemStore;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 题集名称
     */
    private String naItemStore;

    /**
     * 题集描述
     */
    private String desItemStore;

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
    private String gmtCreate;

    /**
     * 修改人员
     */
    private String operator;

    /**
     * 修改时间
     */
    private String gmtModify;

    /**
     * 题目数量
     */
    private Long itemNum;

}
