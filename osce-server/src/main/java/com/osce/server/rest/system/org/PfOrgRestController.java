package com.osce.server.rest.system.org;

import com.osce.api.system.org.PfOrgService;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.SysOrg;
import com.osce.enums.OperationTypeEnum;
import com.osce.server.security.CurrentUserUtils;
import com.sm.open.care.core.ErrorCode;
import com.sm.open.care.core.ErrorMessage;
import com.sm.open.care.core.ResultObject;
import com.sm.open.care.core.enums.Status;
import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.care.core.utils.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: PfOrgRestController
 * @Description: 机构rest服务
 * @Author yangtongbin
 * @Date 2018/9/20 13:59
 */
@RestController
public class PfOrgRestController {

    @Reference
    private PfOrgService pfOrgService;

    /**
     * 机构树
     *
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ORG_MG','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/org/tree")
    public ResultObject selectOrgTree() {
        return ResultObject.createSuccess("selectOrgTree", ResultObject.DATA_TYPE_LIST,
                pfOrgService.selectOrgTree());
    }

    /**
     * 详情
     *
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ORG_MG','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/org/detail")
    public ResultObject selectOrgDetail(@RequestBody SysOrg dto) {
        return ResultObject.createSuccess("selectOrgDetail", ResultObject.DATA_TYPE_LIST,
                pfOrgService.selectOrgDetail(dto.getIdOrg()));
    }

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ORG_MG','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/org/save")
    public ResultObject addOrg(@RequestBody SysOrg dto) {
        /* 参数校验 */
        Assert.isTrue(StringUtils.isNotBlank(dto.getNaOrg()), "naOrg");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        if (dto.getIdOrg() == null) {
            dto.setCreator(CurrentUserUtils.getCurrentUsername());
        }
        return ResultObject.createSuccess("addOrg", ResultObject.DATA_TYPE_OBJECT, pfOrgService.addOrg(dto));
    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ORG_MG','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/org/del")
    public ResultObject delOrg(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "入参不能为空");
        dto.setStatus(YesOrNoNum.YES.getCode());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfOrgService.delOrg(dto) ? ResultObject.createSuccess("delOrg", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delOrg", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 停用、启用
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ORG_MG','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/org/updateStatus")
    public ResultObject updateOrgStatus(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setOperationType(OperationTypeEnum.UPDATE_STATUS.getCode());
        return pfOrgService.delOrg(dto) ? ResultObject.createSuccess("updateOrgStatus", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("updateOrgStatus", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

}
