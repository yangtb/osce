package com.osce.dto.user.login;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: RegisterDto
 * @Description: 注册dto
 * @Author yangtongbin
 * @Date 2019-07-20
 */
@Setter
@Getter
@ToString(exclude = {"password"})
public class RegisterDto implements Serializable {

    private static final long serialVersionUID = -6354572733503100572L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 电邮
     */
    private String email;

    /**
     * 联系电话
     */
    private String phoneNo;

    /**
     * 用户角色类型：1超级管理员，2普通管理员
     */
    private String roleType;

    /**
     * 是否锁定
     */
    private boolean enabled;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户角色
     */
    private List<Long> roles;

    /**
     * 操作人员
     */
    private String operator;

    /**
     * 身份证号
     */
    private String idcard;

    /**
     * 头像地址
     */
    private String photoAddr;

    /**
     * 学号
     */
    private String userCd;

    /**
     * 用户所在机构
     */
    private Long idOrg;

    /**
     * 当前登陆用户机构id
     */
    private Long currentUserOrgId;

    /**
     * 平台或超级管理员用户
     */
    private boolean platOrSuper;

    /**
     * 批量导入错误
     */
    private String importErrorMsg;

}
