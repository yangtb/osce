package com.osce.service.biz.monitor;

import com.osce.api.biz.monitor.PfAreaMonitorService;
import com.osce.dto.biz.monitor.MonitorDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.orm.biz.monitor.PfAreaMonitorDao;
import com.osce.vo.biz.monitor.MonitorStuVo;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PfAreaMonitorServiceImpl
 * @Description: 考场监控实现
 * @Author yangtongbin
 * @Date 2019-07-20
 */
@Service
public class PfAreaMonitorServiceImpl implements PfAreaMonitorService {

    @Resource
    private PfAreaMonitorDao pfAreaMonitorDao;


    @Override
    public List<MonitorStuVo> listToBeExaminedStu(MonitorDto dto) {
        return pfAreaMonitorDao.listToBeExaminedStu(dto);
    }

    @Override
    public List<MonitorStuVo> listOnSiteStu(MonitorDto dto) {
        return pfAreaMonitorDao.listOnSiteStu(dto);
    }

    @Override
    public List<MonitorStuVo> listEndStu(MonitorDto dto) {
        return pfAreaMonitorDao.listEndStu(dto);
    }

    @Override
    public boolean delAreaStu(PfBachChangeStatusDto dto) {
        return pfAreaMonitorDao.delAreaStu(dto) >= 1 ? true : false;
    }

    @Override
    public boolean recoveryTest(PfBachChangeStatusDto dto) {
        return pfAreaMonitorDao.recoveryTest(dto) >= 1 ? true : false;
    }
}
