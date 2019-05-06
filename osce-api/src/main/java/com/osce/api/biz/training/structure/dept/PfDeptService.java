package com.osce.api.biz.training.structure.dept;

import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.OrgDepart;
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
     * @return
     */
    List<PfDeptZtreeVo> selectDeptTree();

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
     * @return
     */
    List<OrgDepart> listAllDept();

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

}
