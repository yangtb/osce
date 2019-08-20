package com.osce.dto.biz.training.res.pick;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PfTpPickCaseDto
 * @Description: 计划_领料实例
 * @Author yangtongbin
 * @Date 2019-08-19
 */
@Setter
@Getter
@ToString
public class PfTpPickCaseDto implements Serializable {

    private static final long serialVersionUID = 7299889105134496275L;

    /**
     * 领料计划ID
     */
    private Long idTpPicking;

    /**
     * 设备实例ids
     */
    private List<Long> idDeviceCases;

}
