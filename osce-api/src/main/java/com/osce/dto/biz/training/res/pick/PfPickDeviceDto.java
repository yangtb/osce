package com.osce.dto.biz.training.res.pick;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfPickDeviceDto
 * @Description: 设备
 * @Author yangtongbin
 * @Date 2019-08-19
 */
@Setter
@Getter
@ToString
public class PfPickDeviceDto implements Serializable {

    private static final long serialVersionUID = 2067643646690656367L;

    /**
     * 领料计划ID
     */
    private Long idTpPicking;

    /**
     * 设备ID
     */
    private Long idDevice;

}
