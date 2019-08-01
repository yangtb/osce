package com.osce.orm.biz.plan.template;

import com.osce.dto.biz.plan.template.PfAddTpPaperDto;
import com.osce.dto.biz.plan.template.PfPaperDto;
import com.osce.dto.biz.plan.template.PfParamItemStoreDto;
import com.osce.dto.biz.plan.template.PfSpCaseDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.*;
import com.osce.vo.biz.plan.template.PaperItemTotalVo;
import com.osce.vo.biz.plan.template.PaperLeftVo;
import com.osce.vo.biz.plan.template.PfExamPaperVo;
import com.osce.vo.biz.plan.template.PfPaperVo;
import com.osce.vo.biz.plan.template.skill.TdSkillCaseVo;
import com.osce.vo.biz.plan.template.sp.TdSpCaseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: PfPaperDao
 * @Description: 试卷设计
 * @Author yangtongbin
 * @Date 2019-06-08
 */
public interface PfPaperDao {

    /**
     * 获取左侧列表
     *
     * @param idModel 模板id
     * @return
     */
    List<PaperLeftVo> listLeft(@Param("idModel") Long idModel);

    /**
     * 保存题集
     *
     * @param dto
     * @return
     */
    int addTdItemStore(TdItemStore dto);

    /**
     * 编辑题集
     *
     * @param dto
     * @return
     */
    int editTdItemStore(TdItemStore dto);

    /**
     * 增加目录
     *
     * @param idItemStoreFrom 来源题集ID
     * @param idItemStore     题集ID
     * @return
     */
    int addTdItemSection(@Param("idItemStoreFrom") Long idItemStoreFrom,
                         @Param("idItemStore") Long idItemStore);

    /**
     * 删除目录
     *
     * @param idItemStore 题集ID
     * @return
     */
    int delTdItemSection(@Param("idItemStore") Long idItemStore);

    /**
     * 获取试题题集
     *
     * @param dto
     * @return
     */
    TdItemStore selectTdItemStore(PfPaperDto dto);

    /**
     * 编辑目录
     *
     * @param list
     * @return
     */
    int editTdItemSections(List<Long> list);

    /**
     * add题型参数
     *
     * @param list
     * @return
     */
    int addTdItemArgTypes(@Param("list") List<TdItemArgType> list);

    /**
     * edit题型参数
     *
     * @param list
     * @return
     */
    int editTdItemArgTypes(@Param("list") List<TdItemArgType> list);

    /**
     * 删除题型参数
     *
     * @param idItemStore 题集ID
     * @return
     */
    int delTdItemArgTypes(@Param("idItemStore") Long idItemStore);

    /**
     * add难易程度比例
     *
     * @param list
     * @return
     */
    int addSdItemLevels(@Param("list") List<TdItemArgLevel> list);

    /**
     * edit难易程度比例
     *
     * @param list
     * @return
     */
    int editSdItemLevels(@Param("list") List<TdItemArgLevel> list);

    /**
     * 中心题库，在模板中另存
     *
     * @param dto
     */
    void callItemStoreIns(PfParamItemStoreDto dto);

    /**
     * 获取题型参数
     *
     * @param idItemStore 题集id
     * @return
     */
    List<TdItemArgType> listTdItemArgType(@Param("idItemStore") Long idItemStore);

    /**
     * 获取难易程度比例
     *
     * @param idItemStore 题集id
     * @return
     */
    List<TdItemArgLevel> listSdItemLevel(@Param("idItemStore") Long idItemStore);

    /**
     * 获取目录信息
     *
     * @param idItemStore 题集id
     * @return
     */
    List<TdItemSection> selectTdItemSection(@Param("idItemStore") Long idItemStore);

    /**
     * 题目总数
     *
     * @param idItemStore 题集id
     * @return
     */
    List<PaperItemTotalVo> listItemTotal(@Param("idItemStore") Long idItemStore);

    /**
     * 题目总数
     *
     * @param dto
     * @return
     */
    Long countItem(PfPaperDto dto);

    /**
     * 题目list
     *
     * @param dto
     * @return
     */
    List<TdItem> listItem(PfPaperDto dto);

    /**
     * 设置必考题
     *
     * @param idItemStore
     * @param status
     * @return
     */
    int updateItemByIdItemStore(@Param("idItemStore") Long idItemStore,
                                @Param("status") Integer status);

    /**
     * 设置必考题
     *
     * @param list
     * @param status
     * @return
     */
    int updateItemByIds(@Param("list") List<Long> list,
                        @Param("status") Integer status);

    /**
     * 生成试卷
     *
     * @param dto
     */
    void callGeneratePaper(PfParamItemStoreDto dto);

    /**
     * 试卷总数
     *
     * @param dto
     * @return
     */
    Long countPaper(PfPaperDto dto);

    /**
     * 试卷list
     *
     * @param dto
     * @return
     */
    List<PfPaperVo> listPaper(PfPaperDto dto);

    /**
     * 删除试卷
     *
     * @param dto
     * @return
     */
    int delPaper(PfBachChangeStatusDto dto);

    /**
     * add sp病例
     *
     * @param dto
     * @return
     */
    int addSpCase(TdSpCase dto);

    /**
     * 编辑sp病例
     *
     * @param dto
     * @return
     */
    int editSpCase(TdSpCase dto);

    /**
     * 另存病例
     *
     * @param dto
     */
    void callSpCase(PfSpCaseDto dto);

    /**
     * sp总数
     *
     * @param dto
     * @return
     */
    Long countSp(PfPaperDto dto);

    /**
     * sp list
     *
     * @param dto
     * @return
     */
    List<TdSpCaseVo> listSp(PfPaperDto dto);

    /**
     * 删除sp
     *
     * @param dto
     * @return
     */
    int delSp(PfBachChangeStatusDto dto);

    /**
     * add skill病例
     *
     * @param dto
     * @return
     */
    int addSkillCase(TdSkillCase dto);

    /**
     * 编辑Skill病例
     *
     * @param dto
     * @return
     */
    int editSkillCase(TdSkillCase dto);

    /**
     * 另存病例
     *
     * @param dto
     */
    void callSkillCase(PfSpCaseDto dto);

    /**
     * Skill总数
     *
     * @param dto
     * @return
     */
    Long countSkill(PfPaperDto dto);

    /**
     * Skill list
     *
     * @param dto
     * @return
     */
    List<TdSkillCaseVo> listSkill(PfPaperDto dto);

    /**
     * 删除Skill
     *
     * @param dto
     * @return
     */
    int delSkillCase(PfBachChangeStatusDto dto);

    /**
     * paper总数
     *
     * @param dto
     * @return
     */
    Long countExamPaper(PfPaperDto dto);

    /**
     * exam paper list
     *
     * @param dto
     * @return
     */
    List<PfExamPaperVo> listExamPaper(PfPaperDto dto);

    /**
     * 保存排站试卷
     *
     * @param dto
     * @return
     */
    int saveTdPaper(PfAddTpPaperDto dto);

    /**
     * 保存排站试卷 - 应用到该考站的所有时段
     *
     * @param idPlan    计划id
     * @param sdSkillCa 类型
     * @param idPaper   试卷id
     * @return
     */
    int saveTdAllPaper(@Param("idPlan") Long idPlan,
                       @Param("sdSkillCa") String sdSkillCa,
                       @Param("idPaper") Long idPaper);

    /**
     * 获取排站信息
     *
     * @param idInsStation
     * @return
     */
    TpInsStation selectTpInsStation(@Param("idInsStation") Long idInsStation);

}
