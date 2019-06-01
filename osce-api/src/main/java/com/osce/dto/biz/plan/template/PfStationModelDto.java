package com.osce.dto.biz.plan.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfPStationModelDto
 * @Description: 排站
 * @Author yangtongbin
 * @Date 2019-06-01
 */
@Setter
@Getter
@ToString
public class PfStationModelDto implements Serializable {

    private static final long serialVersionUID = -1707262746906227797L;


    /**
     * 对应模板ID
     */
    private Long parIdDemo;

    /**
     * 错误编码
     */
    private Integer parCode;

    /**
     * 错误信息
     */
    private String parMsg;

}
