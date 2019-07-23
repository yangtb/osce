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
    private String noReg;

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
    private String gmtReg;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 结束状态
     */
    private Integer endStatus;

}
