package com.osce.dto.biz.training.res.model;

import com.osce.entity.ErpDeviceRepair;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: RepairDto
 * @Description: 维修记录
 * @Author yangtongbin
 * @Date 2019-05-12
 */
@Setter
@Getter
@ToString
public class RepairDto implements Serializable {

    private static final long serialVersionUID = -5849952997964244195L;

    /**
     * 维修记录
     */
    private List<ErpDeviceRepair> list;

}
