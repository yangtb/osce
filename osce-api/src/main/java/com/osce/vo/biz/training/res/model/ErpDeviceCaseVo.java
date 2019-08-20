package com.osce.vo.biz.training.res.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: ErpDeviceVo
 * @Description: 设备
 * @Author yangtongbin
 * @Date 2019-05-12
 */
@Setter
@Getter
@ToString
public class ErpDeviceCaseVo implements Serializable {

    private static final long serialVersionUID = 5770708349611148703L;

    /**
     * 主键
     * 设备ID
     */
    private Long idDevice;

    /**
     * 设备名称
     */
    private String naDevice;

}
