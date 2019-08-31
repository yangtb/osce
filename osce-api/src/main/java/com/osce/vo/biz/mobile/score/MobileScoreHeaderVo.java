package com.osce.vo.biz.mobile.score;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: MobileScoreHeaderVo
 * @Description: 评分页头部信息
 * @Author yangtongbin
 * @Date 2019-08-17
 */
@Setter
@Getter
@ToString
public class MobileScoreHeaderVo implements Serializable {

    private static final long serialVersionUID = 7364199963910988117L;

    /**
     * 序列ID
     */
    private Long idExecQueue;

    /**
     * 执行ID
     */
    private Long idExec;

    /**
     * 学生用户ID
     */
    private Long idUserStudent;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 手机
     */
    private String phoneNo;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 学号
     */
    private String userCd;

    /**
     * 实际开始时间
     */
    private String actBegin;

    /**
     * 试题id
     */
    private Long idPaper;

    /**
     * 试题名称
     */
    private String naPaper;

    /**
     * 考试名称
     */
    private String naPlan;

    /**
     * 技能类型
     */
    private String sdSkillCa;

    /**
     * 倒计时时间(s)
     */
    private Long countdownSecond;

    /**
     * 头像url
     */
    private String headPhotoUrl;

}
