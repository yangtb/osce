package com.osce.dto.biz.plan.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfParamItemStoreDto
 * @Description: sp病例，在模板中另存
 * @Author yangtongbin
 * @Date 2019-06-01
 */
@Setter
@Getter
@ToString
public class PfSpCaseDto implements Serializable {

    private static final long serialVersionUID = 7019978138895086579L;

    /**
     * sp病例ID
     */
    private Long parId;

    /**
     * 错误编码
     */
    private Integer parCode;

    /**
     * 错误信息
     */
    private String parMsg;

}
