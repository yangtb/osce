package com.osce.api.system.monitor;

import com.osce.vo.system.monitor.PfServerVo;

/**
 * @ClassName: PfMonitorFacade
 * @Description: 监控接口
 * @Author yangtongbin
 * @Date 2018/9/5 11:31
 */
public interface PfMonitorService {

    /**
     * 查询服务器信息
     *
     * @return
     */
    PfServerVo selectServerInfo();
}
