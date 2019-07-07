package com.osce.dto.biz.show;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfAioStuRegisterDto
 * @Description: 待考登记
 * @Author yangtongbin
 * @Date 2019-06-01
 */
@Setter
@Getter
@ToString
public class PfAioStuRegisterDto implements Serializable {

    private static final long serialVersionUID = -7565306237593564038L;

    /**
     * 计划ID
     */
    private Long parIdPlan;

    /**
     * 考场ID
     */
    private Long parIdArea;

    /**
     * 时段
     */
    private float parTimeSection;

    /**
     * 学员身份证
     */
    private String parIdCard;

    /**
     * 错误编码
     */
    private Integer parCode;

    /**
     * 错误信息
     */
    private String parMsg;

}
