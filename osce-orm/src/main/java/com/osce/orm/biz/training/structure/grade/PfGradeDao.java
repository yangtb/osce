package com.osce.orm.biz.training.structure.grade;

import com.osce.dto.biz.training.structure.grade.GradeDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.OrgGrade;
import com.osce.vo.PfTreeSelectTableVo;
import com.osce.vo.biz.training.structure.grade.GradeVo;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long addGrade(OrgGrade dto);

    /**
     * 编辑
     *
     * @param dto
     * @return
     */
    int editGrade(OrgGrade dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    int delGrade(PfBachChangeStatusDto dto);

    /**
     * 保证当前学届只有一个
     *
     * @param idOrg   机构id
     * @param idGrade 学届id
     * @return
     */
    int updateOtherGrade(@Param("idOrg") Long idOrg,
                         @Param("idGrade") Long idGrade);

    /**
     * 获取当前机构所有学届
     *
     * @param idOrg 机构id
     * @return
     */
    List<GradeVo> listAllGrades(@Param("idOrg") Long idOrg);

    /**
     * 学届班级tree
     *
     * @param idOrg 机构id
     * @return
     */
    List<PfTreeSelectTableVo> listAllGradeTree(Long idOrg);

}
