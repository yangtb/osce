package com.osce.orm.biz.training.structure.grade;

import com.osce.dto.biz.training.structure.grade.GradeDto;
import com.osce.vo.biz.training.structure.grade.GradeVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PfGradeDao
 * @Description: 学届管理dao
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@Repository
public interface PfGradeDao {

    /**
     * 获取学届列表
     *
     * @param dto
     * @return
     */
    List<GradeVo> listGrades(GradeDto dto);

    /**
     * 获取学届总数
     *
     * @param dto
     * @return
     */
    Long countGrade(GradeDto dto);

}
