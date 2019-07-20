package com.osce.vo.biz.execute;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: ExecListVo
 * @Description: list
 * @Author yangtongbin
 * @Date 2019-07-17
 */
@Setter
@Getter
@ToString
public class ExecTestVo implements Serializable {

    private static final long serialVersionUID = 324256386038325621L;

    /**
     * 执行ID
     */
    private Long idExecQueue;

    /**
     * 科目
     */
    private String sdStationCa;

    /**
     * 科目text
     */
    private String sdStationCaText;

    /**
     * 类型
     */
    private String sdSkillCa;

    /**
     * 试卷名称
     */
    private String naPaper;

    /**
     * 限时
     */
    private String minCost;

    /**
     * 状态 1 待考 2 已交卷
     */
    private Integer status;

}
