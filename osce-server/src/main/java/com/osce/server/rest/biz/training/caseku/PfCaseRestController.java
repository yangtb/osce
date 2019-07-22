package com.osce.server.rest.biz.training.caseku;

import com.osce.api.biz.training.caseku.PfCaseService;
import com.osce.dto.biz.training.caseku.CaseDto;
import com.osce.dto.biz.training.caseku.CobEvaluateDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.*;
import com.osce.enums.OperationTypeEnum;
import com.osce.result.PageResult;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import com.sm.open.care.core.ErrorCode;
import com.sm.open.care.core.ErrorMessage;
import com.sm.open.care.core.ResultObject;
import com.sm.open.care.core.utils.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: PfCaseRestController
 * @Description: 病例库
 * @Author yangtongbin
 * @Date 2019-05-15
 */
@RestController
public class PfCaseRestController extends BaseController {

    @Reference
    private PfCaseService pfCaseService;

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/case/add")
    public ResultObject addCase(@RequestBody CobSpCase dto) {
        /* 参数校验 */
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("addCase", ResultObject.DATA_TYPE_OBJECT,
                pfCaseService.addCase(dto));
    }

    /**
     * 编辑
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_04', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/case/edit")
    public ResultObject editCase(@RequestBody CobSpCase dto) {
        /* 参数校验 */
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.createSuccess("editCase", ResultObject.DATA_TYPE_OBJECT, pfCaseService.addCase(dto));

    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/case/del")
    public ResultObject delCase(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfCaseService.delCase(dto) ? ResultObject.createSuccess("delCase", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delCase", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 停用、启用
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/case/updateStatus")
    public ResultObject updateCaseStatus(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setOperationType(OperationTypeEnum.UPDATE_STATUS.getCode());
        dto.setExtId(CurrentUserUtils.getCurrentUserIdOrg());
        return pfCaseService.delCase(dto) ? ResultObject.createSuccess("updateCaseStatus", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("updateCaseStatus", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 考评表list
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_04', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/case/sheet/list")
    public ResultObject listSheet(@RequestBody CaseDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("listSheet", ResultObject.DATA_TYPE_LIST,
                pfCaseService.listSheet(dto));
    }

    /**
     * 新增考评表
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_04', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/case/sheet/save")
    public ResultObject saveSheet(@RequestBody CobScoreSheet dto) {
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("saveSheet", ResultObject.DATA_TYPE_OBJECT,
                pfCaseService.saveSheet(dto));

    }

    /**
     * 删除考评表
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/case/sheet/del")
    public ResultObject delSheet(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfCaseService.delSheet(dto) ? ResultObject.createSuccess("delSheet", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delSheet", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 保存评分项
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_04', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/case/item/save")
    public ResultObject saveItem(@RequestBody CobScoreItem dto) {
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("saveItem", ResultObject.DATA_TYPE_OBJECT,
                pfCaseService.saveItem(dto));

    }

    /**
     * 删除评分项
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/case/item/del")
    public ResultObject delItem(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfCaseService.delItem(dto) ? ResultObject.createSuccess("delItem", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delItem", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 保存评量表
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_04', 'ROLE_01_05', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/cob/evaluate/save")
    public ResultObject saveCobEvaluate(@RequestBody CobEvaluate dto) {
        return ResultObject.createSuccess("saveCobEvaluate", ResultObject.DATA_TYPE_OBJECT,
                pfCaseService.saveCobEvaluate(dto));

    }

    /**
     * 删除评量表
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_01_05','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/cob/evaluate/del")
    public ResultObject delCobEvaluate(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfCaseService.delCobEvaluate(dto) ? ResultObject.createSuccess("delCobEvaluate", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delCobEvaluate", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 保存评量明细
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_01_05', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/cob/evaluate/detail/save")
    public ResultObject saveCobEvaluateDetail(@RequestBody CobEvaluateDetail dto) {
        return ResultObject.createSuccess("saveCobEvaluateDetail", ResultObject.DATA_TYPE_OBJECT,
                pfCaseService.saveCobEvaluateDetail(dto));

    }

    /**
     * 删除评量明细
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_01_05','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/cob/evaluate/detail/del")
    public ResultObject delCobEvaluateDetail(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfCaseService.delCobEvaluateDetail(dto) ? ResultObject.createSuccess("delCobEvaluateDetail", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delCobEvaluateDetail", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 评量表list
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_01_05','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/cob/evaluate/list")
    public PageResult listCobEvaluate(@RequestBody CobEvaluateDto dto) {
        /* 参数校验 */
        Assert.isTrue(StringUtils.isNotBlank(dto.getCdCobEvaluate()), "cdCobEvaluate");
        List<CobEvaluate> list = pfCaseService.listCobEvaluate(dto.getCdCobEvaluate());
        if (CollectionUtils.isEmpty(list)) {
            CobEvaluate cobEvaluate = new CobEvaluate();
            list.add(cobEvaluate);
        }
        return PageResult.create(list);
    }

    /**
     * 评量明细list
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_04','ROLE_01_05','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/cob/evaluate/detail/list")
    public PageResult listCobEvaluateDetail(@RequestBody CobEvaluateDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdCobEvaluate() != null, "idCobEvaluate");
        List<CobEvaluateDetail> list = pfCaseService.listCobEvaluateDetail(dto.getIdCobEvaluate());
        if (CollectionUtils.isEmpty(list)) {
            CobEvaluateDetail cobEvaluateDetail = new CobEvaluateDetail();
            list.add(cobEvaluateDetail);
        }
        return PageResult.create(list);
    }


}
