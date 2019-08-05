package com.osce.vo.biz.statistics;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: StatisticsStuScoreVo
 * @Description: 学生成绩
 * @Author yangtongbin
 * @Date 2019-08-05
 */
@Setter
@Getter
@ToString
public class StatisticsStuScoreVo implements Serializable {

    private static final long serialVersionUID = 1013439257397874866L;

    /**
     * 执行ID
     */
    private Long idExec;

    /**
     * 部门名称
     */
    private String naDepart;
    /**
     * 姓名
     */
    private String realName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 手机
     */
    private String phoneNo;

    /**
     * 考场名称
     */
    private String naArea;

    /**
     * 技能病例类别
     */
    private String sdSkillCa;

    /**
     * 科目
     */
    private String sdStationCa;

    /**
     * 科目text
     */
    private String sdStationCaText;

    /**
     * 试卷id
     */
    private Long idPaper;

    /**
     * 试卷名称
     */
    private String idPaperText;

    /**
     * 总成绩
     */
    private Integer scoreSum;


}
