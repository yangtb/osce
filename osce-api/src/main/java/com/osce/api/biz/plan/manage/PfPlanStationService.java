package com.osce.api.biz.plan.manage;

import com.osce.dto.biz.plan.manage.AssistantDto;
import com.osce.dto.biz.plan.manage.PlanDto;
import com.osce.dto.biz.plan.manage.PlanSpDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TpAssistant;
import com.osce.entity.TpSp;
import com.osce.entity.TpSpCache;
import com.osce.result.PageResult;
import com.osce.vo.biz.plan.manage.PlanPublishItemVo;
import com.osce.vo.biz.plan.manage.PlanSpStationVo;
import com.osce.vo.biz.plan.manage.PlanSpVo;
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

    /**
     * 发布清单 - 学员
     *
     * @param idPlan 计划id
     * @return
     */
    List<PlanPublishItemVo> listStudentItem(String idPlan);

    /**
     * 发布清单 - sp
     *
     * @param idPlan 计划id
     * @return
     */
    List<PlanPublishItemVo> listSpItem(String idPlan);


    /**
     * 发布清单 - 考官
     *
     * @param idPlan 计划id
     * @return
     */
    List<PlanPublishItemVo> listAssistantItem(String idPlan);

    /**
     * 获取sp站点
     *
     * @param idPlan 计划id
     * @return
     */
    List<PlanSpStationVo> listPlanSpStation(String idPlan);

    /**
     * 获取sp
     *
     * @param dto
     * @return
     */
    PageResult listPlanSp1(PlanSpDto dto);

    /**
     * 计划SP
     *
     * @param dto
     * @return
     */
    List<PlanSpVo> listPlanSpCache(PlanSpDto dto);

    /**
     * 添加sp缓存
     *
     * @param list
     * @return
     */
    boolean addPlanSpCache(List<TpSpCache> list);

    /**
     * 删除sp缓存
     *
     * @param dto
     * @return
     */
    boolean delPlanSpCache(PfBachChangeStatusDto dto);

}
