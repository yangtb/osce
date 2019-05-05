package com.osce.api.biz.training.structure.grade;

import com.osce.dto.biz.training.structure.grade.GradeDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.OrgGrade;
import com.osce.result.PageResult;

/**
 * @ClassName: PfGradeService
 * @Description: 学届管理
 * @Author yangtongbin
 * @Date 2019-05-04
 */
public interface PfGradeService {

    /**
     * 获取列表
     *
     * @param dto
     * @return
     */
    PageResult pageGrades(GradeDto dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long addGrade(OrgGrade dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    boolean delGrade(PfBachChangeStatusDto dto);
}
