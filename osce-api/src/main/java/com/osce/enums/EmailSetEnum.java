package com.osce.enums;

/**
 * @ClassName: WebsiteSetEnum
 * @Description: 网站参数枚举
 * @Author yangtongbin
 * @Date 2018/9/19 09:26
 */
public enum EmailSetEnum {

    HOST("core.email.host", "主机"),
    SENDER("core.email.sender", "发送者"),
    USERNAME("core.email.userName", "名称"),
    NICKNAME("core.email.nickname", "别名"),
    PASSWORD("core.email.password", "密码"),
    SEND_SWITCH("core.email.sendSwitch", "发送邮件开关");

    private String code;
    private String desc;

    private EmailSetEnum(String code, String desc) {
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
