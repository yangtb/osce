package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: OrgGrade
 * @Description: 组织_学届管理
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@Setter
@Getter
@ToString
public class OrgGrade implements Serializable {

    private static final long serialVersionUID = 1556984315086L;
    
    /**
     * 主键
     * 学届ID
     */
    private Long idGrade;

    /**
     * 学届名称
     */
    private String naGrade;

    /**
     * 学届描述
     */
    private String desGrade;

    /**
     * 0 未激活 1 已激活
     */
    private String fgActive;

    /**
     * 0 正常，1 删除
     */
    private String fgValid;

    /**
     * 创建人员
     */
    private String creator;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改人员
     * isNullAble:0
     */
    private String operator;

    /**
     * 修改时间
     */
    private Date gmtModify;
}
