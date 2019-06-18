package com.osce.vo.biz.plan.manage;

import com.osce.entity.TpStudent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: AssignedStudentVo
 * @Description: 已分配学员
 * @Author yangtongbin
 * @Date 2019-06-18
 */
@Setter
@Getter
@ToString
public class AssignedStudentVo extends TpStudent implements Serializable {

    private static final long serialVersionUID = 6849990672559304594L;

    /**
     * 年级
     */
    private String naGrade;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 手机号
     */
    private String phoneNo;

    /**
     * 身份证号
     */
    private String idcard;

}
