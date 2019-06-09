package com.osce.service.biz.plan.template;

import com.osce.api.biz.plan.template.PfPaperService;
import com.osce.dto.biz.plan.template.PfPaperDto;
import com.osce.dto.biz.plan.template.PfPaperMustDto;
import com.osce.dto.biz.plan.template.PfPaperParam;
import com.osce.dto.biz.plan.template.PfParamItemStoreDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdItemArgLevel;
import com.osce.entity.TdItemArgType;
import com.osce.entity.TdItemStore;
import com.osce.orm.biz.plan.template.PfPaperDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.vo.biz.plan.template.PaperLeftVo;
import com.sm.open.care.core.exception.BizRuntimeException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.Service;
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

    @Resource
    private PfPaperDao pfPaperDao;

    @Override
    public List<PaperLeftVo> listLeft(Long idModel) {
        return pfPaperDao.listLeft(idModel);
    }

    @Override
    public Long addTdItemStore(TdItemStore dto) {
        if (dto.getIdItemStore() == null) {
            pfPaperDao.addTdItemStore(dto);
        } else {
            pfPaperDao.editTdItemStore(dto);
        }
        // 调用存储过程另存 P_ITEM_STORE_INS
        PfParamItemStoreDto pfParamItemStoreDto = new PfParamItemStoreDto();
        pfParamItemStoreDto.setParIdItemStore(dto.getIdItemStore());
        pfPaperDao.callItemStoreIns(pfParamItemStoreDto);
        if (pfParamItemStoreDto.getParCode() != 0) {
            throw new BizRuntimeException(String.valueOf(pfParamItemStoreDto.getParCode()), pfParamItemStoreDto.getParMsg());
        }
        return dto.getIdItemStore();
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
            if (tdItemArgTypes.get(0).getIdItemArgType() == null) {
                pfPaperDao.addTdItemArgTypes(tdItemArgTypes);
            } else {
                pfPaperDao.editTdItemArgTypes(tdItemArgTypes);
            }
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
            throw new BizRuntimeException(String.valueOf(dto.getParCode()), dto.getParMsg());
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

}
