package com.osce.vo.biz.training.structure.student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: OrgGrade
 * @Description: 组织_学员管理vo
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@Setter
@Getter
@ToString
public class StudentVo implements Serializable {

    private static final long serialVersionUID = -9093159189068220389L;

    /**
     * 关系id
     */
    private Long idStudentDepart;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 学届
     */
    private String naGrade;

    /**
     * 班级
     */
    private String naDepart;

    /**
     * 账号
     */
    private String username;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 电话
     */
    private String phoneNo;

    /**
     * 身份证号码
     */
    private String idcard;

    /**
     * 是否启用,1启用 0停用
     */
    private boolean enabled;

    /**
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 机构id
     */
    private Long idOrg;

    /**
     * 部门id
     */
    private Long idDepart;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像地址
     */
    private String photoAddr;

    /**
     * 学号
     */
    private String userCd;

}
