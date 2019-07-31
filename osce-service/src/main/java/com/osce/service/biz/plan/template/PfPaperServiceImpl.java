package com.osce.service.biz.plan.template;

import com.osce.api.biz.plan.template.PfPaperService;
import com.osce.dto.biz.plan.template.*;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.*;
import com.osce.exception.RestException;
import com.osce.orm.biz.plan.template.PfPaperDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.vo.biz.plan.template.PaperLeftVo;
import com.sm.open.care.core.utils.CommonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PfPaperServiceImpl
 * @Description: 试卷设计
 * @Author yangtongbin
 * @Date 2019-06-08
 */
@Service
public class PfPaperServiceImpl implements PfPaperService {

    private static final Logger logger = LoggerFactory.getLogger(PfPaperServiceImpl.class);

    @Resource
    private PfPaperDao pfPaperDao;

    @Override
    public List<PaperLeftVo> listLeft(Long idModel) {
        return pfPaperDao.listLeft(idModel);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long addTdItemStore(TdItemStore dto) {
        if (dto.getIdItemStore() == null) {
            pfPaperDao.addTdItemStore(dto);
        } else {
            pfPaperDao.editTdItemStore(dto);
            pfPaperDao.delTdItemSection(dto.getIdItemStore());
        }
        pfPaperDao.addTdItemSection(dto.getIdItemStoreFrom(), dto.getIdItemStore());
        return dto.getIdItemStore();
    }

    @Override
    public boolean copyTdItemStore(PfParamItemStoreDto dto) {
        // 调用存储过程另存 P_ITEM_STORE_INS
        pfPaperDao.callItemStoreIns(dto);
        if (dto.getParCode() != 0) {
            logger.error("调用存储过程另存[理论试题]出错, param : {} ", dto.toString());
            throw new RestException(String.valueOf(dto.getParCode()), dto.getParMsg());
        }
        return true;
    }

    @Override
    public TdItemStore selectTdItemStore(PfPaperDto dto) {
        return pfPaperDao.selectTdItemStore(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PfPaperParam addPaperParam(PfPaperParam dto) {
        // 目录信息
        List<Long> idItemSections = dto.getIdItemSections();
        if (CollectionUtils.isNotEmpty(idItemSections)) {
            pfPaperDao.editTdItemSections(idItemSections);
        }
        // 题型参数
        List<TdItemArgType> tdItemArgTypes = dto.getTdItemArgTypes();
        if (CollectionUtils.isNotEmpty(tdItemArgTypes)) {
            pfPaperDao.delTdItemArgTypes(tdItemArgTypes.get(0).getIdItemStore());
            pfPaperDao.addTdItemArgTypes(tdItemArgTypes);
        }
        // 难易程度比例
        List<TdItemArgLevel> sdItemLevels = dto.getSdItemLevels();
        if (CollectionUtils.isNotEmpty(sdItemLevels)) {
            if (sdItemLevels.get(0).getIdItemArgLevel() == null) {
                pfPaperDao.addSdItemLevels(sdItemLevels);
            } else {
                pfPaperDao.editSdItemLevels(sdItemLevels);
            }
        }
        Long idItemStore = tdItemArgTypes.get(0).getIdItemStore();
        return selectPaperParam(idItemStore);
    }

    @Override
    public PfPaperParam selectPaperParam(Long idItemStore) {
        PfPaperParam pfPaperParam = new PfPaperParam();
        pfPaperParam.setItemTotals(pfPaperDao.listItemTotal(idItemStore));
        pfPaperParam.setTdItemSections(pfPaperDao.selectTdItemSection(idItemStore));
        pfPaperParam.setTdItemArgTypes(pfPaperDao.listTdItemArgType(idItemStore));
        pfPaperParam.setSdItemLevels(pfPaperDao.listSdItemLevel(idItemStore));
        return pfPaperParam;
    }

    @Override
    public PageResult listItem(PfPaperDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfPaperDao.countItem(dto),
                pfPaperDao.listItem(dto));
    }

    @Override
    public boolean setPaperMustParam(PfPaperMustDto dto) {
        dto.setStatus(dto.getStatus() == 1 ? 1 : 0);
        if (dto.getIdItemStore() != null) {
            pfPaperDao.updateItemByIdItemStore(dto.getIdItemStore(), dto.getStatus());
        }
        if (CollectionUtils.isNotEmpty(dto.getIdItems())) {
            pfPaperDao.updateItemByIds(dto.getIdItems(), dto.getStatus());
        }
        return true;
    }

    @Override
    public boolean generatePaper(PfParamItemStoreDto dto) {
        pfPaperDao.callGeneratePaper(dto);
        if (dto.getParCode() != 0) {
            throw new RestException(String.valueOf(dto.getParCode()), dto.getParMsg());
        }
        return true;
    }

    @Override
    public PageResult listPaper(PfPaperDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfPaperDao.countPaper(dto),
                pfPaperDao.listPaper(dto));
    }

    @Override
    public boolean delPaper(PfBachChangeStatusDto dto) {
        int num = pfPaperDao.delPaper(dto);
        return num >= 1 ? true : false;
    }

    @Override
    public TdSpCase addSpCase(TdSpCase dto) {
        TdSpCase tdSpCaseVo = new TdSpCase();
        if (StringUtils.isBlank(dto.getIdCase())) {
            dto.setIdCase(CommonUtil.uuid());
            pfPaperDao.addSpCase(dto);
        } else {
            pfPaperDao.editSpCase(dto);
        }
        tdSpCaseVo.setId(dto.getId());
        tdSpCaseVo.setIdCase(dto.getIdCase());
        return tdSpCaseVo;
    }

    @Override
    public boolean copyTdSpCase(PfSpCaseDto dto) {
        pfPaperDao.callSpCase(dto);
        if (dto.getParCode() != 0) {
            logger.error("调用存储过程另存[sp病例]出错, param : {} ", dto.toString());
            throw new RestException(String.valueOf(dto.getParCode()), dto.getParMsg());
        }
        return true;
    }

    @Override
    public PageResult listSp(PfPaperDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfPaperDao.countSp(dto),
                pfPaperDao.listSp(dto));
    }

