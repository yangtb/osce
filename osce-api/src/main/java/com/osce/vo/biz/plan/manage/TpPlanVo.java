package com.osce.vo.biz.plan.manage;

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
public class TpPlanVo implements Serializable {

    private static final long serialVersionUID = 2896722517125516615L;

    /**
     * 主键
     * 计划ID
     */
    private Long idPlan;

    /**
     *
     */
    private Long idModelFrom;

    /**
     * 模板ID
     */
    private Long idModel;

    /**
     * 考试名称
     */
    private String naPlan;

    /**
     * 考试描述
     */
    private String desPlan;

    /**
     * 补考标志
     */
    private String fgReplan;

    /**
     * 1 未发布 2 已发布-待领料 3 已领料-待考 4 正在考试 5 考试完成
     */
    private String sdPlanStatus;

    /**
     * 计划开始时间
     */
    private String gmtPlanBegin;

    /**
     * 计划结束时间
     */
    private String gmtPlanEnd;

    /**
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 考场数
     */
    private Long areaNum;

    /**
     * 学生数
     */
    private Long studentNum;


}
