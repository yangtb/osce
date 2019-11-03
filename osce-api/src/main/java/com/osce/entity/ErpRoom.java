package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: ErpRoom
 * @Description: 资源_房间管理
 * @Author yangtongbin
 * @Date 2019-05-10
 */
@Setter
@Getter
@ToString
public class ErpRoom implements Serializable {

    private static final long serialVersionUID = 1557497596098L;

    /**
     * 主键
     * 房间ID
     */
    private Long idRoom;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 房号
     */
    private String naRoom;

    /**
     * 描述
     */
    private String desRoom;

    /**
     * 布局图
     */
    private String picRoom;

    /**
     * 0 未激活 1 已激活
     */
    private String fgActive;

    /**
     * 是否理论站点 0 否 1 是
     */
    private String roomFlag;

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
    private String gmtCreate;

    /**
     * 修改人员
     */
    private String operator;

    /**
     * 修改时间
     */
    private String gmtModify;

    /**
     * 设备数量
     */
    private Long deviceNum;

    /**
     * 站点url
     */
    private String stationQrCodeUrl;

    /**
     * 理论考试首页url
     */
    private String testUrl;

}
