package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TpPicking
 * @Description: 计划_领料计划
 * @Author yangtongbin
 * @Date 2019-06-22
 */
@Setter
@Getter
@ToString
public class TpPicking implements Serializable {

    private static final long serialVersionUID = 1561201071565L;


    /**
     * 主键
     * 领料计划ID
     */
    private Long idTpPicking;

    /**
     * 计划ID
     */
    private Long idPlan;

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
     * 房间ID
     */
    private Long idRoom;

    /**
     * 技能病例ID
     */
    private String idSkillCase;

    /**
     * 技能病例使用次数
     */
    private Integer numSkillCase;

    /**
     * 设备ID
     */
    private Long idDevice;

    /**
     * 计划数量
     */
    private Float numPlan;

    /**
     * 0 未领
     * 1 以领
     */
    private String fgPicking;

    /**
     * 已领数量
     */
    private Float fgPicked;

}
