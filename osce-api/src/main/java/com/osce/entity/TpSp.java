package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: TpSp
 * @Description: 计划_SP分配
 * @Author yangtongbin
 * @Date 2019-06-21
 */
@Setter
@Getter
@ToString
public class TpSp implements Serializable {

    private static final long serialVersionUID = 1561130724933L;

    /**
     * 主键
     * 分配SPID
     */
    private Long idTpSp;

    /**
     * 计划ID
     */
    private Long idPlan;

    /**
     * 考场ID
     */
    private Long idArea;

    /**
     * 考站ID
     */
    private Long idStation;

    /**
     * 实例说明：
     * 1 第一天上午  1.5 第一天下午
     * 2 第二天上午  2.5 第二天下午
     * 以此类推
     */
    private Float timeSection;

    /**
     * 房间ID
     */
    private Long idRoom;

    /**
     * 主键
     * SPID
     */
    private Long idUser;

    /**
     * userId集合
     */
    private List<Long> idUsers;

}
