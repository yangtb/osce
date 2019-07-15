package com.osce.dto.biz.show;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfAioStationDto
 * @Description: 房间的考试信息
 * @Author yangtongbin
 * @Date 2019-06-01
 */
@Setter
@Getter
@ToString
public class PfAioStationDto implements Serializable {

    private static final long serialVersionUID = 7977740239415899984L;

    /**
     * 计划ID
     */
    private Long idPlan;

    /**
     * 考场ID
     */
    private Long idArea;

    /**
     * 时段
     */
    private float timeSection;

    /**
     * 房间ID
     */
    private Long idRoom;

    /**
     * 每页数据量
     */
    private Integer limit;


}
