package com.osce.vo.biz.show;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: ShowAioMainVo
 * @Description: 一体机main
 * @Author yangtongbin
 * @Date 2019-06-30
 */
@Setter
@Getter
@ToString
public class ShowAioMainVo implements Serializable {

    private static final long serialVersionUID = -6026943475970981541L;

    /**
     * 机构
     */
    private String naOrg;

    /**
     * 考试名称
     */
    private String naPlan;

    /**
     * 考试类型
     */
    private String fgReplan;

    /**
     * 1 未发布 2 已发布-待领料 3 已领料-待考 4 正在考试 5 考试完成
     */
    private String sdPlanStatus;

    /**
     * 考场
     */
    private String naArea;

    /**
     * 时间
     */
    private String planDay;

    /**
     * 开始时间
     */
    private String timeBegin;

    /**
     * 结束时间
     */
    private String timeEnd;

    /**
     * 计划id
     */
    private Long idPlan;

    /**
     * 考场
     */
    private Long idArea;

    /**
     * 时间：上午、下午
     */
    private float timeSection;

    /**
     * 计划时间
     */
    private String planTime;

    /**
     * 待考区总数
     */
    private Long itemNum;

    /**
     * 每页数据量
     */
    private Integer limit;

}
