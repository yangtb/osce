package com.osce.orm.biz.training.structure.dept;

import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.dto.system.org.PfDeptDto;
import com.osce.entity.OrgDepart;
import com.osce.vo.PfTreeSelectVo;
import com.osce.vo.biz.training.structure.dept.PfDeptZtreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: PfDeptDao
 * @Description: 部门管理
 * @Author yangtongbin
 * @Date 2019-05-06
 */
public interface PfDeptDao {

    /**
     * 部门tree
     *
     * @param idOrg   机构id
     * @param idGrade 当前学届
     * @return
     */
    List<PfDeptZtreeVo> selectDeptTree(@Param("idOrg") Long idOrg,
                                       @Param("idGrade") Long idGrade);

    /**
     * 部门详情
     *
     * @param idDepart 部门id
     * @return
     */
    OrgDepart selectDeptDetail(@Param("idDepart") Long idDepart);

    /**
     * 查询所有部门
     *
     * @param idOrg 机构id
     * @return
     */
    List<OrgDepart> listAllDept(@Param("idOrg") Long idOrg);

    /**
     * 新增部门
     *
     * @param dto
     * @return
     */
    int addDept(OrgDepart dto);

    /**
     * 编辑部门
     *
     * @param dto
     * @return
     */
    int editDept(OrgDepart dto);

    /**
     * 学届是否有变化
     *
     * @param idDepart 部门id
     * @param idGrade  学届
     * @return
     */
    boolean changeGrade(@Param("idDepart") Long idDepart,
                        @Param("idGrade") Long idGrade);

    /**
     * 删除部门
     *
     * @param dto
     * @return
     */
    int delDept(PfBachChangeStatusDto dto);

    /**
     * 获取部门下维护学员数目
     *
     * @param dto
     * @return
     */
    Integer countDeptByIds(PfBachChangeStatusDto dto);

    /**
     * treeSelect
     *
     * @param dto 机构id
     * @return
     */
    List<PfTreeSelectVo> selectDeptTreeSelect(PfDeptDto dto);
}
