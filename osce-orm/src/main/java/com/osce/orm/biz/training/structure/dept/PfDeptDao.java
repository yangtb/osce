package com.osce.orm.biz.training.structure.dept;

import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.OrgDepart;
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
     * @return
     */
    List<PfDeptZtreeVo> selectDeptTree();

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
     * @return
     */
    List<OrgDepart> listAllDept();

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
     * 删除部门
     *
     * @param dto
     * @return
     */
    int delDept(PfBachChangeStatusDto dto);


}
