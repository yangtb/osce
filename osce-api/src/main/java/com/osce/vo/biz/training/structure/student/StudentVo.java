package com.osce.vo.biz.training.structure.student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

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
     * 班级
     */
    private String naGrade;

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
    private Date gmtCreate;


}