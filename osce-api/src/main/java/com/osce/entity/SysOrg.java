package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: SysOrg
 * @Description: 机构实体
 * @Author yangtongbin
 * @Date 2018/9/20 15:30
 */
@Setter
@Getter
@ToString
public class SysOrg implements Serializable {

    private static final long serialVersionUID = 1537428265775L;

    /**
     * 主键
     * 机构ID
     */
    private Long idOrg;

    /**
     * 机构名称
     */
    private String naOrg;

    /**
     * 机构描述
     */
    private String desOrg;

    /**
     * 机构地址
     */
    private String address;

    /**
     * 电话
     */
    private String tell;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 布局图
     */
    private String examRoomUrl;

    /**
     * 上级机构
     */
    private Long idOrgPar;

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
