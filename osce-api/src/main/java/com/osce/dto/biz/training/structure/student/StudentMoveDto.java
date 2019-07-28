package com.osce.dto.biz.training.structure.student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: StudentMoveDto
 * @Description: 往届学员迁移
 * @Author yangtongbin
 * @Date 2019-07-28
 */
@Setter
@Getter
@ToString
public class StudentMoveDto implements Serializable {

    private static final long serialVersionUID = -425939886584074561L;

    /**
     * 用户ID
     */
    private List<Long> idUsers;

    /**
     * 部门ID
     */
    private List<Long> idDeparts;

}
