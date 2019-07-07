package com.osce.service.system.param;

import com.osce.api.system.param.PfParamService;
import com.osce.dto.system.param.ParamDto;
import com.osce.entity.SysParam;
import com.osce.orm.system.param.PfParamDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.sm.open.care.core.enums.Status;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfParamServiceImpl implements PfParamService {

    @Resource
    private PfParamDao pfParamDao;

    @Override
    public PageResult listParams(ParamDto dto) {
        if (StringUtils.isBlank(dto.getStatus())) {
            dto.setStatus(Status.ENABLED.getCode());
        }
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfParamDao.count(dto),
                pfParamDao.listParams(dto));
    }

    @Override
    public Long count(ParamDto dto) {
        return pfParamDao.count(dto);
    }

    @Override
    public List<SysParam> listAllParam() {
        return pfParamDao.listAllParam();
    }

    @Override
    public boolean addParam(SysParam dto) {
        return pfParamDao.addParam(dto) == 1 ? true : false;
    }

    @Override
    public boolean editParam(SysParam dto) {
        return pfParamDao.editParam(dto) == 1 ? true : false;
    }

    @Override
    public boolean isExistParam(String paramCode, String sysType) {
        return pfParamDao.isExistParam(paramCode, sysType) >= 1 ? true : false;
    }

    @Override
    public boolean changeStatus(List<Long> list, String status) {
        return pfParamDao.changeStatus(list, status) >= 1 ? true : false;
    }

    @Override
    public SysParam selectParamByCode(String paramCode) {
        return pfParamDao.selectParamByCode(paramCode);
    }


}
