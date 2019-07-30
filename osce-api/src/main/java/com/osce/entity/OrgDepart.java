package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: OrgDepart
 * @Description: 部门
 * @Author yangtongbin
 * @Date 2019-05-06
 */
@Setter
@Getter
@ToString
public class OrgDepart implements Serializable {

    private static final long serialVersionUID = 1557148346098L;

    /**
     * 主键
     * 部门ID
     */
    private Long idDepart;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 学届ID
     */
    private Long idGrade;

    /**
     * 部门名称
     */
    private String naDepart;

    /**
     * 部门描述
     */
    private String desDepart;

    /**
     * 1 院系 2 专业 3 班级
     */
    private String sdDepartCa;

    /**
     * 上级部门
     */
    private Long idDepartPar;

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
