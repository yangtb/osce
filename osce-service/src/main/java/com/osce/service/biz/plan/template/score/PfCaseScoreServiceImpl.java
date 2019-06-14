package com.osce.service.biz.plan.template.score;

import com.osce.api.biz.plan.template.score.PfCaseScoreService;
import com.osce.dto.biz.training.caseku.CaseDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdScoreItem;
import com.osce.entity.TdScoreSheet;
import com.osce.orm.biz.plan.template.score.PfCaseScoreDao;
import com.osce.orm.biz.training.caseku.PfCaseDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
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
public class PfCaseScoreServiceImpl implements PfCaseScoreService {

    @Resource
    private PfCaseScoreDao pfCaseScoreDao;

    @Override
    public List<TdScoreSheet> listSheet(CaseDto dto) {
        return pfCaseScoreDao.listSheet(dto);
    }

    @Override
    public Long saveSheet(TdScoreSheet dto) {
        if (dto.getIdScoreSheet() == null) {
            pfCaseScoreDao.addSheet(dto);
        } else {

        }
        return dto.getIdScoreSheet();
    }

    @Override
    public boolean delSheet(PfBachChangeStatusDto dto) {
        int num = pfCaseScoreDao.delSheet(dto);
        return num >= 1 ? true : false;
    }

    @Override
    public PageResult pageItem(CaseDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfCaseScoreDao.countItem(dto),
                pfCaseScoreDao.listItem(dto));
    }

    @Override
    public Long saveItem(TdScoreItem dto) {
        if (dto.getIdScoreItem() == null) {
            pfCaseScoreDao.addItem(dto);
        } else {
            pfCaseScoreDao.editItem(dto);
        }
        return dto.getIdScoreItem();
    }

    @Override
    public boolean delItem(PfBachChangeStatusDto dto) {
        int num = pfCaseScoreDao.delItem(dto);
        return num >= 1 ? true : false;
    }

}
