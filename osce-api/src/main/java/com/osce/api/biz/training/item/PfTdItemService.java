package com.osce.api.biz.training.item;

import com.osce.dto.biz.training.item.ItemBach;
import com.osce.dto.biz.training.item.ItemBachDto;
import com.osce.dto.biz.training.item.ItemDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdItem;
import com.osce.entity.TdItemOption;
import com.osce.entity.TdItemSection;
import com.osce.entity.TdItemStore;
import com.osce.result.PageResult;

import java.util.List;

/**
 * @ClassName: PfItemService
 * @Description: 题库管理接口
 * @Author yangtongbin
 * @Date 2019-05-14
 */
public interface PfTdItemService {

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
    Long addItem(TdItemStore dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    boolean delItem(PfBachChangeStatusDto dto);

    /**
     * 目录list
     *
     * @param idItemStore
     * @return
     */
    List<TdItemSection> listSection(Long idItemStore);

    /**
     * 保存目录
     *
     * @param dto
     * @return
     */
    Long saveSection(TdItemSection dto);

    /**
     * 删除目录
     *
     * @param dto
     * @return
     */
    boolean delSection(PfBachChangeStatusDto dto);

    /**
     * 题目列表
     *
     * @param dto
     * @return
     */
    PageResult pageItemManage(ItemDto dto);


    /**
     * 保存题目
     *
     * @param dto
     * @return
     */
    Long saveExam(TdItem dto);

    /**
     * 删除题目
     *
     * @param dto
     * @return
     */
    boolean delExam(PfBachChangeStatusDto dto);

    /**
     * 题目选项
     *
     * @param dto
     * @return
     */
    List<TdItemOption> listItemOption(ItemDto dto);

    /**
     * 删除题目选项
     *
     * @param dto
     */
    boolean delOption(PfBachChangeStatusDto dto);

    /**
     * 新增信息
     *
     * @param dto
     * @return
     */
    List<ItemBach> addBachItem(ItemBachDto dto);

}