    @Override
    public boolean delSp(PfBachChangeStatusDto dto) {
        int num = pfPaperDao.delSp(dto);
        return num >= 1 ? true : false;
    }

    @Override
    public TdSkillCase addSkillCase(TdSkillCase dto) {
        TdSkillCase skillCase = new TdSkillCase();
        if (StringUtils.isBlank(dto.getIdSkillCase())) {
            dto.setIdSkillCase(CommonUtil.uuid());
            pfPaperDao.addSkillCase(dto);
        } else {
            pfPaperDao.editSkillCase(dto);
        }
        skillCase.setId(dto.getId());
        skillCase.setIdSkillCase(dto.getIdSkillCase());
        return skillCase;
    }

    @Override
    public boolean copyTdSkillCase(PfSpCaseDto dto) {
        pfPaperDao.callSkillCase(dto);
        if (dto.getParCode() != 0) {
            logger.error("调用存储过程另存[skill病例]出错, param : {} ", dto.toString());
            throw new RestException(String.valueOf(dto.getParCode()), dto.getParMsg());
        }
        return true;
    }

    @Override
    public PageResult listSkill(PfPaperDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfPaperDao.countSkill(dto),
                pfPaperDao.listSkill(dto));
    }

    @Override
    public boolean delSkillCase(PfBachChangeStatusDto dto) {
        int num = pfPaperDao.delSkillCase(dto);
        return num >= 1 ? true : false;
    }

    @Override
    public PageResult pagePaper(PfPaperDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfPaperDao.countExamPaper(dto),
                pfPaperDao.listExamPaper(dto));
    }

    @Override
    public boolean saveTdPaper(PfAddTpPaperDto dto) {
        int num = pfPaperDao.saveTdPaper(dto);
        return num >= 1 ? true : false;
    }

}
