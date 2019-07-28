package com.osce.api.biz.plan.manage;

import com.osce.dto.biz.plan.manage.PfCallPlanDto;
import com.osce.dto.biz.plan.manage.PfCopyModelDto;
import com.osce.dto.biz.plan.manage.PlanDto;
import com.osce.dto.biz.plan.manage.TpStudentDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TpPlan;
import com.osce.result.PageResult;
import com.osce.vo.biz.plan.manage.AssignedStudentVo;

import java.util.List;

/**
 * @ClassName: PfPlanManageService
 * @Description: 计划管理
 * @Author yangtongbin
 * @Date 2019-06-16
 */
public interface PfPlanManageService {

    /**
     * 实训计划list
     *
     * @param dto
     * @return
     */
    PageResult pagePlan(PlanDto dto);

    /**
     * 新增计划
     *
     * @param dto
     * @return
     */
    Long addPlan(TpPlan dto);

    /**
     * 删除计划
     *
     * @param dto
     * @return
     */
    boolean delPlan(PfBachChangeStatusDto dto);

    /**
     * 另存模板
     *
     * @param dto
     * @param addFlag
     * @return
     */
    String copyTdModel(PfCopyModelDto dto, boolean addFlag);

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
     * @param dto
     * @return
     */
    List<AssignedStudentVo> listAssignedStudent(PlanDto dto);

    /**
     * 分配学员
     *
     * @param dto
     * @return
     */
    boolean addStudent(TpStudentDto dto);

    /**
     * 删除学员
     *
     * @param dto
     * @return
     */
    boolean delStudent(PfBachChangeStatusDto dto);

    /**
     * 实训计划排站
     *
     * @param dto
     * @return
     */
    void callStationPlanOrder(PfCallPlanDto dto);

    /**
     * 生成领料计划
     *
     * @param dto
     */
    void callStationPlanPick(PfCallPlanDto dto);

    /**
     * 领料清单
     *
     * @param dto
     * @return
     */
    PageResult pagePick(PlanDto dto);

    /**
     * 发布计划
     *
     * @param dto
     * @return
     */
    boolean publishPlan(PlanDto dto);

}
