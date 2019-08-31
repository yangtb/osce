package com.osce.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: cdAssistantCaEnum
 * @Description: 考官类型
 * @Author yangtongbin
 * @Date 2019-08-31
 */
public enum CdAssistantCaEnum {

    MANAGER("1", "主考官"),
    ASSISTANT("2", "考官"),
    REMOTE("3", "中控考官");

    private String code;
    private String desc;

    CdAssistantCaEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 校验枚举值是否存在
     *
     * @param code
     * @return
     */
    public static boolean contains(String code) {
        if (StringUtils.isBlank(code)) {
            return false;
        }
        for (CdAssistantCaEnum consType : values()) {
            if (consType.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据名称获取值
     *
     * @param code
     * @return
     */
    public static String getValueByDesc(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (CdAssistantCaEnum enums : values()) {
            if (enums.getCode().equals(code)) {
                return enums.getDesc();
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
