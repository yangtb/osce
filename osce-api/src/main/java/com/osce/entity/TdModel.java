package com.osce.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TdModel
 * @Description: 模板_模板管理
 * @Author yangtongbin
 * @Date 2019-05-25
 */
@Setter
@Getter
@ToString
public class TdModel implements Serializable {

    private static final long serialVersionUID = 1558791621208L;

    /**
     * 主键
     * 模板ID
     */
    private Long idModel;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 1 子模板 0 父模板
     */
    private String fgChild;

    /**
     * 模板名称
     */
    private String naModel;

    /**
     * 模板描述
     */
    private String desModel;

    /**
     * 考场数
     */
    private Integer numArea;

    /**
     * 最大学生数
     */
    private Integer numStudentMax;

    /**
     * 主考官权重
     */
    private Integer weightManager;

    /**
     * 考官权重
     */
    private Integer weightAssistant;

    /**
     * 中程考官权重
     */
    private Integer weightRemote;

    /**
     * 考试时隔（分钟）
     */
    private Integer minInterval;

    /**
     * 1 人员优先 2 设备优先
     */
    private String sdModelStrategy;

    /**
     * 1 出课考 2 月考 3 季考 4 入学考 5 毕业考 6 期中考 7 期末考 8 全科考试 9 综合考试
     */
    private String sdModelCa;

    /**
     * 上午开始时间
     */
    private String morningBegin;

    /**
     * 上午结束时间
     */
    private String morningEnd;

    /**
     * 下午开始时间
     */
    private String afternoonBegin;

    /**
     * 下午结束时间
     */
    private String afternoonEnd;

    /**
     * 0 未激活 1 已激活
     */
    private String fgActive;

    /**
     * 0 正常 1 删除
     * ,defaultVal:0
     */
    private String fgValid;

    /**
     * 排序
     * ,defaultVal:999
     */
    private Integer sort;
    /**
     * 创建人员
     */
    private String creator;

    /**
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 修改人员
     */
    private String operator;

    /**
     * 修改时间
     */
    private String gmtModify;


}
