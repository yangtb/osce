package com.osce.vo.biz.execute;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TestStuVo
 * @Description: 学员信息
 * @Author yangtongbin
 * @Date 2019-07-19
 */
@Setter
@Getter
@ToString
public class TestStuVo implements Serializable {

    private static final long serialVersionUID = 3179598101978646972L;

    /**
     * 执行ID
     */
    private Long idExecQueue;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 执行ID
     */
    private Long idExec;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 等待序号
     */
    private Integer noReg;

    /**
     * 手机
     */
    private String phoneNo;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 试卷id
     */
    private Long idPaper;

    /**
     * 限时
     */
    private String minCost;

    /**
     * 执行状态
     */
    private Integer sdExecQueue;

    /**
     * 实际开始时间
     */
    private String actBegin;

    /**
     * 实际结束时间
     */
    private String actEnd;

    /**
     * 服务器时间
     */
    private String nowTime;

    /**
     * 倒计时时间
     */
    private String countdownTime;

    /**
     * 正确数
     */
    private Integer rightNum;

    /**
     * 错误数
     */
    private Integer errorNum;

    /**
     * 未完成数
     */
    private Integer noDoneNum;

    /**
     * 得分
     */
    private Integer totalScore;

}
