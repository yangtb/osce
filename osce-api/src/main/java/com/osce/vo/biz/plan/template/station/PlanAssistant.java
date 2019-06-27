package com.osce.vo.biz.plan.template.station;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PlanSp
 * @Description: 计划分配sp
 * @Author yangtongbin
 * @Date 2019-06-19
 */
@Setter
@Getter
@ToString
public class PlanAssistant implements Serializable {

    private static final long serialVersionUID = 1633746824969998117L;

    /**
     * 技能考试_主考官ID
     */
    private Long idUserManager;

    /**
     * 技能考试_考官ID
     */
    private Long idUserAssistant;

    /**
     * 技能考试_中程考官ID
     */
    private Long idUserRemote;

    /**
     * 姓名
     */
    private String managerName;

    /**
     * 姓名
     */
    private String assistantName;

    /**
     * 姓名
     */
    private String remoteName;

}
