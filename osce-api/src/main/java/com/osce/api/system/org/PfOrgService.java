package com.osce.api.system.org;

import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.SysOrg;
import com.osce.vo.system.org.PfOrgZtreeVo;

import java.util.List;

/**
 * @ClassName: PfOrgService
 * @Description: 机构服务
 * @Author yangtongbin
 * @Date 2019-05-05
 */
public interface PfOrgService {

    /**
     * 机构树
     *
     * @return
     */
    List<PfOrgZtreeVo> selectOrgTree();

    /**
     * 机构详情
     *
     * @param idOrg
     * @return
     */
    SysOrg selectOrgDetail(Long idOrg);

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
    Long addOrg(SysOrg dto);

    /**
     * 编辑机构
     *
     * @param dto
     * @return
     */
    boolean editOrg(SysOrg dto);

    /**
     * 删除机构
     *
     * @param dto
     * @return
     */
    boolean delOrg(PfBachChangeStatusDto dto);


    /**
     * 根据id查询机构信息
     *
     * @param idOrg 机构id
     * @return
     */
    SysOrg selectOrgInfoById(Long idOrg);


}
