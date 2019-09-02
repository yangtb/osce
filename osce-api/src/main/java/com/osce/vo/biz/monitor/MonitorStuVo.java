package com.osce.vo.biz.monitor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: MonitorStuVo
 * @Description: 学生列表
 * @Author yangtongbin
 * @Date 2019-07-23
 */
@Setter
@Getter
@ToString
public class MonitorStuVo implements Serializable {

    private static final long serialVersionUID = 8303572873786241447L;

    /**
     * 队列ID
     */
    private Long idExecQueue;

    /**
     * 排站明细ID
     */
    private Long idInsStationDetail;

    /**
     * 入场登记ID
     */
    private Long idWaitingReg;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 登记序号
     */
    private String rownum;

    /**
     * 手机号
     */
    private String phoneNo;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 入场时间
     */
    private String timeBegin;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 结束状态
     */
    private Integer endStatus;

    /**
     * 考站名称
     */
    private String naStation;

    /**
     * 考场名称
     */
    private String naArea;

    /**
     * 房间名称
     */
    private String naRoom;

    /**
     * 入场序号
     */
    private String noReg;

    /**
     * 开考时间
     */
    private String actBegin;

}
