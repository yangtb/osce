package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TdInsStation
 * @Description: 模板_排站
 * @Author yangtongbin
 * @Date 2019-05-25
 */
@Setter
@Getter
@ToString
public class TdInsStation implements Serializable {

    private static final long serialVersionUID = 1558791616794L;


    /**
     * 主键
     * 排站ID
     */
    private Long idInsStation;

    /**
     * 模板ID
     */
    private Long idModel;

    /**
     * 考场ID
     */
    private Long idArea;

    /**
     * 考站ID
     */
    private Long idStation;

    /**
     * 站点ID
     */
    private Long idSite;

    /**
     * 实例说明：
     * 1 第一天上午  1.5 第一天下午
     * 2 第二天上午  2.5 第二天下午
     * 以此类推
     */
    private Float timeSection;

    /**
     * 技能ID
     */
    private Long idPaper;

    /**
     * 房间ID
     */
    private Long idRoom;

    /**
     * 1 内科
     * 2 外科
     * 3 妇产科
     * 4 儿科
     * 5 急诊
     * 6 医患沟通
     * 7 神经内科
     * 8 眼鼻喉
     * 9 皮肤科
     * 10 肿瘤
     * 11 传染
     * 12 心理精神
     * 13 循证医学
     * 14 康复
     * 15 伦理
     */
    private String sdStationCa;

    /**
     * 1 理论试题
     * 2 技能操作
     * 3 病史采集
     */
    private String sdSkillCa;

    /**
     * 站点耗时
     */
    private Integer minCost;

    /**
     * 必过标志
     */
    private String fgMust;

    /**
     * 并发人数
     */
    private Integer numConcur;


}
