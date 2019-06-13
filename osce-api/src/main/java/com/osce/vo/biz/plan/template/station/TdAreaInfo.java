package com.osce.vo.biz.plan.template.station;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: TdAreaInfo
 * @Description: 考场数据
 * @Author yangtongbin
 * @Date 2019-06-12
 */
@Setter
@Getter
@ToString
public class TdAreaInfo implements Serializable {

    private static final long serialVersionUID = 1601242425858124107L;

    /**
     * 考场ID
     */
    private Long idArea;

    /**
     * 考场名称
     */
    private String naArea;

    /**
     * 考站信息
     */
    private List<TdStation> stationData;

}
