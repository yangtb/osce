package com.osce.orm.biz.training.caseku;

import com.osce.dto.biz.training.caseku.CaseDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: PfCaseDao
 * @Description: 病例库
 * @Author yangtongbin
 * @Date 2019-05-14
 */
public interface PfCaseDao {

    /**
     * 获取列表
     *
     * @param dto
     * @return
     */
    List<CobSpCase> listCase(CaseDto dto);

    /**
     * 获取总数
     *
     * @param dto
     * @return
     */
    Long countCase(CaseDto dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long addCase(CobSpCase dto);

    /**
     * 编辑
     *
     * @param dto
     * @return
     */
    int editCase(CobSpCase dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    int delCase(PfBachChangeStatusDto dto);

    /**
     * 考评表list
     *
     * @param dto
     * @return
     */
    List<CobScoreSheet> listSheet(CaseDto dto);

    /**
     * 保存考评表
     *
     * @param dto
     * @return
     */
    Long addSheet(CobScoreSheet dto);

    /**
     * 删除考评表
     *
     * @param dto
     * @return
     */
    int delSheet(PfBachChangeStatusDto dto);

    /**
     * 评分项列表
     *
     * @param dto
     * @return
     */
    List<CobScoreItem> listItem(CaseDto dto);

    /**
     * 评分项总数
     *
     * @param dto
     * @return
     */
    Long countItem(CaseDto dto);

    /**
     * 保存评分项
     *
     * @param dto
     * @return
     */
    int addItem(CobScoreItem dto);

    /**
     * 保存评分项
     *
     * @param dto
     * @return
     */
    int editItem(CobScoreItem dto);

    /**
     * 删除评分项
     *
     * @param dto
     * @return
     */
    int delItem(PfBachChangeStatusDto dto);

    /**
     * 保存评量表
     *
     * @param dto
     * @return
     */
    int addCobEvaluate(CobEvaluate dto);

    /**
     * 编辑评量表
     *
     * @param dto
     * @return
     */
    int editCobEvaluate(CobEvaluate dto);

    /**
     * 删除评量表
     *
     * @param dto
     * @return
     */
    int delCobEvaluate(PfBachChangeStatusDto dto);

    /**
     * 保存评量明细
     *
     * @param dto
     * @return
     */
    Long addCobEvaluateDetail(CobEvaluateDetail dto);

    /**
     * 编辑评量表
     *
     * @param dto
     * @return
     */
    int editCobEvaluateDetail(CobEvaluateDetail dto);

    /**
     * 删除评量明细
     *
     * @param dto
     * @return
     */
    int delCobEvaluateDetail(PfBachChangeStatusDto dto);

    /**
     * 删除评量明细
     *
     * @param dto
     * @return
     */
    int delCobEvaluateDetailByIdCob(PfBachChangeStatusDto dto);

    /**
     * 评量表list
     *
     * @param cdCobEvaluate
     * @return
     */
    List<CobEvaluate> listCobEvaluate(@Param("cdCobEvaluate") String cdCobEvaluate);

    /**
     * 评量明细list
     *
     * @param idCobEvaluate
     * @return
     */
    List<CobEvaluateDetail> listCobEvaluateDetail(@Param("idCobEvaluate") Long idCobEvaluate);


}
