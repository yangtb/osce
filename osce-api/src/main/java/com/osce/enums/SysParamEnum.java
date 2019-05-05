package com.osce.enums;

/**
 * @ClassName: SysParamEnum
 * @Description: 系统参数枚举
 * @Author yangtongbin
 * @Date 2018/9/25
 */
public enum SysParamEnum {

    ORG_TRIAL_SWITCH("orgTrialSwitch", "是否开启机构试用"),
    ORG_EXPIRY_DAY("orgExpiryDay", "机构试用有效期"),
    VISITOR_SWITCH("visitorSwitch","是否开启游客功能"),
    EXPIRE_NOTICE_DAY("expireNoticeDay", "机构使用到期提醒天数"),
    EXECUTING_SHOW_EXPERT("executingShowExpert","执行时是否显示专家解读"),
    COMPLETED_SHOW_EXPERT("completedShowExpert", "执行完成是否显示专家解读"),
    SIMULATE_CASE_TYPE("simulateCaseType","模拟就诊(评估)参数");

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
