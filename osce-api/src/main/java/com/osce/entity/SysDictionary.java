package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class SysDictionary implements Serializable {

    private static final long serialVersionUID = -6793337107156687509L;

    private Long        id;             // ID
    private String      groupName;      // 分组名称
    private String      groupCode;      // 字典分组编码，用于区分一组字典
    private String      dictName;       // 显示名称
    private String      dictCode;       // 数据值编码
    private String      extValue ;      // 扩展数据项
    private String      gmtCreate;      // 创建时间
    private String      gmtModify;      // 更新时间
    private String      status;         // 状态：有效(enabled)、无效(disabled)、通过(pass)、失败(fail)、已发布(publish)
    private Integer     sortNum;        // 推荐排序，默认999，数值从小到大排序
    private String      operator;       // 操作人员
    private String      remark;         // 备注
    private String      sysType;        // 作用业务与系统 all-所有

}
