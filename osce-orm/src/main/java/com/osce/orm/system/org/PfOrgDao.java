package com.osce.orm.system.org;

import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.SysOrg;
import com.osce.vo.PfTreeSelectVo;
import com.osce.vo.system.org.PfOrgZtreeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfOrgDao {

    /**
     * 机构tree
     *
     * @return
     */
    List<PfOrgZtreeVo> selectOrgTree();

    /**
     * 机构详情
     *
     * @param idOrg 机构id
     * @return
     */
    SysOrg selectOrgDetail(@Param("idOrg") Long idOrg);

    /**
     * 查询所有机构
     *
     * @return
     */
    List<SysOrg> listAllOrg();

    /**
     * 新增机构
     *
     * @param dto
     * @return
     */
    int addOrg(SysOrg dto);

    /**
     * 编辑机构
     *
     * @param dto
     * @return
     */
    int editOrg(SysOrg dto);

    /**
     * 删除机构
     *
     * @param dto
     * @return
     */
    int delOrg(PfBachChangeStatusDto dto);

    /**
     * 根据id查询机构信息
     *
     * @param idOrg 机构id
     * @return
     */
    SysOrg selectOrgInfoById(@Param("idOrg") Long idOrg);

    /**
     * 机构treeSelect
     *
     * @return
     */
    List<PfTreeSelectVo> selectOrgTreeSelect();

}
