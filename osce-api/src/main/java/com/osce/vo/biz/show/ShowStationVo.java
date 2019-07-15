package com.osce.vo.biz.show;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: ShowStationVo
 * @Description: 房间的考试信息
 * @Author yangtongbin
 * @Date 2019-07-06
 */
@Setter
@Getter
@ToString
public class ShowStationVo implements Serializable {

    private static final long serialVersionUID = -5364212419977645232L;


    /**
     * 考站
     */
    private String naStation;

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

    /**
     * 二维码url
     */
    private String qrCodeUrl;


}
