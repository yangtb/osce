package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: CobSpCase
 * @Description: CobSpCase
 * @Author yangtongbin
 * @Date 2019-05-15
 */
@Setter
@Getter
@ToString
public class CobSpCase implements Serializable {

    private static final long serialVersionUID = 1557887442544L;

    /**
     * 主键
     */
    private String id;

    /**
     * 主键
     */
    private String idCase;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * SP病例名称
     */
    private String naSpCase;

    /**
     * 1 内科 2 外科 3 妇科 4 儿科
     */
    private String sdSpCaseCa;

    /**
     * 1 内科 2 外科 3 妇科 4 儿科
     */
    private String sdSpCaseCaText;

    /**
     * SP病例简介
     */
    private String desSpCase;

    /**
     * 患者主诉
     */
    private String desProb;

    /**
     * 现病史
     */
    private String desCurDie;

    /**
     * 既往史
     */
    private String desHisDie;

    /**
     * 个人史
     */
    private String desPatDie;

    /**
     * 家族史
     */
    private String desFamDie;

    /**
     * 表演要点
     */
    private String desPoints;

    /**
     * 剧本文档
     */
    private String docSp;

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

}
