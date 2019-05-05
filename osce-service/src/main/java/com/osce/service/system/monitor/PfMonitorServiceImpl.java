package com.osce.service.system.monitor;

import com.osce.api.system.monitor.PfMonitorService;
import com.osce.vo.system.monitor.PfServerVo;
import org.apache.dubbo.config.annotation.Service;

import java.util.Properties;

@Service
public class PfMonitorServiceImpl implements PfMonitorService {

    @Override
    public PfServerVo selectServerInfo() {
        PfServerVo pfServerVo = new PfServerVo();
        // 获得系统属性集
        Properties props=System.getProperties();
        pfServerVo.setOsName(props.getProperty("os.name"));
        pfServerVo.setOsVersion(props.getProperty("os.version"));
        pfServerVo.setJavaVersion(props.getProperty("java.version"));
        pfServerVo.setUserDir(props.getProperty("user.dir"));

        Runtime runtime = Runtime.getRuntime();
        pfServerVo.setCpuNum(runtime.availableProcessors());
        pfServerVo.setTotalMemory(runtime.totalMemory());
        pfServerVo.setFreeMemory(runtime.freeMemory());
        return pfServerVo;
    }
}
