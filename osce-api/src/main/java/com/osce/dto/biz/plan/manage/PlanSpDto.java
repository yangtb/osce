package com.osce.dto.biz.plan.manage;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PlanSpVo
 * @Description: sp
 * @Author yangtongbin
 * @Date 2019-11-05
 */
@Setter
@Getter
@ToString
public class PlanSpDto extends PageParam implements Serializable {

    private static final long serialVersionUID = -7162523379807579111L;

    /**
     * 机构id
     */
    private Long idOrg;

    /**
     * 计划ID
     */
    private Long idPlan;

    /**
     * 条件
     */
    private List<PlanSpConditionDto> conditions;

}
