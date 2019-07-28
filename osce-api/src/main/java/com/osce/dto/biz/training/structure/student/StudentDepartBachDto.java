package com.osce.dto.biz.training.structure.student;

import com.osce.dto.user.login.RegisterDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: StudentDepartBachDto
 * @Description: 学员批量操作
 * @Author yangtongbin
 * @Date 2019-07-25
 */
@Setter
@Getter
@ToString
public class StudentDepartBachDto implements Serializable {

    private static final long serialVersionUID = -741176629717366120L;

    /**
     * 部门ID
     */
    private Long idDepart;

    /**
     * 学届ID
     */
    private Long idGrade;

    /**
     * 用户注册信息
     */
    private List<RegisterDto> users;

}
