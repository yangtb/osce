package com.osce.vo.biz.plan.manage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TpPlanAddVo
 * @Description: 计划_实训计划
 * @Author yangtongbin
 * @Date 2019-06-17
 */
@Setter
@Getter
@ToString
public class TpPlanAddVo implements Serializable {

    private static final long serialVersionUID = 1560751001310L;

    /**
     * 主键
     * 计划ID
     */
    private Long idPlan;

    /**
     * 模板ID
     */
    private String idModel;

}
