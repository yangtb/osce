package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

/**
 * @ClassName: ErpRoomDevice
 * @Description:资源_固定设备
 * @Author yangtongbin
 * @Date 2019-05-10
 */
@Setter
@Getter
@ToString
public class ErpRoomDevice implements Serializable {

    private static final long serialVersionUID = 1557497598949L;


    /**
     * 主键
     * 固定设备ID
     */
    private Long idRoomDevice;

    /**
     * 房间ID
     */
    private Long idRoom;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 11 监控摄像头 21 身份证识别设备 22 指纹识别设备 23 人脸识别设备 31 大屏显示设备 32 直立式一体机 33 壁挂式一体机
     */
    private String sdRoomDeviceCa;

    /**
     * 固定设备编号
     */
    private String cdRoomDevice;

    /**
     * 固定设备描述
     */
    private String desRoomDevice;

    /**
     * 0 未激活 1 已激活
     */
    private String fgActive;

    /**
     * 0 正常 1 删除
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
