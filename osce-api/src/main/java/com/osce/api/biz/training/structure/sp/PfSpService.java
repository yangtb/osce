package com.osce.api.biz.training.structure.sp;

import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.OrgSpDefine;
import com.osce.result.PageResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @ClassName: PfSpService
 * @Description: SP接口
 * @Author yangtongbin
 * @Date 2019-05-07
 */
public interface PfSpService {

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
