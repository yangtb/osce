package com.osce.vo.biz.show;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: ShowRoomStudentVo
 * @Description: 房间下学员信息
 * @Author yangtongbin
 * @Date 2019-07-07
 */
@Setter
@Getter
@ToString
public class ShowRoomStudentVo implements Serializable {

    private static final long serialVersionUID = -4493355522909125432L;

    /**
     * 学生用户ID
     */
    private Long idUserStudent;

    /**
     * 考场号
     */
    private String cdStudent;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 电话
     */
    private String phoneNo;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 学号
     */
    private String userCd;

    /**
     * 计划开始时间
     */
    private String planBegin;

    /**
     * 计划结束时间
     */
    private String planEnd;

    /**
     * 状态     1 待考,2 叫号待认证,3 叫号已认证,4 考试开始,5 考试结束,6 缺考
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
     * 站点耗时（可用来计算倒计时）
     */
    private Integer minCost;

}
