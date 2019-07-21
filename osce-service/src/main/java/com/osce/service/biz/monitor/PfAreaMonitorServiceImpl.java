package com.osce.service.biz.monitor;

import com.osce.api.biz.monitor.PfAreaMonitorService;
import com.osce.orm.biz.monitor.PfAreaMonitorDao;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

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


}
