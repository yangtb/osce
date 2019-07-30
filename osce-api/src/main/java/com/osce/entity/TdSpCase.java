package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TdSpCase
 * @Description: 模板_SP病例实例
 * @Author yangtongbin
 * @Date 2019-06-13
 */
@Setter
@Getter
@ToString
public class TdSpCase implements Serializable {

    private static final long serialVersionUID = 1560435539746L;

    /**
     * 主键
     * ID
     */
    private Long id;

    /**
     * 病例ID
     */
    private String idCase;

    /**
     * 模板ID
     */
    private Long idModel;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 来源ID
     */
    private Long idFrom;

    /**
     * SP病例名称
     */
    private String naSpCase;

    /**
     * 及格分数
     */
    private Integer scorePass;

    /**
     * 1 内科 2 外科 3 妇科 4 儿科
     */
    private String sdSpCaseCa;

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
     * ,defaultVal:0
     */
    private String fgValid;

    /**
     * 排序
     * ,defaultVal:999
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
