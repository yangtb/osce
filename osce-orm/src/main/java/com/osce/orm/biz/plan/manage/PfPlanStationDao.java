package com.osce.orm.biz.plan.manage;

import com.osce.dto.biz.plan.manage.AssistantDto;
import com.osce.dto.biz.plan.manage.PlanSpDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TpAssistant;
import com.osce.entity.TpSp;
import com.osce.entity.TpSpCache;
import com.osce.entity.UserInfo;
import com.osce.vo.biz.plan.manage.PlanPublishItemVo;
import com.osce.vo.biz.plan.manage.PlanSpStationVo;
import com.osce.vo.biz.plan.manage.PlanSpVo;
import com.osce.vo.biz.plan.template.station.PlanAssistant;
import com.osce.vo.biz.plan.template.station.PlanSp;
import com.osce.vo.biz.plan.template.station.TdStationInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: PfPlanStationDao
 * @Description: 计划 - 排站
 * @Author yangtongbin
 * @Date 2019-06-19
 */
public interface PfPlanStationDao {

    /**
     * 获取排站信息
     *
     * @param idPlan 计划id
     * @return
     */
    List<TdStationInfoVo> selectStationInfo(@Param("idPlan") Long idPlan);

    /**
     * 获取模拟排考信息
     *
     * @param idPlan 计划id
     * @return
     */
    List<TdStationInfoVo> selectStationSpDetail(@Param("idPlan") Long idPlan);


    /**
     * 获取计划分配sp
     *
     * @param idPlan
     * @param idArea
     * @param idStation
     * @param timeSection
     * @param idRoom
     * @return
     */
    List<PlanSp> listPlanSp(@Param("idPlan") Long idPlan,
                            @Param("idArea") Long idArea,
                            @Param("idStation") Long idStation,
                            @Param("timeSection") Float timeSection,
                            @Param("idRoom") Long idRoom);

    /**
     * 获取计划分配考官
     *
     * @param idPlan
     * @param idArea
     * @param idStation
     * @param timeSection
     * @param idRoom
     * @return
     */
    PlanAssistant selectPlanAssistant(@Param("idPlan") Long idPlan,
                                      @Param("idArea") Long idArea,
                                      @Param("idStation") Long idStation,
                                      @Param("timeSection") Float timeSection,
                                      @Param("idRoom") Long idRoom);

    /**
     * 保存考站sp
     *
     * @param dto
     * @return
     */
    int saveStationSp(TpSp dto);

    /**
     * 删除考站sp
     *
     * @param dto
     * @return
     */
    int delStationSp(TpSp dto);

    /**
     * 保存考站考官
     *
     * @param dto
     * @return
     */
    int saveStationAssistant(TpAssistant dto);

    /**
     * 删除考站考官
     *
     * @param dto
     * @return
     */
    int delStationAssistant(TpAssistant dto);

    /**
     * 考官数量
     *
     * @param dto
     * @return
     */
    Long countAssistant(AssistantDto dto);

    /**
     * 考官列表
     *
     * @param dto
     * @return
     */
    List<UserInfo> listAssistant(AssistantDto dto);

    /**
     * 发布清单 - 学员
     *
     * @param idPlan 计划id
     * @return
     */
    List<PlanPublishItemVo> listStudentItem(@Param("idPlan") String idPlan);

    /**
     * 发布清单 - sp
     *
     * @param idPlan 计划id
     * @return
     */
    List<PlanPublishItemVo> listSpItem(@Param("idPlan") String idPlan);

    /**
     * 发布清单 - 考官
     *
     * @param idPlan 计划id
     * @return
     */
    List<PlanPublishItemVo> listAssistantItem(@Param("idPlan") String idPlan);

    /**
     * 获取sp站点
     *
     * @param idPlan 计划id
     * @return
     */
    List<PlanSpStationVo> listPlanSpStation(@Param("idPlan") String idPlan);

    /**
     * 获取sp总数
     *
     * @param dto
     * @return
     */
    Long countPlanSp1(@Param("dto") PlanSpDto dto,
                      @Param("spSql") String spSql);

    /**
     * 获取sp
     *
     * @param dto
     * @return
     */
    List<PlanSpVo> listPlanSp1(@Param("dto") PlanSpDto dto,
                               @Param("spSql") String spSql);

    /**
     * 获取sp sql
     *
     * @param idOrg 机构id
     * @return
     */
    String getSpSql(@Param("idOrg") Long idOrg);

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
     * @param dto
     * @return
     */
    int addPlanSpCache(TpSpCache dto);

    /**
     * 是否存在sp缓存
     *
     * @param dto
     * @return
     */
    boolean isExistPlanSpCache(TpSpCache dto);

    /**
     * 删除sp缓存
     *
     * @param dto
     * @return
     */
    int delPlanSpCache(PfBachChangeStatusDto dto);


}
