package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
public class MessageTemplate implements Serializable {
    private String templateId;//模板ID
    /**
     * 模板编码
     */
    private String templateCode;
    private String templateName;//模板名称
    private String templateType;//模板类型sms=短信, email=邮件
    private String content;//模板内容
    private String isDeleted;//删除标示，N未删除 Y-已删除
    private String operator;//最后修改管理员ID
    private Date gmtCreate;//创建时间
    private Date gmtModify;//更新时间
}
