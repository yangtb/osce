package com.osce.server.rest.biz.plan.template.score;

import com.osce.api.biz.plan.template.score.PfCaseScoreService;
import com.osce.dto.biz.training.caseku.CaseDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdScoreItem;
import com.osce.entity.TdScoreSheet;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
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
 * @ClassName: PfCaseRestController
 * @Description: 病例库
 * @Author yangtongbin
 * @Date 2019-05-15
 */
@RestController
public class PfCaseScoreRestController extends BaseController {

    @Reference
    private PfCaseScoreService pfCaseScoreService;
    
    /**
     * 考评表list
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/paper/sheet/list")
    public ResultObject listSheet(@RequestBody CaseDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("listSheet", ResultObject.DATA_TYPE_LIST,
                pfCaseScoreService.listSheet(dto));
    }

    /**
     * 新增考评表
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/paper/sheet/save")
    public ResultObject saveSheet(@RequestBody TdScoreSheet dto) {
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("saveSheet", ResultObject.DATA_TYPE_OBJECT,
                pfCaseScoreService.saveSheet(dto));

    }

    /**
     * 删除考评表
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/plan/paper/sheet/del")
    public ResultObject delSheet(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfCaseScoreService.delSheet(dto) ? ResultObject.createSuccess("delSheet", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delSheet", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 保存评分项
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/paper/item/save")
    public ResultObject saveItem(@RequestBody TdScoreItem dto) {
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("saveItem", ResultObject.DATA_TYPE_OBJECT,
                pfCaseScoreService.saveItem(dto));

    }

    /**
     * 删除评分项
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/plan/paper/item/del")
    public ResultObject delItem(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfCaseScoreService.delItem(dto) ? ResultObject.createSuccess("delItem", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delItem", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

}
