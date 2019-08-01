package com.osce.server.rest.biz.plan.template;

import com.osce.api.biz.plan.template.PfPaperService;
import com.osce.dto.biz.plan.template.*;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdItemStore;
import com.osce.entity.TdSkillCase;
import com.osce.entity.TdSpCase;
import com.osce.enums.OperationTypeEnum;
import com.osce.enums.SysDicGroupEnum;
import com.osce.server.security.CurrentUserUtils;
import com.osce.server.utils.EnumUtil;
import com.osce.vo.biz.plan.template.PaperLeftVo;
import com.osce.vo.system.dic.PfDicCache;
import com.sm.open.care.core.ErrorCode;
import com.sm.open.care.core.ErrorMessage;
import com.sm.open.care.core.ResultObject;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PfExamPaperRestController
 * @Description: 设计考卷
 * @Author yangtongbin
 * @Date 2019-06-01
 */
@RestController
public class PfExamPaperRestController {

    @Reference
    private PfPaperService pfPaperService;

    @Resource
    private EnumUtil enumUtil;

    /**
     * 获取试卷左侧列表
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @PostMapping(value = "/pf/p/plan/paper/select/leftList")
    public ResultObject listLeft(@RequestBody TemplateDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdModel() != null, "模板id");
        List<PaperLeftVo> paperLeftVos = pfPaperService.listLeft(dto.getIdModel());
        List<PfDicCache> dicCaches = enumUtil.getEnumList(SysDicGroupEnum.SD_STATION_CA.getCode());
        for (PaperLeftVo paperLeftVo : paperLeftVos) {
            for (PfDicCache pfDicCache : dicCaches) {
                if (pfDicCache.getDictCode().equals(paperLeftVo.getSdStationCa())) {
                    paperLeftVo.setSdStationCaText(pfDicCache.getDictName());
                    continue;
                }
            }
        }
        return ResultObject.createSuccess("listLeft", ResultObject.DATA_TYPE_LIST, paperLeftVos);
    }

    /**
     * 增加试题题集
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/p/plan/paper/add/item")
    public ResultObject addTdItemStore(@RequestBody TdItemStore dto) {
        /* 参数校验 */
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.createSuccess("addTdItemStore", ResultObject.DATA_TYPE_OBJECT,
                pfPaperService.addTdItemStore(dto));
    }

    /**
     * 另存试题题集
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/p/plan/paper/copy/item")
    public ResultObject copyTdItemStore(@RequestBody PfParamItemStoreDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getParIdItemStore() != null, "题集ID");
        return ResultObject.createSuccess("copyTdItemStore", ResultObject.DATA_TYPE_OBJECT,
                pfPaperService.copyTdItemStore(dto));
    }


    /**
     * 获取试题题集
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/p/plan/paper/select/item")
    public ResultObject selectTdItemStore(@RequestBody PfPaperDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdModel() != null, "模板id");
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("selectTdItemStore", ResultObject.DATA_TYPE_OBJECT,
                pfPaperService.selectTdItemStore(dto));
    }

    /**
     * add试卷参数
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/p/plan/paper/add/param")
    public ResultObject addPaperParam(@RequestBody PfPaperParam dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getTdItemArgTypes()), "题型参数");
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getSdItemLevels()), "难易程度比例");
        return ResultObject.createSuccess("addPaperParam", ResultObject.DATA_TYPE_OBJECT,
                pfPaperService.addPaperParam(dto));
    }

    /**
     * 获取试卷参数
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/p/plan/paper/select/param")
    public ResultObject selectPaperParam(@RequestBody PfPaperDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdItemStore() != null, "题集ID");
        return ResultObject.createSuccess("selectPaperParam", ResultObject.DATA_TYPE_OBJECT,
                pfPaperService.selectPaperParam(dto.getIdItemStore()));
    }

    /**
     * 设置必考题
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/p/plan/paper/set/param/must")
    public ResultObject setPaperMustParam(@RequestBody PfPaperMustDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getStatus() != null, "status");
        if (dto.getIdItemStore() == null && CollectionUtils.isEmpty(dto.getIdItems())) {
            throw new BizRuntimeException(ErrorCode.ERROR_GENERAL_110001, "缺少必填参数");
        }
        return ResultObject.createSuccess("setPaperMustParam", ResultObject.DATA_TYPE_OBJECT,
                pfPaperService.setPaperMustParam(dto));
    }

    /**
     * 生成试卷
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/p/plan/paper/generate")
    public ResultObject generatePaper(@RequestBody PfParamItemStoreDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getParIdItemStore() != null, "parIdItemStore");
        return ResultObject.createSuccess("generatePaper", ResultObject.DATA_TYPE_OBJECT,
                pfPaperService.generatePaper(dto));
    }

    /**
     * 删除试卷
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/p/plan/paper/del")
    public ResultObject delPaper(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.createSuccess("delPaper", ResultObject.DATA_TYPE_OBJECT,
                pfPaperService.delPaper(dto));
    }

    /**
     * add Sp病例
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/p/plan/paper/add/sp/case")
    public ResultObject addSpCase(@RequestBody TdSpCase dto) {
        /* 参数校验 */
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.createSuccess("addSpCase", ResultObject.DATA_TYPE_OBJECT,
                pfPaperService.addSpCase(dto));
    }

    /**
     * 另存Sp病例
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/p/plan/paper/copy/sp/case")
    public ResultObject copyTdSpCase(@RequestBody PfSpCaseDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getParId() != null, "spId");
        return ResultObject.createSuccess("copyTdSpCase", ResultObject.DATA_TYPE_OBJECT,
                pfPaperService.copyTdSpCase(dto));
    }

    /**
     * 删除sp
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/plan/paper/sp/del")
    public ResultObject delSp(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfPaperService.delSp(dto) ? ResultObject.createSuccess("delSp", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delSp", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 停用、启用sp
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/plan/paper/sp/updateStatus")
    public ResultObject updateSpStatus(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        dto.setOperationType(OperationTypeEnum.UPDATE_STATUS.getCode());
        return pfPaperService.delSp(dto) ?
                ResultObject.createSuccess("updateSpStatus", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("updateSpStatus", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }


    /**
     * add Skill病例
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/p/plan/paper/add/skill/case")
    public ResultObject addSkillCase(@RequestBody TdSkillCase dto) {
        /* 参数校验 */
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        dto.setCreator(CurrentUserUtils.getCurrentUsername());
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.createSuccess("addSkillCase", ResultObject.DATA_TYPE_OBJECT,
                pfPaperService.addSkillCase(dto));
    }

    /**
     * 另存Skill病例
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/p/plan/paper/copy/skill/case")
    public ResultObject copyTdSkillCase(@RequestBody PfSpCaseDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getParId() != null, "skillId");
        return ResultObject.createSuccess("copyTdSkillCase", ResultObject.DATA_TYPE_OBJECT,
                pfPaperService.copyTdSkillCase(dto));
    }

    /**
     * 删除Skill
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/plan/paper/skill/del")
    public ResultObject delSkillCase(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfPaperService.delSkillCase(dto) ? ResultObject.createSuccess("delSkillCase", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delSkillCase", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 保存排站试卷
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_02_01_001', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/plan/paper/save/paper")
    public ResultObject saveTdPaper(@RequestBody PfAddTpPaperDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdInsStation() != null, "idInsStation");
        Assert.isTrue(dto.getIdPaper() != null, "idPaper");
        return ResultObject.createSuccess("saveTdPaper", ResultObject.DATA_TYPE_OBJECT,
                pfPaperService.saveTdPaper(dto));
    }


}
