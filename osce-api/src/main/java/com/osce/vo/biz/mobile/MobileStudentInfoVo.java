package com.osce.vo.biz.mobile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: MobileStudentInfoVo
 * @Description: 学员信息
 * @Author yangtongbin
 * @Date 2019-08-08
 */
@Setter
@Getter
@ToString
public class MobileStudentInfoVo implements Serializable {

    private static final long serialVersionUID = -8484989484445556209L;

    /**
     * 执行ID
     */
    private Long idExecQueue;

    /**
     * 学生用户ID
     */
    private Long idUserStudent;

    /**
     * 考场号
     */
    private String cdStudent;

    /**
     * 执行状态
     */
    private Integer sdExecQueue;

    /**
     * 计划开始时间
     */
    private String planBegin;

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
     * 头像url
     */
    private String headPhotoUrl;


}
