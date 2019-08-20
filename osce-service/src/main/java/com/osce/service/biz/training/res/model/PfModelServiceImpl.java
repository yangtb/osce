package com.osce.service.biz.training.res.model;

import com.osce.api.biz.training.res.model.PfModelService;
import com.osce.dto.biz.training.res.model.FaultDto;
import com.osce.dto.biz.training.res.model.ModelDto;
import com.osce.dto.biz.training.res.model.RepairDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.ErpDevice;
import com.osce.entity.ErpDeviceCase;
import com.osce.entity.ErpDeviceFault;
import com.osce.entity.ErpDeviceRepair;
import com.osce.orm.biz.training.res.model.PfModelDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.vo.biz.training.res.model.ErpDeviceCaseVo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public PageResult pageModelDevice(ModelDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(0L,
                pfModelDao.listModelDevice(dto));
    }

    @Override
    public Long addModelDevice(ErpDeviceCase dto) {
        if (dto.getIdDeviceCase() == null) {
            pfModelDao.addModelDevice(dto);
        } else {
            pfModelDao.editModelDevice(dto);
        }
        return dto.getIdDeviceCase();
    }

    @Override
    public boolean delModelDevice(PfBachChangeStatusDto dto) {
        int num = pfModelDao.delModelDevice(dto);
        return num >= 1 ? true : false;
    }

    @Override
    public List<ErpDeviceFault> listDeviceFault(ModelDto dto) {
        return pfModelDao.listDeviceFault(dto);
    }

    @Override
    public List<ErpDeviceRepair> listDeviceRepair(ModelDto dto) {
        return pfModelDao.listDeviceRepair(dto);
    }

    @Override
    public boolean saveDeviceFault(FaultDto dto) {
        List<ErpDeviceFault> list = dto.getList();
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        for (ErpDeviceFault erpDeviceFault : list) {
            if (erpDeviceFault.getIdDeviceFault() == null) {
                pfModelDao.addDeviceFault(erpDeviceFault);
            } else {
                pfModelDao.editDeviceFault(erpDeviceFault);
            }
        }
        return true;
    }

    @Override
    public boolean saveDeviceRepair(RepairDto dto) {
        List<ErpDeviceRepair> list = dto.getList();
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        for (ErpDeviceRepair erpDeviceRepair : list) {
            if (erpDeviceRepair.getIdRepair() == null) {
                pfModelDao.addDeviceRepair(erpDeviceRepair);
            } else {
                pfModelDao.editDeviceRepair(erpDeviceRepair);
            }
        }
        return true;
    }

    @Override
    public boolean delDeviceFault(PfBachChangeStatusDto dto) {
        return pfModelDao.delDeviceFault(dto) >= 1 ? true : false;
    }

    @Override
    public boolean delDeviceRepair(PfBachChangeStatusDto dto) {
        return pfModelDao.delDeviceRepair(dto) >= 1 ? true : false;
    }

    @Override
    public List<ErpDeviceCaseVo> listDeviceCase(Long idDevice) {
        return pfModelDao.listDeviceCase(idDevice);
    }

}
