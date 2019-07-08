package com.osce.vo.biz.mobile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: MobileMainVo
 * @Description: 移动端首页
 * @Author yangtongbin
 * @Date 2019-07-08
 */
@Setter
@Getter
@ToString
public class MobileMainVo implements Serializable {

    private static final long serialVersionUID = 7839339956849536386L;

    /**
     * 考场名称
     */
    private String naArea;

    /**
     * 考站
     */
    private String naStation;

    /**
     * 科目
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
     * 试卷描述
     */
    private String desPaper;

    /**
     * 站点耗时（可用来计算倒计时）
     */
    private Integer minCost;

    /**
     * 必过标志（暂时无用）
     */
    private String fgMust;

    /**
     * 并发人数（暂时无用）
     */
    private Integer numConcur;


}
