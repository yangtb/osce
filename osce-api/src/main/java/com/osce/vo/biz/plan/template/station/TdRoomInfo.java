package com.osce.vo.biz.plan.template.station;

import com.osce.entity.TdInsStationDetail;
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
public class TdRoomInfo implements Serializable {

    private static final long serialVersionUID = 1601242425858124107L;

    /**
     * 主键
     * 排站ID
     */
    private Long idInsStation;

    /**
     * 技能ID
     */
    private Long idPaper;

    /**
     * 技能名称
     */
    private String idPaperText;

    /**
     * 评分表ID
     */
    private Long idScoreSheet;

    /**
     * 房间ID
     */
    private Long idRoom;

    /**
     * 房间名称
     */
    private String idRoomText;

    /**
     * 识别序列号
     */
    private String sq;

    /**
     * SP
     */
    private List<TdInsStationDetail> spList;

    /**
     * 分配的sp
     */
    private List<PlanSp> planSp;

    /**
     * 分配的考官
     */
    private PlanAssistant planAssistant;

}
