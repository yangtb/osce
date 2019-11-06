package com.osce.dto.biz.plan.manage;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PlanSpVo
 * @Description: sp
 * @Author yangtongbin
 * @Date 2019-11-05
 */
@Setter
@Getter
@ToString
public class PlanSpConditionDto extends PageParam implements Serializable {

    private static final long serialVersionUID = -7162523379807579111L;

    /**
     * 标签id
     */
    private Long idSpTag;

    /**
     * 类型 1且 2或
     */
    private Integer type;

    /**
     * condition 1等于 2包含
     */
    private Integer condition;

    /**
     * 值
     */
    private String value;

    /**
     * 标签名称
     */
    private String tagName;

}
