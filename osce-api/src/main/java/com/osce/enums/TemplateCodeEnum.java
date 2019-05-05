package com.osce.enums;

/**
 * @ClassName: TemplateCodeEnum
 * @Description: 模板枚举
 * @Author yangtongbin
 * @Date 2018/9/21 22:52
 */
public enum TemplateCodeEnum {

    EMAIL_REGISTER_CODE("emailRegisterCode", "注册验证码(邮件)");

    private String code;
    private String desc;

    private TemplateCodeEnum(String code, String desc) {
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
