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

    /**
     * 批量导入 - 获取题集id
     *
     * @param naItemStore 题集名称
     * @param idOrg       机构id
     * @return
     */
    Long selectIbmItemStoreId(@Param("naItemStore") String naItemStore,
                              @Param("idOrg") Long idOrg);

    /**
     * 批量导入 - 获取目录id
     *
     * @param naItemSection 目录名称
     * @param idItemStore   题集id
     * @return
     */
    Long selectIbmItemSectionId(@Param("naItemStore") String naItemSection,
                                @Param("idItemStore") Long idItemStore);

    /**
     * 批量导入 - 获取题目id
     *
     * @param idItemStore   题集id
     * @param idItemSection 目录id
     * @param mainItem      题干
     * @return
     */
    Long selectIbmItem(@Param("idItemStore") Long idItemStore,
                       @Param("idItemSection") Long idItemSection,
                       @Param("mainItem") String mainItem);

    /**
     * 批量导入 - 获取题目选项id
     *
     * @param cdIteStr 选项
     * @param idItem   题目id
     * @return
     */
    Long selectIbmItemOptionId(@Param("cdIteStr") String cdIteStr,
                               @Param("idItem") Long idItem);

}
