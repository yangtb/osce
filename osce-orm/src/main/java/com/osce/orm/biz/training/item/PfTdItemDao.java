package com.osce.orm.biz.training.item;

import com.osce.dto.biz.training.item.ItemDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: PfItemDao
 * @Description: 题库
 * @Author yangtongbin
 * @Date 2019-05-14
 */
public interface PfTdItemDao {

    /**
     * 获取列表
     *
     * @param dto
     * @return
     */
    List<TdItemStore> listItem(ItemDto dto);

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
    Long addItem(TdItemStore dto);

    /**
     * 编辑
     *
     * @param dto
     * @return
     */
    int editItem(TdItemStore dto);

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
    List<TdItemSection> listSection(@Param("idItemStore") Long idItemStore);

    /**
     * 保存目录
     *
     * @param dto
     * @return
     */
    Long addSection(TdItemSection dto);

    /**
     * 编辑目录
     *
     * @param dto
     * @return
     */
    Long editSection(TdItemSection dto);

    /**
     * 删除目录
     *
     * @param dto
     * @return
     */
    int delSection(PfBachChangeStatusDto dto);

    /**
     * 删除目录下题目
     *
     * @param list
     * @return
     */
    int delTdItemBySection(@Param("list") List<Long> list);

    /**
     * 获取题目列表
     *
     * @param dto
     * @return
     */
    List<TdItem> listItemManage(ItemDto dto);

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
    Long addExam(TdItem dto);

    /**
     * 编辑题目
     *
     * @param dto
     * @return
     */
    int editExam(TdItem dto);

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
    Long addItemOption(TdItemOption dto);

    /**
     * 编辑题目选项
     *
     * @param dto
     * @return
     */
    Long editItemOption(TdItemOption dto);

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
    Long selectTdItemStoreId(@Param("naItemStore") String naItemStore,
                             @Param("idOrg") Long idOrg);

    /**
     * 批量导入 - 获取目录id
     *
     * @param naItemSection 目录名称
     * @param idItemStore   题集id
     * @return
     */
    Long selectTdItemSectionId(@Param("naItemStore") String naItemSection,
                               @Param("idItemStore") Long idItemStore);

    /**
     * 批量导入 - 获取题目id
     *
     * @param idItemStore   题集id
     * @param idItemSection 目录id
     * @param mainItem      题干
     * @return
     */
    Long selectTdItem(@Param("idItemStore") Long idItemStore,
                      @Param("idItemSection") Long idItemSection,
                      @Param("mainItem") String mainItem);

    /**
     * 批量导入 - 获取题目选项id
     *
     * @param cdIteStr 选项
     * @param idItem   题目id
     * @return
     */
    Long selectTdItemOptionId(@Param("cdIteStr") String cdIteStr,
                              @Param("idItem") Long idItem);

    /**
     * 分组编码list
     *
     * @param dto
     * @return
     */
    List<String> listCdGroup(ItemDto dto);
}
