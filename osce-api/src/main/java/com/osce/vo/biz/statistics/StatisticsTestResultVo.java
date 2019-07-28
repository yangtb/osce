package com.osce.vo.biz.statistics;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TdModel
 * @Description: 模板_模板管理
 * @Author yangtongbin
 * @Date 2019-05-25
 */
@Setter
@Getter
@ToString
public class StatisticsTestResultVo implements Serializable {

    private static final long serialVersionUID = -6873783614886332991L;

    /**
     * 计划ID
     */
    private Long idPlan;

    /**
     * 考试名称
     */
    private String naPlan;

    /**
     * 1 未发布 2 已发布-待领料 3 已领料-待考 4 正在考试 5 考试完成
     */
    private String sdPlanStatus;

    /**
     * 补考标志
     */
    private String fgReplan;

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
