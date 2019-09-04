package com.osce.vo.biz.plan.manage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TdAreaInfo
 * @Description: 考场数据
 * @Author yangtongbin
 * @Date 2019-06-12
 */
@Setter
@Getter
@ToString
public class TdPlanStepCheckVo implements Serializable {

    private static final long serialVersionUID = 1601242425858124107L;

    /**
     * 计划时间
     */
    private String planTime;

    /**
     * 考场名称
     */
    private String naArea;

    /**
     * 考站名称
     */
    private String naStation;

    /**
     * 房号
     */
    private String naRoom;

    /**
     * 1 理论试题 2 技能操作 3 病史采集
     */
    private String sdSkillCa;

    /**
     * 技能ID
     */
    private Long idPaper;

    /**
     * 评分表ID
     */
    private Long idScoreSheet;

}
