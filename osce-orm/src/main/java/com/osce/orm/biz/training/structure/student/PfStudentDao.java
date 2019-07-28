package com.osce.orm.biz.training.structure.student;

import com.osce.dto.biz.training.structure.student.StudentDepartDto;
import com.osce.dto.biz.training.structure.student.StudentDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.OrgGrade;
import com.osce.vo.biz.training.structure.student.StudentDepartVo;
import com.osce.vo.biz.training.structure.student.StudentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: PfDeptDao
 * @Description: 学员管理
 * @Author yangtongbin
 * @Date 2019-05-06
 */
public interface PfStudentDao {

    /**
     * 部门tree
     *
     * @param dto
     * @return
     */
    List<StudentVo> listStudents(StudentDto dto);

    /**
     * 获取总数
     *
     * @param dto
     * @return
     */
    Long countStudent(StudentDto dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long addStudent(StudentDepartDto dto);

    /**
     * 编辑
     *
     * @param dto
     * @return
     */
    int editStudent(StudentDepartDto dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    int delStudent(PfBachChangeStatusDto dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    int delUser(PfBachChangeStatusDto dto);

    /**
     * 获取班级下所有学员信息
     *
     * @param dto
     * @return
     */
    List<StudentVo> listStudentByIdGrade(StudentDto dto);

    /**
     * 半机械下已有该学生
     *
     * @param idDepart
     * @param idUser
     * @return
     */
    boolean isExistDeptStu(@Param("idDepart") Long idDepart,
                           @Param("idUser") Long idUser);

    /**
     * 学生迁移
     *
     * @param idDepart
     * @param idUser
     * @return
     */
    int moveStudent(@Param("idDepart") Long idDepart,
                    @Param("idUser") Long idUser);

}
