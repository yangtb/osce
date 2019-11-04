package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TpPlan
 * @Description: 计划_实训计划
 * @Author yangtongbin
 * @Date 2019-06-17
 */
@Setter
@Getter
@ToString
public class TpPlan implements Serializable {

    private static final long serialVersionUID = 1560751001310L;

    /**
     * 主键
     * 计划ID
     */
    private Long idPlan;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 模板来源
     */
    private String idModelFrom;

    /**
     * 模板ID
     */
    private String idModel;

    /**
     * 模板名称
     */
    private String naModel;

    /**
     * 考试名称
     */
    private String naPlan;

    /**
     * 考试描述
     */
    private String desPlan;

    /**
     * 1 未发布 2 已发布-待领料 3 已领料-待考 4 正在考试 5 考试完成
     */
    private String sdPlanStatus;

    /**
     * 补考标志
     */
    private String fgReplan;

    /**
     * 补考来源
     */
    private Long idReplanFrom;

    /**
     * 补考来源text
     */
    private String idReplanFromText;

    /**
     * 计划开始时间
     */
    private String gmtPlanBegin;

    /**
     * 计划结束时间
     */
    private String gmtPlanEnd;

    /**
     * 考试发布时间
     */
    private String gmtRelease;

    /**
     * 领料完成时间
     */
    private String gmyPicking;

    /**
     * 实际开考时间
     */
    private String gmtActBegin;

    /**
     * 实际结束时间
     */
    private String gmtActEnd;

    /**
     * 领料完成度
     */
    private Integer percentPicking;

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
     * 学届ID
     */
    private Long idGrade;
}
