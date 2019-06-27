package com.osce.api.biz.plan.manage;

import com.osce.dto.biz.plan.manage.AssistantDto;
import com.osce.dto.biz.plan.manage.PlanDto;
import com.osce.entity.TpAssistant;
import com.osce.entity.TpSp;
import com.osce.result.PageResult;
import com.osce.vo.biz.plan.template.station.PlanAssistant;
import com.osce.vo.biz.plan.template.station.PlanSp;
import com.osce.vo.biz.plan.template.station.TdStationInfoVo;

import java.util.List;

/**
 * @ClassName: PfPlanStationService
 * @Description: 计划 - 排站
 * @Author yangtongbin
 * @Date 2019-06-19
 */
public interface PfPlanStationService {

    /**
     * 获取排站信息
     *
     * @param idPlan 计划id
     * @return
     */
    List<TdStationInfoVo> selectStationInfo(Long idPlan);

    /**
     * 获取模拟排考信息
     *
     * @param dto 计划id
     * @return
     */
    List<TdStationInfoVo> selectStationSpDetail(PlanDto dto);

    /**
     * 保存考站sp
     *
     * @param dto
     * @return
     */
    boolean saveStationSp(TpSp dto);

    /**
     * 保存考站考官
     *
     * @param dto
     * @return
     */
    boolean saveStationAssistant(TpAssistant dto);

    /**
     * 考官列表
     *
     * @param dto
     * @return
     */
    PageResult pageAssistant(AssistantDto dto);

    /**
     * 考站考官
     *
     * @param dto
     * @return
     */
    PlanAssistant selectStationAssistant(TpAssistant dto);

    /**
     * 获取已分配sp
     *
     * @param dto
     * @return
     */
    List<PlanSp> selectStationSp(TpSp dto);


}
