package com.osce.api.biz.training.caseku;

import com.osce.dto.biz.training.caseku.CaseDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.*;
import com.osce.result.PageResult;

import java.util.List;

/**
 * @ClassName: PfCaseService
 * @Description: 病历接口
 * @Author yangtongbin
 * @Date 2019-05-15
 */
public interface PfCaseService {

    /**
     * 列表
     *
     * @param dto
     * @return
     */
    PageResult pageCase(CaseDto dto);

    /**
     * 增加
     *
     * @param dto
     * @return
     */
    String addCase(CobSpCase dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    boolean delCase(PfBachChangeStatusDto dto);

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
    Long saveSheet(CobScoreSheet dto);

    /**
     * 删除考评表
     *
     * @param dto
     * @return
     */
    boolean delSheet(PfBachChangeStatusDto dto);

    /**
     * 评分项列表
     *
     * @param dto
     * @return
     */
    PageResult pageItem(CaseDto dto);

    /**
     * 保存评分项
     *
     * @param dto
     * @return
     */
    Long saveItem(CobScoreItem dto);

    /**
     * 删除评分项
     *
     * @param dto
     * @return
     */
    boolean delItem(PfBachChangeStatusDto dto);

    /**
     * 保存评量表
     *
     * @param dto
     * @return
     */
    Long saveCobEvaluate(CobEvaluate dto);

    /**
     * 删除评量表
     *
     * @param dto
     * @return
     */
    boolean delCobEvaluate(PfBachChangeStatusDto dto);

    /**
     * 保存评量明细
     *
     * @param dto
     * @return
     */
    Long saveCobEvaluateDetail(CobEvaluateDetail dto);

    /**
     * 删除评量明细
     *
     * @param dto
     * @return
     */
    boolean delCobEvaluateDetail(PfBachChangeStatusDto dto);

    /**
     * 评量表list
     *
     * @param cdCobEvaluate
     * @return
     */
    List<CobEvaluate> listCobEvaluate(String cdCobEvaluate);

    /**
     * 评量明细list
     *
     * @param idCobEvaluate
     * @return
     */
    List<CobEvaluateDetail> listCobEvaluateDetail(Long idCobEvaluate);

}
