package com.osce.server.rest.biz.plan.manage;

import com.osce.api.biz.plan.manage.PfPlanStationService;
import com.osce.dto.biz.plan.manage.PlanDto;
import com.osce.dto.biz.plan.manage.PlanSpDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TpAssistant;
import com.osce.entity.TpSp;
import com.osce.entity.TpSpCache;
import com.osce.result.PageResult;
import com.osce.server.security.CurrentUserUtils;
import com.sm.open.care.core.ErrorCode;
import com.sm.open.care.core.ErrorMessage;
import com.sm.open.care.core.ResultObject;
import com.sm.open.care.core.utils.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: PfPlanStationRestController
 * @Description: 计划 - 排站
 * @Author yangtongbin
 * @Date 2019-06-17
 */
@RestController
public class PfPlanStationRestController {

    @Reference
    private PfPlanStationService pfPlanStationService;

    /**
     * 获取排站信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/station/selectStationInfo")
    public ResultObject selectStationInfo(@RequestBody PlanDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdPlan() != null, "计划id");
        return ResultObject.createSuccess("selectStationInfo", ResultObject.DATA_TYPE_OBJECT,
                pfPlanStationService.selectStationInfo(Long.valueOf(dto.getIdPlan())));
    }

    /**
     * 获取模拟排考信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/station/select/sp/detail")
    public ResultObject selectStationSpDetail(@RequestBody PlanDto dto) {
        if (StringUtils.isBlank(dto.getSpFlag())) {
            dto.setSpFlag("sp");
        }
        /* 参数校验 */
        Assert.isTrue(dto.getIdPlan() != null, "计划id");
        return ResultObject.createSuccess("selectStationSpDetail", ResultObject.DATA_TYPE_LIST,
                pfPlanStationService.selectStationSpDetail(dto));
    }

    /**
     * 保存考站sp
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/station/save/sp")
    public ResultObject saveStationSp(@RequestBody TpSp dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        Assert.isTrue(dto.getIdArea() != null, "idArea");
        Assert.isTrue(dto.getIdStation() != null, "idStation");
        Assert.isTrue(dto.getTimeSection() != null, "timeSection");
        Assert.isTrue(dto.getIdRoom() != null, "idRoom");
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getIdUsers()), "idUsers");
        return pfPlanStationService.saveStationSp(dto) ?
                ResultObject.createSuccess("saveStationSp", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("saveStationSp", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 保存考站考官
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/station/save/assistant")
    public ResultObject saveStationAssistant(@RequestBody TpAssistant dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        Assert.isTrue(dto.getIdArea() != null, "idArea");
        Assert.isTrue(dto.getIdStation() != null, "idStation");
        Assert.isTrue(dto.getIdRoom() != null, "idRoom");
        Assert.isTrue(dto.getTimeSection() != null, "timeSection");
        Assert.isTrue(dto.getIdUserManager() != null, "idUserManager");
        return pfPlanStationService.saveStationAssistant(dto) ?
                ResultObject.createSuccess("saveStationAssistant", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("saveStationAssistant", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 获取考站考官
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/station/select/assistant")
    public ResultObject selectStationAssistant(@RequestBody TpAssistant dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        Assert.isTrue(dto.getIdArea() != null, "idArea");
        Assert.isTrue(dto.getIdStation() != null, "idStation");
        Assert.isTrue(dto.getIdRoom() != null, "idRoom");
        Assert.isTrue(dto.getTimeSection() != null, "timeSection");
        return ResultObject.createSuccess("selectStationAssistant", ResultObject.DATA_TYPE_LIST,
                pfPlanStationService.selectStationAssistant(dto));
    }

    /**
     * 获取考站SP
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/station/select/sp")
    public ResultObject selectStationSp(@RequestBody TpSp dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        Assert.isTrue(dto.getIdArea() != null, "idArea");
        Assert.isTrue(dto.getIdStation() != null, "idStation");
        Assert.isTrue(dto.getIdRoom() != null, "idRoom");
        Assert.isTrue(dto.getTimeSection() != null, "timeSection");
        return ResultObject.createSuccess("selectStationSp", ResultObject.DATA_TYPE_LIST,
                pfPlanStationService.selectStationSp(dto));
    }

    /**
     * 添加计划sp缓存
     *
     * @param list
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @PostMapping("/pf/r/plan/sp/cache/add")
    public ResultObject addPlanSpCache(@RequestBody List<TpSpCache> list) {
        Assert.isTrue(CollectionUtils.isNotEmpty(list), "list");
        return ResultObject.createSuccess("addPlanSpCache", ResultObject.DATA_TYPE_OBJECT,
                pfPlanStationService.addPlanSpCache(list));
    }

    /**
     * 删除计划sp缓存
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/sp/cache/del")
    public ResultObject delPlanSpCache(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        Assert.isTrue(dto.getExtId() != null, "extId");
        return pfPlanStationService.delPlanSpCache(dto) ? ResultObject.createSuccess("delPlanSpCache", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delPlanSpCache", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * sp列表
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_02_001','ROLE_SUPER')")
    @PostMapping("/pf/r/plan/sp/list")
    public PageResult listPlanSp(@RequestBody PlanSpDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfPlanStationService.listPlanSp1(dto);
    }

}
