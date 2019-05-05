package com.osce.enums;

/**
 * @ClassName: PfRoleEnum
 * @Description: 平台角色code枚举
 * @Author yangtongbin
 * @Date 2018/9/24 17:44
 */
public enum PfRoleEnum {

    PFADM("PFADM", "平台管理员"),
    PFTOU("PFTOU", "游客"),
    MCADM("MCADM", "机构管理员"),
    MCTE("MCTE", "老师"),
    MCST("MCST", "学生");

    private String code;
    private String desc;

    private PfRoleEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
