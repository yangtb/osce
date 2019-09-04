package com.osce.orm.biz.plan.manage;

import com.osce.dto.biz.plan.manage.PfCallPlanDto;
import com.osce.dto.biz.plan.manage.PfCopyModelDto;
import com.osce.dto.biz.plan.manage.PlanDto;
import com.osce.dto.biz.plan.manage.TpStudentDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TpPlan;
import com.osce.vo.biz.plan.manage.AssignedStudentVo;
import com.osce.vo.biz.plan.manage.TdPlanStepCheckVo;
import com.osce.vo.biz.plan.manage.TpPickingVo;
import com.osce.vo.biz.plan.manage.TpPlanVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: PfPlanManageDao
 * @Description: 计划管理
 * @Author yangtongbin
 * @Date 2019-06-17
 */
public interface PfPlanManageDao {

    /**
     * 计划总数
     *
     * @param dto
     * @return
     */
    Long countPlanManage(PlanDto dto);

    /**
     * 计划list
     *
     * @param dto
     * @return
     */
    List<TpPlanVo> listPlanManage(PlanDto dto);

    /**
     * 计划总数
     *
     * @param dto
     * @return
     */
    Long countPlanManage1(PlanDto dto);

    /**
     * 计划list
     *
     * @param dto
     * @return
     */
    List<TpPlanVo> listPlanManage1(PlanDto dto);

    /**
     * 新增计划
     *
     * @param dto
     * @return
     */
    int addPlan(TpPlan dto);

    /**
     * 编辑计划
     *
     * @param dto
     * @return
     */
    int editPlan(TpPlan dto);

    /**
     * 删除计划
     *
     * @param dto
     * @return
     */
    int delPlan(PfBachChangeStatusDto dto);

    /**
     * 另存模板
     *
     * @param dto
     * @return
     */
    void copyTdModel(PfCopyModelDto dto);

    /**
     * 获取idModel
     *
     * @param idPlan 计划id
     * @return
     */
    String getIdModel(@Param("idPlan") Long idPlan);

    /**
     * 获取计划
     *
     * @param dto
     * @return
     */
    TpPlan selectPlan(PlanDto dto);

    /**
     * 已分配学员
     *
     * @param idPlan 计划id
     * @return
     */
    List<AssignedStudentVo> listAssignedStudent(@Param("idPlan") Long idPlan);

    /**
     * 分配学员
     *
     * @param dto
     * @return
     */
    int addStudent(TpStudentDto dto);

    /**
     * 删除学员
     *
     * @param dto
     * @return
     */
    int delStudent(PfBachChangeStatusDto dto);

    /**
     * 实训计划排站
     *
     * @param dto
     */
    void callStationPlanOrder(PfCallPlanDto dto);

    /**
     * 生成领料计划
     *
     * @param dto
     */
    void callStationPlanPick(PfCallPlanDto dto);

    /**
     * 领料清单总数
     *
     * @param dto
     * @return
     */
    Long countPick(PlanDto dto);

    /**
     * 领料清单
     *
     * @param dto
     * @return
     */
    List<TpPickingVo> listPick(PlanDto dto);

    /**
     * 发布计划
     *
     * @param idPlan 计划id
     * @return
     */
    int publishPlan(@Param("idPlan") Long idPlan);

    /**
     * 第7步
     *
     * @param idPlan 计划id
     * @return
     */
    boolean isExistStep7(@Param("idPlan") Long idPlan);

    /**
     * 第6步
     *
     * @param idPlan 计划id
     * @return
     */
    boolean isExistStep6(@Param("idPlan") Long idPlan);

    /**
     * 第5步
     *
     * @param idPlan 计划id
     * @return
     */
    boolean isExistStep5(@Param("idPlan") Long idPlan);

    /**
     * 第4步
     *
     * @param idPlan 计划id
     * @return
     */
    boolean isExistStep4(@Param("idPlan") Long idPlan);

    /**
     * 第3步
     *
     * @param idPlan 计划id
     * @return
     */
    boolean isExistStep3(@Param("idPlan") Long idPlan);

    /**
     * 第2步
     *
     * @param idPlan 计划id
     * @return
     */
    boolean isExistStep2(@Param("idPlan") Long idPlan);

    /**
     * 查询分配考卷页面未填信息
     *
     * @param idPlan 计划id
     * @return
     */
    List<TdPlanStepCheckVo> selectUnfilledPlanStep3(@Param("idPlan") Long idPlan);

    /**
     * 查询分配SP页面未填信息
     *
     * @param idPlan 计划id
     * @return
     */
    List<TdPlanStepCheckVo> selectUnfilledPlanStep4(@Param("idPlan") Long idPlan);

    /**
     * 查询分配考官页面未填信息
     *
     * @param idPlan 计划id
     * @return
     */
    List<TdPlanStepCheckVo> selectUnfilledPlanStep5(@Param("idPlan") Long idPlan);

}
