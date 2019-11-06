package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TpSpCache
 * @Description: 计划_SP分配
 * @Author yangtongbin
 * @Date 2019-11-05
 */
@Setter
@Getter
@ToString
public class TpSpCache implements Serializable {

    private static final long serialVersionUID = -8399307085976817231L;

    /**
     * 主键
     * 暂存SPID
     */
    private Long idTpSpCache;

    /**
     * 计划ID
     */
    private Long idPlan;

    /**
     * spID
     */
    private Long idUser;

}
