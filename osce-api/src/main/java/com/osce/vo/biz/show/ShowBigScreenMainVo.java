package com.osce.vo.biz.show;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: ShowDto
 * @Description: 显示屏main
 * @Author yangtongbin
 * @Date 2019-06-30
 */
@Setter
@Getter
@ToString
public class ShowBigScreenMainVo implements Serializable {

    private static final long serialVersionUID = -6026943475970981541L;

    /**
     * 机构id
     */
    private Long idOrg;

    /**
     * 机构
     */
    private String naOrg;

    /**
     * 考试名称
     */
    private String naPlan;

    /**
     * 考试类型
     */
    private String fgReplan;

    /**
     * 考场
     */
    private String naArea;

    /**
     * 时间
     */
    private String planDay;

    /**
     * 开始时间
     */
    private String timeBegin;

    /**
     * 结束时间
     */
    private String timeEnd;

    /**
     * 计划id
     */
    private Long idPlan;

    /**
     * 考场
     */
    private Long idArea;

    /**
     * 时间：上午、下午
     */
    private float timeSection;

    /**
     * 待考区总数
     */
    private Long itemNum;

    /**
     * 每页数据量
     */
    private Integer limit;

}
