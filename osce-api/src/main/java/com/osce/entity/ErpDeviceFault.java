package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ErpDeviceFault
 * @Description: 资源_设备故障记录
 * @Author yangtongbin
 * @Date 2019-05-12
 */
@Setter
@Getter
@ToString
public class ErpDeviceFault implements Serializable {

    private static final long serialVersionUID = 1557675152086L;


    /**
     * 主键
     * 故障ID
     */
    private Long idDeviceFault;

    /**
     * 设备实例ID
     */
    private Long idDeviceCase;

    /**
     * 故障描述
     */
    private Integer desFault;

    /**
     * 故障时间
     */
    private Date gmtFault;


}
