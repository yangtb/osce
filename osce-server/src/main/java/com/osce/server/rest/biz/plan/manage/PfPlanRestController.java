package com.osce.server.rest.biz.plan.manage;

import com.osce.api.biz.plan.manage.PfPlanManageService;
import com.osce.dto.biz.plan.manage.PfCallPlanDto;
import com.osce.dto.biz.plan.manage.PfCopyModelDto;
import com.osce.dto.biz.plan.manage.PlanDto;
import com.osce.dto.biz.plan.manage.TpStudentDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TpPlan;
import com.osce.server.security.CurrentUserUtils;
import com.osce.vo.biz.plan.manage.TpPlanAddVo;
import com.sm.open.care.core.ErrorCode;
import com.sm.open.care.core.ErrorMessage;
import com.sm.open.care.core.ResultObject;
import com.sm.open.care.core.utils.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: PfPlanRestController
 * @Description: 计划管理
 * @Author yangtongbin
 * @Date 2019-06-17
 */
@RestController
public class PfPlanRestController {

    @Reference
    private PfPlanManageService pfPlanManageService;

    /**
     * 查询计划保存步骤
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/manage/select/currentStep")
    public ResultObject selectCurrentStep(@RequestBody PlanDto dto) {
        /* 参数校验 */
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");

        return ResultObject.createSuccess("selectCurrentStep", ResultObject.DATA_TYPE_OBJECT,
                pfPlanManageService.selectCurrentStep(dto));
    }

    /**
     * 新增计划
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/manage/add")
    public ResultObject addPlan(@RequestBody TpPlan dto) {
        /* 参数校验 */
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());

        boolean addFlag = dto.getIdPlan() == null ? true : false;
        Long idPlan = pfPlanManageService.addPlan(dto);
        PfCopyModelDto pfCopyModelDto = new PfCopyModelDto();
        pfCopyModelDto.setParIdPlan(idPlan);
        String idModel = pfPlanManageService.copyTdModel(pfCopyModelDto, addFlag);

        TpPlanAddVo tpPlanAddVo = new TpPlanAddVo();
        tpPlanAddVo.setIdPlan(idPlan);
        tpPlanAddVo.setIdModel(idModel);
        return ResultObject.createSuccess("addPlan", ResultObject.DATA_TYPE_OBJECT, tpPlanAddVo);
    }

    /**
     * 删除计划
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/plan/manage/del")
    public ResultObject delPlan(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfPlanManageService.delPlan(dto) ? ResultObject.createSuccess("delPlan", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delPlan", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 查询计划
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/manage/select")
    public ResultObject selectPlan(@RequestBody PlanDto dto) {
        /* 参数校验 */
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");

        return ResultObject.createSuccess("selectPlan", ResultObject.DATA_TYPE_OBJECT,
                pfPlanManageService.selectPlan(dto));
    }

    /**
     * 分配学员
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/manage/add/student")
    public ResultObject addStudent(@RequestBody TpStudentDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getIdUsers()), "idUsers");
        return pfPlanManageService.addStudent(dto) ? ResultObject.createSuccess("addStudent", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("addStudent", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 删除学员
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/assigned/student/del")
    public ResultObject delStudent(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getExtId() != null, "extId");
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        return pfPlanManageService.delStudent(dto) ? ResultObject.createSuccess("delStudent", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delStudent", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 实训计划排站
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/call/station/order")
    public ResultObject callStationPlanOrder(@RequestBody PfCallPlanDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getParIdPlan() != null, "parIdPlan");
        pfPlanManageService.callStationPlanOrder(dto);
        return ResultObject.createSuccess("callStationPlanOrder", ResultObject.DATA_TYPE_OBJECT, true);
    }

    /**
     * 生成领料计划
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/call/station/pick")
    public ResultObject callStationPlanPick(@RequestBody PfCallPlanDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getParIdPlan() != null, "parIdPlan");
        pfPlanManageService.callStationPlanPick(dto);
        return ResultObject.createSuccess("callStationPlanPick", ResultObject.DATA_TYPE_OBJECT, true);
    }

    /**
     * 发布计划
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/publish")
    public ResultObject publishPlan(@RequestBody PlanDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        return ResultObject.createSuccess("publishPlan", ResultObject.DATA_TYPE_OBJECT,
                pfPlanManageService.publishPlan(dto));
    }

    /**
     * 计划步骤校验
     * <p>
     * 1.考卷是否全部分配
     * 2.SP是否全部分配
     * 3.主考官是否全部分配
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/check/step")
    public ResultObject checkPlanStep(@RequestBody PlanDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        Assert.isTrue(dto.getCheckStep() != null, "checkStep");

        return ResultObject.createSuccess("checkPlanStep", ResultObject.DATA_TYPE_OBJECT,
                pfPlanManageService.checkPlanStep(dto));
    }

    /**
     * 结束计划
     * <p>
     * 1.更新计划状态
     * 2.更新实际完成时间
     * 3.清空该计划下的队列
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/plan/manage/end")
    public ResultObject endPlan(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfPlanManageService.endPlan(dto) ? ResultObject.createSuccess("delPlan", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("endPlan", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

}
