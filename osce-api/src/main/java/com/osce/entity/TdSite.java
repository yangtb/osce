package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TdSite
 * @Description: 模板_站点
 * @Author yangtongbin
 * @Date 2019-06-01
 */
@Setter
@Getter
@ToString
public class TdSite implements Serializable {

    private static final long serialVersionUID = 1559364093794L;


    /**
     * 主键
     * 站点ID
     */
    private Long idSite;

    /**
     * 考站ID
     */
    private Long idStation;

    /**
     * 考场ID
     */
    private Long idArea;

    /**
     * 房间ID
     */
    private Long idRoom;

    /**
     * 房间号
     */
    private String idRoomText;

    /**
     * 试卷ID
     */
    private Long idPaper;

    /**
     * 并发人数
     */
    private Integer numConcur;


}
