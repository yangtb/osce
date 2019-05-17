package com.osce.service.biz.training.caseku;

import com.osce.api.biz.training.caseku.PfCaseService;
import com.osce.dto.biz.training.caseku.CaseDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.CobScoreItem;
import com.osce.entity.CobScoreSheet;
import com.osce.entity.CobSpCase;
import com.osce.orm.biz.training.caseku.PfCaseDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.sm.open.care.core.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PfCaseServiceImpl
 * @Description: 病例库实现
 * @Author yangtongbin
 * @Date 2019-05-15
 */
@Service
public class PfCaseServiceImpl implements PfCaseService {

    @Resource
    private PfCaseDao pfCaseDao;

    @Override
    public PageResult pageCase(CaseDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfCaseDao.countCase(dto),
                pfCaseDao.listCase(dto));
    }

    @Override
    public String addCase(CobSpCase dto) {
        if (StringUtils.isBlank(dto.getIdCase())) {
            dto.setIdCase(CommonUtil.uuid());
            pfCaseDao.addCase(dto);
        } else {
            pfCaseDao.editCase(dto);
        }
        return dto.getIdCase();
    }

    @Override
    public boolean delCase(PfBachChangeStatusDto dto) {
        int num = pfCaseDao.delCase(dto);
        return num >= 1 ? true : false;
    }

    @Override
    public List<CobScoreSheet> listSheet(CaseDto dto) {
        return pfCaseDao.listSheet(dto);
    }

    @Override
    public Long saveSheet(CobScoreSheet dto) {
        if (dto.getIdScoreSheet() == null) {
            pfCaseDao.addSheet(dto);
        } else {

        }
        return dto.getIdScoreSheet();
    }

    @Override
    public boolean delSheet(PfBachChangeStatusDto dto) {
        int num = pfCaseDao.delSheet(dto);
        return num >= 1 ? true : false;
    }

    @Override
    public PageResult pageItem(CaseDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfCaseDao.countItem(dto),
                pfCaseDao.listItem(dto));
    }

    @Override
    public Long saveItem(CobScoreItem dto) {
        if (dto.getIdScoreItem() == null) {
            pfCaseDao.addItem(dto);
        } else {
            pfCaseDao.editItem(dto);
        }
        return dto.getIdScoreItem();
    }

    @Override
    public boolean delItem(PfBachChangeStatusDto dto) {
        int num = pfCaseDao.delItem(dto);
        return num >= 1 ? true : false;
    }

}
