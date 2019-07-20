package com.osce.vo.biz.execute;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: ExecAuthVo
 * @Description: ExecAuthVo
 * @Author yangtongbin
 * @Date 2019-07-17
 */
@Setter
@Getter
@ToString
public class ExecAuthVo implements Serializable {

    private static final long serialVersionUID = 324256386038325621L;

    /**
     * 队列ID
     */
    private Long idExecQueue;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 联系电话
     */
    private String phoneNo;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 入场序号
     */
    private Integer noReg;

    /**
     * 计划开始时间
     */
    private String planBegin;

    /**
     * 计划结束时间
     */
    private String planEnd;

    /**
     * 状态 1 待考,2 叫号待认证,3 叫号已认证,4 考试开始,5 考试结束,6 缺考
     */
    private Integer sdExecQueue;

}
