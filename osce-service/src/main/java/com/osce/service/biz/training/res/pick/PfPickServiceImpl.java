package com.osce.service.biz.training.res.pick;

import com.osce.api.biz.training.res.pick.PfPickService;
import com.osce.dto.biz.training.res.pick.PfPickDeviceDto;
import com.osce.dto.biz.training.res.pick.PfPickDto;
import com.osce.dto.biz.training.res.pick.PfTpPickCaseDto;
import com.osce.entity.TpPicking;
import com.osce.orm.biz.training.res.pick.PfPickDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.vo.biz.training.res.pick.TpPickingCaseVo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PfPickServiceImpl
 * @Description: 领料计划实现
 * @Author yangtongbin
 * @Date 2019-08-19
 */
@Service
public class PfPickServiceImpl implements PfPickService {

    @Resource
    private PfPickDao pfPickDao;

    @Override
    public PageResult pagePick(PfPickDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfPickDao.countPick(dto),
                pfPickDao.listPick(dto));
    }

    @Override
    public List<TpPickingCaseVo> listDeviceCase(PfPickDeviceDto dto) {
        return pfPickDao.listDeviceCase(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveTpPickCase(PfTpPickCaseDto dto) {
        // 删除领料实例
        pfPickDao.delTpPickingCase(dto.getIdTpPicking());
        // 重新插入
        pfPickDao.saveTpPickCase(dto);
        // 更新实领数量
        pfPickDao.updatePickNum(dto.getIdDeviceCases().size(), dto.getIdTpPicking());
        return true;
    }

    @Override
    public boolean updateTpPick(TpPicking dto) {
        int num = pfPickDao.updatePickNum(dto.getFgPicked(), dto.getIdTpPicking());
        return num == 1 ? true : false;
    }

}
