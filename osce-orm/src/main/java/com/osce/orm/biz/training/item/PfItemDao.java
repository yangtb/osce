package com.osce.orm.biz.training.item;

import com.osce.dto.biz.training.item.ItemDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.IbmItemStore;

import java.util.List;

/**
 * @ClassName: PfItemDao
 * @Description: 题库
 * @Author yangtongbin
 * @Date 2019-05-14
 */
public interface PfItemDao {

    /**
     * 获取列表
     *
     * @param dto
     * @return
     */
    List<IbmItemStore> listItem(ItemDto dto);

    /**
     * 获取总数
     *
     * @param dto
     * @return
     */
    Long countItem(ItemDto dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long addItem(IbmItemStore dto);

    /**
     * 编辑
     *
     * @param dto
     * @return
     */
    int editItem(IbmItemStore dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    int delItem(PfBachChangeStatusDto dto);


}
