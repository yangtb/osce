package com.osce.orm.biz.plan.template.score;

import com.osce.dto.biz.training.caseku.CaseDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdScoreItem;
import com.osce.entity.TdScoreSheet;

import java.util.List;

/**
 * @ClassName: PfCaseDao
 * @Description: 病例库
 * @Author yangtongbin
 * @Date 2019-05-14
 */
public interface PfCaseScoreDao {

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
    Long addSheet(TdScoreSheet dto);

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
    List<TdScoreItem> listItem(CaseDto dto);

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
    int addItem(TdScoreItem dto);

    /**
     * 保存评分项
     *
     * @param dto
     * @return
     */
    int editItem(TdScoreItem dto);

    /**
     * 删除评分项
     *
     * @param dto
     * @return
     */
    int delItem(PfBachChangeStatusDto dto);

}
