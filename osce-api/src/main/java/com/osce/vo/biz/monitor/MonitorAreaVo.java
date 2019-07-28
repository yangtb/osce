package com.osce.vo.biz.monitor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: MonitorAreaVo
 * @Description: 考场监控
 * @Author yangtongbin
 * @Date 2019-07-24
 */
@Setter
@Getter
@ToString
public class MonitorAreaVo implements Serializable {

    private static final long serialVersionUID = -1353833270698470448L;

    /**
     * 考站名称
     */
    private String naStation;

    /**
     * 房间ID
     */
    private Long idRoom;

    /**
     * 房间号
     */
    private String naRoom;

    /**
     * 限时
     */
    private String minCost;

    /**
     * 科目
     */
    private String sdStationCa;

    /**
     * 试卷名称
     */
    private String naPaper;

    /**
     * 试卷描述
     */
    private String desPaper;

    /**
     * 技能考试_主考官ID
     */
    private Long idUserManager;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 状态 1 待考,2 叫号待认证,3 叫号已认证,4 考试开始,5 考试结束,6 缺考
     */
    private Integer sdExecQueue;

}
