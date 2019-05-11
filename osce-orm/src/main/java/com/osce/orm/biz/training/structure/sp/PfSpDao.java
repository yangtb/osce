package com.osce.orm.biz.training.structure.sp;

import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.OrgSpDefine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: PfSpDao
 * @Description: sp管理
 * @Author yangtongbin
 * @Date 2019-05-06
 */
public interface PfSpDao {

    /**
     * 标签列表
     *
     * @param idOrg 机构id
     * @return
     */
    List<OrgSpDefine> listSpTag(@Param("idOrg") Long idOrg);

    /**
     * add 标签
     *
     * @param dto
     * @return
     */
    int addSpTag(OrgSpDefine dto);

    /**
     * 删除标签
     *
     * @param dto
     * @return
     */
    int delSpTag(PfBachChangeStatusDto dto);

}
