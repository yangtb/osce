package com.osce.orm.biz.training.item;

import com.osce.dto.biz.training.item.ItemDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.IbmItem;
import com.osce.entity.IbmItemOption;
import com.osce.entity.IbmItemSection;
import com.osce.entity.IbmItemStore;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 目录list
     *
     * @param idItemStore
     * @return
     */
    List<IbmItemSection> listSection(@Param("idItemStore") Long idItemStore);

    /**
     * 保存目录
     *
     * @param dto
     * @return
     */
    Long addSection(IbmItemSection dto);

    /**
     * 编辑目录
     *
     * @param dto
     * @return
     */
    Long editSection(IbmItemSection dto);

    /**
     * 删除目录
     *
     * @param dto
     * @return
     */
    int delSection(PfBachChangeStatusDto dto);

    /**
     * 获取题目列表
     *
     * @param dto
     * @return
     */
    List<IbmItem> listItemManage(ItemDto dto);

    /**
     * 获取题目总数
     *
     * @param dto
     * @return
     */
    Long countItemManage(ItemDto dto);

    /**
     * 新增题目
     *
     * @param dto
     * @return
     */
    Long addExam(IbmItem dto);

    /**
     * 编辑题目
     *
     * @param dto
     * @return
     */
    int editExam(IbmItem dto);

    /**
     * 删除题目
     *
     * @param idItem
     * @return
     */
    int delExamByIdItem(@Param("idItem") Long idItem);

    /**
     * 新增题目选项
     *
     * @param dto
     * @return
     */
    Long addItemOption(IbmItemOption dto);

    /**
     * 编辑题目选项
     *
     * @param dto
     * @return
     */
    Long editItemOption(IbmItemOption dto);

    /**
     * 题目选项
     *
     * @param dto
     * @return
     */
    List<IbmItemOption> listItemOption(ItemDto dto);

    /**
     * 删除题目选项
     *
     * @param dto
     * @return
     */
    int delOption(PfBachChangeStatusDto dto);

    /**
     * 删除题目选项
     *
     * @param idItem
     * @return
     */
    int delOptionByIdItem(@Param("idItem") Long idItem);

}
