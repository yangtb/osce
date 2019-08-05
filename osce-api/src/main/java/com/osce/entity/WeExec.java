package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: WeExec
 * @Description: 执行_执行记录
 * @Author yangtongbin
 * @Date 2019-08-05
 */
@Setter
@Getter
@ToString
public class WeExec implements Serializable {

    private static final long serialVersionUID = 1565015726199L;

    /**
     * 主键
     * 执行ID
     */
    private Long idExec;

    /**
     * 队列ID
     */
    private Long idExecQueue;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 计划ID
     */
    private Long idPlan;

    /**
     * 学员ID
     */
    private Long idUserStudent;

    /**
     * 考场ID
     */
    private Long idArea;

    /**
     * 实例说明：
     * 1 第一天上午  1.5 第一天下午
     * 2 第二天上午  2.5 第二天下午
     * 以此类推
     */
    private Float timeSection;

    /**
     * 考站ID
     */
    private Long idStation;

    /**
     * 房间ID
     */
    private Long idRoom;

    /**
     * 1 理论试题
     * 2 技能操作
     * 3 病史采集
     */
    private String sdSkillCa;

    /**
     * 试卷ID
     */
    private Long idPaper;

    /**
     * 评分表ID
     */
    private Long idScoreSheet;

    /**
     * 技能考试_主考官ID
     */
    private Long idUserManager;

    /**
     * 技能考试_主考官权重
     */
    private Integer weightManager;

    /**
     * 技能考试_主考官评分
     */
    private Integer scoreManager;

    /**
     * 技能考试_考官ID
     */
    private Long idUserAssistant;

    /**
     * 技能考试_考官权重
     */
    private Integer weightAssistant;

    /**
     * 技能考试_考官评分
     */
    private Integer scoreAssistant;

    /**
     * 技能考试_中程考官ID
     */
    private Long idUserRemote;

    /**
     * 技能考试_中程考官权重
     */
    private Integer weightRemote;

    /**
     * 技能考试_中程考官评分
     */
    private Integer scoreRemote;

    /**
     * 总分
     */
    private Integer scoreSum;

    /**
     * 及格分数
     */
    private Integer scorePass;

    /**
     * 是否及格
     */
    private String fgPass;

    /**
     * 是否提交
     */
    private String fgCommit;

    /**
     * 实际开始时间
     */
    private String actBegin;

    /**
     * 实际结束时间
     */
    private String actEnd;

}
