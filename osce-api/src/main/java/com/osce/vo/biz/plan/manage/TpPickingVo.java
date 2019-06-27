package com.osce.vo.biz.plan.manage;

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
public class TpPickingVo implements Serializable {

    private static final long serialVersionUID = 1561201071565L;

    /**
     * 日期
     */
    private String planDay;

    /**
     * 时段
     */
    private String planSection;

    /**
     * 房间
     */
    private String naRoom;

    /**
     * 技能
     */
    private String naSkillCase;

    /**
     * 训练次数
     */
    private Long numSkillCase;

    /**
     * 耗材标志
     */
    private String fgConsumables;

    /**
     * 设备
     */
    private String naDevice;

    /**
     * 设备单位
     */
    private String sdDeviceUnit;

    /**
     * 单次消耗数量
     */
    private Long numDevice;

    /**
     * 总消耗数量
     */
    private Long numPlan;

}
