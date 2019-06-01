package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TdInsStationDetail
 * @Description: 模板_排站明细
 * @Author yangtongbin
 * @Date 2019-05-25
 */
@Setter
@Getter
@ToString
public class TdInsStationDetail implements Serializable {

    private static final long serialVersionUID = 1558791619183L;

    /**
     * 主键
     * 排站明细ID
     */
    private Long idInsStationDetail;

    /**
     * 排站ID
     */
    private Long idInsStation;

    /**
     * 虚拟学生编码
     */
    private String cdInventedStudent;

    /**
     * 开始时间
     */
    private String timeBegin;

    /**
     * 结束时间
     */
    private String timeEnd;

}
