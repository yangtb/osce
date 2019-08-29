package com.osce.vo.biz.plan.manage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PlanPublishItemVo
 * @Description: 发布清单
 * @Author yangtongbin
 * @Date 2019-06-27
 */
@Setter
@Getter
@ToString
public class PlanPublishItemVo implements Serializable {

    private static final long serialVersionUID = -4827136347318765224L;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 联系方式
     */
    private String phoneNo;

    /**
     * 考场
     */
    private String naArea;

    /**
     * 考站
     */
    private String naStation;

    /**
     * 基地
     */
    private String sdStationCaText;

    /**
     * 技能
     */
    private String sdSkillCaText;

    /**
     * 房间
     */
    private String naRoom;

    /**
     * 日期
     */
    private String planTime;

    /**
     * 开始时间
     */
    private String timeBegin;

    /**
     * 结束时间
     */
    private String timeEnd;

    /**
     * 考官类别
     */
    private String manageType;

}
