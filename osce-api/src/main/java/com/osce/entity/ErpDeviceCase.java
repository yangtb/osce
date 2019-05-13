package com.osce.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

/**
 * @ClassName: ErpDeviceCase
 * @Description: 资源_设备实例
 * @Author yangtongbin
 * @Date 2019-05-12
 */
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErpDeviceCase implements Serializable {

    private static final long serialVersionUID = 1557675261229L;


    /**
     * 主键
     * 设备实例ID
     */
    private Long idDeviceCase;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 设备ID
     */
    private Long idDevice;

    /**
     * 实例编码
     */
    private String cdDeviceCase;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 型号
     */
    private String model;

    /**
     * 单价
     */
    private Float price;

    /**
     * 购入时间
     */
    private String gmtStoreIn;

    /**
     * 保修截止时间
     */
    private String gmtRepairEnd;

    /**
     * 维修电话
     */
    private String tellRepair;

    /**
     * 设备图片
     */
    private String picDiviceCase;

    /**
     * 0 正常 1 报废
     */
    private String fgScrap;

    /**
     * 报废时间
     */
    private String gmtScrap;

    /**
     * 报废描述
     */
    private String desScrap;

    /**
     * 0 正常 1 删除
     */
    private String fgValid;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建人员
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改人员
     */
    private String operator;

    /**
     * 修改时间
     */
    private Date gmtModify;


}
