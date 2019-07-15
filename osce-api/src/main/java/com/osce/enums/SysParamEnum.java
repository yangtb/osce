package com.osce.enums;

/**
 * @ClassName: SysParamEnum
 * @Description: 系统参数枚举
 * @Author yangtongbin
 * @Date 2018/9/25
 */
public enum SysParamEnum {

    VISITOR_SWITCH("visitorSwitch", "是否开启游客功能"),
    BIG_SCREEN_NUM("bigScreenNum", "待考区大屏显示条数"),
    STATION_QR_CODE_URL("stationQrCodeUrl", "站点房间二维码链接"),
    ROOM_SHOW_NUM("roomShowNum", "考站等待学员展示条数"),
    MOBILE_AUTH_CODE("mobileAuthCode", "移动端授权码");

    private String code;
    private String desc;

    SysParamEnum(String code, String desc) {
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
