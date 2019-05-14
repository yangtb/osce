package com.osce.api.biz.training.item;

import com.osce.dto.biz.training.item.ItemDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.IbmItem;
import com.osce.entity.IbmItemOption;
import com.osce.entity.IbmItemSection;
import com.osce.entity.IbmItemStore;
import com.osce.result.PageResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

    /**
     * 目录list
     *
     * @param idItemStore
     * @return
     */
    List<IbmItemSection> listSection(Long idItemStore);

    /**
     * 保存目录
     *
     * @param dto
     * @return
     */
    Long saveSection(IbmItemSection dto);

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
    Long saveExam(IbmItem dto);

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
    List<IbmItemOption> listItemOption(ItemDto dto);

    /**
     * 删除题目选项
     *
     * @param dto
     */
    boolean delOption(PfBachChangeStatusDto dto);

}
