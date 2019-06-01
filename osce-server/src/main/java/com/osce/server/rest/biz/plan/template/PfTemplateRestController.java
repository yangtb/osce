package com.osce.server.rest.biz.plan.template;

import com.osce.api.biz.plan.template.PfTemplateService;
import com.osce.dto.biz.plan.template.*;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.enums.OperationTypeEnum;
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
 * @ClassName: PfTemplateRestController
 * @Description: 实训模板
 * @Author yangtongbin
 * @Date 2019-05-27
 */
@RestController
public class PfTemplateRestController extends BaseController {

    @Reference
    private PfTemplateService pfTemplateService;

    /**
     * 考站定义
     *
     * <pre>
     *     1、保存模板
     *     2、保存考场
     *     3、保存考站
     *     4、保存站点
     * </pre>
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/template/add")
    public ResultObject addTemplate(@RequestBody TdModelInfo dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getTdModel() != null, "模板");
        Assert.isTrue(CollectionUtils.size(dto.getTdAreas()) >= 1, "至少有一个考场");
        for (TdAreaDto tdAreaDto : dto.getTdAreas()) {
            Assert.isTrue(CollectionUtils.size(tdAreaDto.getTdStations()) >= 1, "至少有一个考站");
            for (TdStationDto tdStationDto : tdAreaDto.getTdStations()) {
                Assert.isTrue(CollectionUtils.size(tdStationDto.getTdSites()) >= 1, "至少有一个站点");
            }
        }
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("addTemplate", ResultObject.DATA_TYPE_OBJECT,
                pfTemplateService.addTemplate(dto));
    }

    /**
     * 编辑
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/template/edit")
    public ResultObject editTemplate(@RequestBody TdModelInfo dto) {
        /* 参数校验 */
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.createSuccess("editTemplate", ResultObject.DATA_TYPE_OBJECT,
                pfTemplateService.addTemplate(dto));

    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/plan/template/del")
    public ResultObject delTemplate(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfTemplateService.delTemplate(dto) ? ResultObject.createSuccess("delTemplate", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delTemplate", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 停用、启用
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/plan/template/updateStatus")
    public ResultObject updateTemplateStatus(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setOperationType(OperationTypeEnum.UPDATE_STATUS.getCode());
        dto.setExtId(CurrentUserUtils.getCurrentUserIdOrg());
        return pfTemplateService.delTemplate(dto) ? ResultObject.createSuccess("updateTemplateStatus", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("updateTemplateStatus", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 获取模板信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/plan/template/select")
    public ResultObject selectTdModelInfo(TemplateDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdModel() != null, "模板id");
        return ResultObject.createSuccess("selectTdModelInfo", ResultObject.DATA_TYPE_OBJECT,
                pfTemplateService.selectTdModelInfo(dto.getIdModel()));
    }

    /**
     * 获取排站信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/plan/template/selectStationInfo")
    public ResultObject selectStationInfo(TemplateDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdModel() != null, "模板id");
        return ResultObject.createSuccess("selectTdModelInfo", ResultObject.DATA_TYPE_LIST,
                pfTemplateService.selectStationInfo(dto.getIdModel()));
    }

    /**
     * 修改技能 - 排站信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/plan/template/editSkill")
    public ResultObject editSkill(TdInsStationDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdInsStation() != null, "排站id");
        Assert.isTrue(dto.getIdPaper() != null, "技能id");
        return ResultObject.createSuccess("editSkill", ResultObject.DATA_TYPE_LIST,
                pfTemplateService.editSkill(dto));
    }

    /**
     * 获取模拟排考信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/plan/template/selectStationDetail")
    public ResultObject selectStationDetail(TemplateDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdModel() != null, "模板id");
        return ResultObject.createSuccess("selectStationDetail", ResultObject.DATA_TYPE_LIST,
                pfTemplateService.selectStationDetail(dto.getIdModel()));
    }

}
