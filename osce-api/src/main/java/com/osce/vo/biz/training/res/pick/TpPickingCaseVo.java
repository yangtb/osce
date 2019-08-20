package com.osce.vo.biz.training.res.pick;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TpPickingCaseVo
 * @Description: 设备
 * @Author yangtongbin
 * @Date 2019-05-12
 */
@Setter
@Getter
@ToString
public class TpPickingCaseVo implements Serializable {

    private static final long serialVersionUID = 5770708349611148703L;

    /**
     * 设备ID
     */
    private Long value;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 选中
     */
    private String selected;

}
