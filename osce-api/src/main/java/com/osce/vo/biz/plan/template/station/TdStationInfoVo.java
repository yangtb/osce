package com.osce.vo.biz.plan.template.station;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: TdInsStationVo
 * @Description: 排站信息
 * @Author yangtongbin
 * @Date 2019-06-01
 */
@Setter
@Getter
@ToString
public class TdStationInfoVo implements Serializable {

    private static final long serialVersionUID = -467996255017463083L;

    /**
     * 1 上午  2下午
     */
    private float timeFlag;

    /**
     * 天数信息
     */
    private List<TdDayInfo> dayData;


}
