package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: OrgStudentDepart
 * @Description: 组织_学员部门关系表
 * @Author yangtongbin
 * @Date 2019-05-08
 */
@Setter
@Getter
@ToString
public class OrgStudentDepart implements Serializable {

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

    /**
     * 部门ID
     */
    private Long idDepart;

    /**
     * 学届ID
     */
    private Long idGrade;

}
