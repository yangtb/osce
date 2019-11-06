package com.osce.vo.biz.plan.manage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PlanSpVo
 * @Description: sp
 * @Author yangtongbin
 * @Date 2019-11-05
 */
@Setter
@Getter
@ToString
public class PlanSpStationVo implements Serializable {

    private static final long serialVersionUID = -7399423362576260975L;

    /**
     * 日期
     */
    private String planDay;

    /**
     * 1 上午  2下午
     */
    private Integer timeFlag;

    /**
     * 考场名称
     */
    private String naArea;

    /**
     * 考站名称
     */
    private String naStation;

    /**
     * 房间名称
     */
    private String idRoomText;

    /**
     * 试卷名称
     */
    private String idPaperText;

}
