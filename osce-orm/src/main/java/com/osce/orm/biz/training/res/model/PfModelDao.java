package com.osce.orm.biz.training.res.model;

import com.osce.dto.biz.training.res.model.ModelDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.ErpDevice;

import java.util.List;

/**
 * @ClassName: PfModelDao
 * @Description: 管理
 * @Author yangtongbin
 * @Date 2019-05-10
 */
public interface PfModelDao {

    /**
     * 列表
     *
     * @param dto
     * @return
     */
    List<ErpDevice> listModels(ModelDto dto);

    /**
     * 总数
     *
     * @param dto
     * @return
     */
    Long countModel(ModelDto dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long addModel(ErpDevice dto);

    /**
     * 编辑
     *
     * @param dto
     * @return
     */
    int editModel(ErpDevice dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    int delModel(PfBachChangeStatusDto dto);


}
