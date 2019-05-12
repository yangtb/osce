package com.osce.api.biz.training.structure.dept;

import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.OrgDepart;
import com.osce.vo.PfTreeSelectVo;
import com.osce.vo.biz.training.structure.dept.PfDeptZtreeVo;

import java.util.List;

/**
 * @ClassName: PfDeptService
 * @Description: 部门服务
 * @Author yangtongbin
 * @Date 2019-05-06
 */
public interface PfDeptService {

    /**
     * 部门树
     *
     * @param idOrg   机构id
     * @param idGrade 当前学届
     * @return
     */
    List<PfDeptZtreeVo> selectDeptTree(Long idOrg, Long idGrade);

    /**
     * 部门详情
     *
     * @param idDepart
     * @return
     */
    OrgDepart selectDeptDetail(Long idDepart);

    /**
     * 查询所有部门
     *
     * @param idOrg 机构id
     * @return
     */
    List<OrgDepart> listAllDept(Long idOrg);

    /**
     * 保存部门
     *
     * @param dto
     * @return
     */
    Long saveDept(OrgDepart dto);

    /**
     * 删除部门
     *
     * @param dto
     * @return
     */
    boolean delDept(PfBachChangeStatusDto dto);

    /**
     * treeSelect
     *
     * @param idOrg 机构id
     * @return
     */
    List<PfTreeSelectVo> selectDeptTreeSelect(Long idOrg);

}
