package com.osce.api.biz.training.res.model;

import com.osce.dto.biz.training.res.model.ModelDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.ErpDevice;
import com.osce.result.PageResult;

/**
 * @ClassName: PfModelService
 * @Description: 教学模块
 * @Author yangtongbin
 * @Date 2019-05-10
 */
public interface PfModelService {

    /**
     * 列表
     *
     * @param dto
     * @return
     */
    PageResult pageModels(ModelDto dto);

    /**
     * 增加
     *
     * @param dto
     * @return
     */
    Long addModel(ErpDevice dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    boolean delModel(PfBachChangeStatusDto dto);

   

}
