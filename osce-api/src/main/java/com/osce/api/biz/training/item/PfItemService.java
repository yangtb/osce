package com.osce.api.biz.training.item;

import com.osce.dto.biz.training.item.ItemDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.IbmItemStore;
import com.osce.result.PageResult;

/**
 * @ClassName: PfItemService
 * @Description: 题库管理接口
 * @Author yangtongbin
 * @Date 2019-05-14
 */
public interface PfItemService {

    /**
     * 获取列表
     *
     * @param dto
     * @return
     */
    PageResult pageItem(ItemDto dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long addItem(IbmItemStore dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    boolean delItem(PfBachChangeStatusDto dto);
}
