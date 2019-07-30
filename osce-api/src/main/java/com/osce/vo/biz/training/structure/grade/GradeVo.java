package com.osce.vo.biz.training.structure.grade;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: OrgGrade
 * @Description: 组织_学届管理vo
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@Setter
@Getter
@ToString
public class GradeVo implements Serializable {

    private static final long serialVersionUID = -195462528039622689L;

    /**
     * 学届ID
     */
    private Long idGrade;

    /**
     * 学届名称
     */
    private String naGrade;

    /**
     * 学届描述
     */
    private String desGrade;

    /**
     * 班级数
     */
    private Integer gradeNum;

    /**
     * 学员数
     */
    private Integer studentNum;

    /**
     * 当前学届
     */
    private boolean currentGrade;

    /**
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 激活标志
     */
    private String fgActive;

}
