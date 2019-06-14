package com.osce.api.biz.plan.template.score;

import com.osce.dto.biz.training.caseku.CaseDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdScoreItem;
import com.osce.entity.TdScoreSheet;
import com.osce.result.PageResult;

import java.util.List;

/**
 * @ClassName: PfCaseService
 * @Description: 病历接口
 * @Author yangtongbin
 * @Date 2019-05-15
 */
public interface PfCaseScoreService {

    /**
     * 考评表list
     *
     * @param dto
     * @return
     */
    List<TdScoreSheet> listSheet(CaseDto dto);

    /**
     * 保存考评表
     *
     * @param dto
     * @return
     */
    Long saveSheet(TdScoreSheet dto);

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
    Long saveItem(TdScoreItem dto);

    /**
     * 删除评分项
     *
     * @param dto
     * @return
     */
    boolean delItem(PfBachChangeStatusDto dto);

}
