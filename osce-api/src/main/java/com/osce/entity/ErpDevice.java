package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ErpDevice
 * @Description: 资源_设备管理
 * @Author yangtongbin
 * @Date 2019-05-12
 */
@Setter
@Getter
@ToString
public class ErpDevice implements Serializable {

    private static final long serialVersionUID = 1557675145612L;

    /**
    * 主键
    * 设备ID
    */
    private Long idDevice;

    /**
    * 机构ID
    */
    private Long idOrg;

    /**
    * 设备名称
    */
    private String naDevice;

    /**
    * 设备描述
    */
    private String desDevice;

    /**
    * 设备单位
    */
    private String sdDeviceUnit;

    /**
    * 耗材标志
    */
    private String fgConsumables;

    /**
    * 耗材警戒数量
    */
    private Float numWarn;

    /**
    * 库存数量
    */
    private Float unmStock;

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
