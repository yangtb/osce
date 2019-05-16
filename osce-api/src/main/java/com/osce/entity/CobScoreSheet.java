package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CobScoreSheet
 * @Description: CobScoreSheet
 * @Author yangtongbin
 * @Date 2019-05-15
 */
@Setter
@Getter
@ToString
public class CobScoreSheet implements Serializable {

    private static final long serialVersionUID = 1557887021552L;


    /**
     * 主键
     * 评分表ID
     */
    private Long idScoreSheet;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 病例ID
     */
    private String idCase;

    /**
     * 评分表名称
     */
    private String naScoreSheet;

    /**
     * 评分表描述
     * isNullAble:1
     */
    private String desScoreSheet;

    /**
     * 及格分数
     */
    private Integer scorePass;

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

}
