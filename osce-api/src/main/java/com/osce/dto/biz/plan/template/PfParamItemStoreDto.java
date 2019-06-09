package com.osce.dto.biz.plan.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfParamItemStoreDto
 * @Description: 中心题库，在模板中另存
 * @Author yangtongbin
 * @Date 2019-06-01
 */
@Setter
@Getter
@ToString
public class PfParamItemStoreDto implements Serializable {

    private static final long serialVersionUID = 7019978138895086579L;

    /**
     * 题集ID
     */
    private Long parIdItemStore;

    /**
     * 错误编码
     */
    private Integer parCode;

    /**
     * 错误信息
     */
    private String parMsg;

}
