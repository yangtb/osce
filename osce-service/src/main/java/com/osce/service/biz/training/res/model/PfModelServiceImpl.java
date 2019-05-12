package com.osce.service.biz.training.res.model;

import com.osce.api.biz.training.res.model.PfModelService;
import com.osce.dto.biz.training.res.model.ModelDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.ErpDevice;
import com.osce.exception.RestErrorCode;
import com.osce.exception.RestException;
import com.osce.orm.biz.training.res.model.PfModelDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @ClassName: PfModelServiceImpl
 * @Description: 教学模型
 * @Author yangtongbin
 * @Date 2019-05-12
 */
@Service
public class PfModelServiceImpl implements PfModelService {

    @Resource
    private PfModelDao pfModelDao;

    @Override
    public PageResult pageModels(ModelDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfModelDao.countModel(dto),
                pfModelDao.listModels(dto));
    }

    @Override
    public Long addModel(ErpDevice dto) {
        if (dto.getIdDevice() == null) {
            pfModelDao.addModel(dto);
        } else {
            pfModelDao.editModel(dto);
        }
        return dto.getIdDevice();
    }

    @Override
    public boolean delModel(PfBachChangeStatusDto dto) {
        int num = pfModelDao.delModel(dto);
        return num >= 1 ? true : false;
    }


}
