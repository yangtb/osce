package com.osce.api.biz.training.structure.sp;

import com.osce.dto.biz.training.structure.sp.SpDto;
import com.osce.dto.biz.training.structure.sp.UserSpDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.OrgSpDefine;
import com.osce.result.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: PfSpService
 * @Description: SP接口
 * @Author yangtongbin
 * @Date 2019-05-07
 */
public interface PfSpService {

    /**
     * sp列表
     *
     * @param dto
     * @return
     */
    PageResult pageSp(SpDto dto);

    /**
     * sp列表
     *
     * @param dto
     * @return
     */
    PageResult pageSpCache(SpDto dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long addSp(UserSpDto dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    boolean delSp(PfBachChangeStatusDto dto);

    /**
     * SP标签value
     *
     * @param userId 用户id
     * @return
     */
    List<Map<Long, String>> listSpTagValue(Long userId);

    /**
     * 标签列表
     *
     * @param idOrg 机构id
     * @return
     */
    List<OrgSpDefine> listSpTag(Long idOrg);

    /**
     * add 标签
     *
     * @param dto
     * @return
     */
    Long addSpTag(OrgSpDefine dto);

    /**
     * 删除标签
     *
     * @param dto
     * @return
     */
    boolean delSpTag(PfBachChangeStatusDto dto);

}
