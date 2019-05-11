package com.osce.vo.biz.training.structure.student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: StudentDepartVo
 * @Description: 组织_学员部门关系vo
 * @Author yangtongbin
 * @Date 2019-05-08
 */
@Setter
@Getter
@ToString
public class StudentDepartVo implements Serializable {

    private static final long serialVersionUID = 1557302773987L;

    /**
     * 主键
     * 关系ID
     */
    private Long idStudentDepart;

    /**
     * 用户ID
     */
    private Long idUser;


}
