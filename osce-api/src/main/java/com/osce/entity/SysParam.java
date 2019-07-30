package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
public class SysParam implements Serializable {

    private static final long serialVersionUID = 8617982017538738949L;

    private Long        id;             // ID
    private String      paramCode;      // 参数编码
    private String      paramName;      // 参数名称
    private String      paramValue;     // 参数值
    private String      defaultValue;   // 默认值
    private String      dataType;       // 参数值数据类型(string-字串，int-整数 float-小数 datetime-日期与时间-yyyy-MM-dd HH:mm:ss,date-日期yyyy-MM-dd), 多值(multi)
    private String      extendValue;    // 扩展数据项
    private String      sysType;        // 作用业务与系统 all-所有
    private String      bizModule;      // 作用业务模块
    private String      gmtCreate;      // 创建时间
    private String      gmtModify;      // 更新时间
    private String      status;         // 状态：有效(enabled)、无效(disabled)、通过(pass)、失败(fail)、已发布(publish)
    private Integer     sortNum;        // 推荐排序，默认999，数值从小到大排序
    private String      operator;       // 操作人员
    private String      remark;         // 备注

}
