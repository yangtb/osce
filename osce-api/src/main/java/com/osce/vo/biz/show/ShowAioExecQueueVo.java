package com.osce.vo.biz.show;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: ShowAioExecQueueVo
 * @Description: 一体机 - 考试列表
 * @Author yangtongbin
 * @Date 2019-06-30
 */
@Setter
@Getter
@ToString
public class ShowAioExecQueueVo implements Serializable {

    private static final long serialVersionUID = -4493355522909125432L;

    /**
     * 队列ID
     */
    private Long idExecQueue;

    /**
     * 考站
     */
    private String naStation;

    /**
     * 房间号
     */
    private String naRoom;

    /**
     *
     */
    private String sdSkillCa;

    /**
     * 试卷id
     */
    private Long idPaper;

    /**
     * 试卷名称
     */
    private String  idPaperText;

    /**
     * 计划开始时间
     */
    private String planBegin;

    /**
     * 计划结束时间
     */
    private String planEnd;


}
