package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ErpDeviceRepair
 * @Description: 资源_设备维修记录
 * @Author yangtongbin
 * @Date 2019-05-12
 */
@Setter
@Getter
@ToString
public class ErpDeviceRepair implements Serializable {

    private static final long serialVersionUID = 1557675154488L;


    /**
     * 主键
     * 维修ID
     */
    private Long idRepair;

    /**
     * 设备实例ID
     */
    private Long idDeviceCase;

    /**
     * 维修结果
     */
    private String desRepair;

    /**
     * 维修时间
     */
    private Date gmtRepair;


}
