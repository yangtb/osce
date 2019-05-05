package com.osce.enums;

/**
 * @ClassName: SdRegEnum
 * @Description: 机构认证状态
 * @Author yangtongbin
 * @Date 2018/9/27
 */
public enum SdRegEnum {

    UNTREATED("0", "未处理"),
    PASS("1", "审核通过"),
    REJECT("2", "审核驳回");

    private String code;
    private String desc;

    private SdRegEnum(String code, String desc) {
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
