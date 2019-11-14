package com.osce.vo.biz.monitor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: MonitorRoomDevice
 * @Description: 监控设备
 * @Author yangtongbin
 * @Date 2019-11-13
 */
@Setter
@Getter
@ToString
public class MonitorRoomDevice implements Serializable {

    private static final long serialVersionUID = -273062570737505100L;

    /**
     * 主键
     * 固定设备ID
     */
    private Long idRoomDevice;

    /**
     * 固定设备编号
     */
    private String cdRoomDevice;

    /**
     * 设备地址
     */
    private String roomDeviceAddress;

}
