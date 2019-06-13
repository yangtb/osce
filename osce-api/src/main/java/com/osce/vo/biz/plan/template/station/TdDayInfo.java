package com.osce.vo.biz.plan.template.station;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: TdDayInfo
 * @Description: 天数数据
 * @Author yangtongbin
 * @Date 2019-06-12
 */
@Setter
@Getter
@ToString
public class TdDayInfo implements Serializable {

    private static final long serialVersionUID = 1601242425858124107L;

    /**
     * 天数
     */
    private int dayNum;

    /**
     * 考场信息
     */
    private List<TdAreaInfo> areaData;
}
