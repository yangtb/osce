package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

/**
 * @ClassName: CobSkillCase
 * @Description: CobSkillCase
 * @Author yangtongbin
 * @Date 2019-05-15
 */
@Setter
@Getter
@ToString
public class CobSkillCase implements Serializable {

    private static final long serialVersionUID = 1557887024749L;

    /**
     * 主键
     */
    private String id;

    /**
     * 技能病例ID
     */
    private String idSkillCase;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 技能病例名称
     */
    private String naSkillCase;

    /**
     * 技能病例类别
     */
    private String sdSkillCaseCa;

    /**
     * 技能病例简介
     */
    private String desSkillCase;

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
