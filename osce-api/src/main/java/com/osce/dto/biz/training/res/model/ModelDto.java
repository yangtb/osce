package com.osce.dto.biz.training.res.model;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: ModelDto
 * @Description: 教学模型
 * @Author yangtongbin
 * @Date 2019-05-12
 */
@Setter
@Getter
@ToString
public class ModelDto extends PageParam implements Serializable {

    private static final long serialVersionUID = 1884737987077356819L;

    /**
     * 机构id
     */
    private Long idOrg;

    /**
     * 设备ID
     */
    private Long idDevice;

    /**
     * 设备实例ID
     */
    private Long idDeviceCase;

}
