package com.osce.vo.biz.plan.template.station;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PlanSp
 * @Description: 计划分配sp
 * @Author yangtongbin
 * @Date 2019-06-19
 */
@Setter
@Getter
@ToString
public class PlanSp implements Serializable {

    private static final long serialVersionUID = 1633746824969998117L;

    /**
     * 用户ID
     */
    private Long idUser;

    /**
     * 姓名
     */
    private String realName;

}
