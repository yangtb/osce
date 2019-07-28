package com.osce.api.biz.training.structure.student;

import com.osce.dto.biz.training.structure.student.StudentDepartDto;
import com.osce.dto.biz.training.structure.student.StudentDto;
import com.osce.dto.biz.training.structure.student.StudentMoveDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.result.PageResult;
import com.osce.vo.biz.training.structure.student.StudentDepartVo;
import com.osce.vo.biz.training.structure.student.StudentVo;

import java.util.List;

/**
 * @ClassName: PfStudentService
 * @Description: 学员接口
 * @Author yangtongbin
 * @Date 2019-05-07
 */
public interface PfStudentService {

    /**
     * 获取列表
     *
     * @param dto
     * @return
     */
    PageResult pageStudents(StudentDto dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    StudentDepartVo addStudent(StudentDepartDto dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    boolean delStudent(PfBachChangeStatusDto dto);

    /**
     * 获取班级下所有学员信息
     *
     * @param dto
     * @return
     */
    List<StudentVo> listStudentByIdGrade(StudentDto dto);

    /**
     * 往届学员迁移
     *
     * @param dto
     * @return
     */
    boolean moveStudent(StudentMoveDto dto);

}
