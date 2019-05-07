package com.osce.server.rest.biz.training.structure.dept;

import com.osce.api.biz.training.structure.dept.PfDeptService;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.OrgDepart;
import com.osce.enums.OperationTypeEnum;
import com.osce.server.security.CurrentUserUtils;
import com.sm.open.care.core.ErrorCode;
import com.sm.open.care.core.ErrorMessage;
import com.sm.open.care.core.ResultObject;
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
 * @ClassName: PfDeptRestController
 * @Description: 部门rest服务
 * @Author yangtongbin
 * @Date 2018/9/20 13:59
 */
@RestController
public class PfDeptRestController {

    @Reference
    private PfDeptService pfDeptService;

    /**
     * 机构树
     *
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ORG_MG','ROLE_01_01_003','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/dept/tree")
    public ResultObject selectDeptTree(@RequestBody OrgDepart dto) {
        return ResultObject.createSuccess("selectDeptTree", ResultObject.DATA_TYPE_LIST,
                pfDeptService.selectDeptTree(dto.getIdOrg(), dto.getIdGrade()));
    }

    /**
     * 详情
     *
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ORG_MG','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/dept/detail")
    public ResultObject selectDeptDetail(@RequestBody OrgDepart dto) {
        return ResultObject.createSuccess("selectDeptDetail", ResultObject.DATA_TYPE_LIST,
                pfDeptService.selectDeptDetail(dto.getIdDepart()));
    }

    /**
     * 保存
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ORG_MG','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/dept/save")
    public ResultObject saveDept(@RequestBody OrgDepart dto) {
        /* 参数校验 */
        Assert.isTrue(StringUtils.isNotBlank(dto.getNaDepart()), "naDept");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        if (dto.getIdDepart() == null) {
            dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
            dto.setCreator(CurrentUserUtils.getCurrentUsername());
        }
        return ResultObject.createSuccess("addDept", ResultObject.DATA_TYPE_OBJECT, pfDeptService.saveDept(dto));
    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ORG_MG','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/dept/del")
    public ResultObject delDept(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "入参不能为空");
        dto.setStatus(YesOrNoNum.YES.getCode());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfDeptService.delDept(dto) ? ResultObject.createSuccess("delDept", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delDept", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 停用、启用
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ORG_MG','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/dept/updateStatus")
    public ResultObject updateDeptStatus(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setOperationType(OperationTypeEnum.UPDATE_STATUS.getCode());
        return pfDeptService.delDept(dto) ? ResultObject.createSuccess("updateDeptStatus", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("updateDeptStatus", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

}
