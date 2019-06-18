package com.osce.dto.biz.plan.manage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfCopyModelDto
 * @Description: 模板另存
 * @Author yangtongbin
 * @Date 2019-06-01
 */
@Setter
@Getter
@ToString
public class PfCopyModelDto implements Serializable {

    private static final long serialVersionUID = 7019978138895086579L;

    /**
     * 计划ID
     */
    private Long parIdPlan;

    /**
     * 错误编码
     */
    private Integer parCode;

    /**
     * 错误信息
     */
    private String parMsg;

}
