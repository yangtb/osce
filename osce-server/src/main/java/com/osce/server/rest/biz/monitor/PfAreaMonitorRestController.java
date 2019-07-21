package com.osce.server.rest.biz.monitor;

import com.osce.api.biz.monitor.PfAreaMonitorService;
import com.osce.server.portal.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: PfMonitorRestController
 * @Description: 考场监控
 * @Author yangtongbin
 * @Date 2019-07-20
 */
@RestController
public class PfAreaMonitorRestController extends BaseController {

    @Reference
    private PfAreaMonitorService pfAreaMonitorService;


}
