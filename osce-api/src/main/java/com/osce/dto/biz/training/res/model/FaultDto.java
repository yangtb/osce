package com.osce.dto.biz.training.res.model;

import com.osce.entity.ErpDeviceFault;
import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: FaultDto
 * @Description: 故障记录
 * @Author yangtongbin
 * @Date 2019-05-12
 */
@Setter
@Getter
@ToString
public class FaultDto implements Serializable {

    private static final long serialVersionUID = -5849952997964244195L;

    /**
     * 故障记录
     */
    private List<ErpDeviceFault> list;

}
