package com.osce.server.rest.biz.statistics;

import com.osce.api.biz.statistics.PfStatisticsService;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.server.security.CurrentUserUtils;
import com.sm.open.care.core.ErrorCode;
import com.sm.open.care.core.ErrorMessage;
import com.sm.open.care.core.ResultObject;
import com.sm.open.care.core.utils.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: PfStatisticsRestController
 * @Description: 统计模块
 * @Author yangtongbin
 * @Date 2019-07-20
 */
@RestController
public class PfStatisticsRestController {

    @Reference
    private PfStatisticsService pfStatisticsService;

    /**
     * 删除实训记录
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_05_01','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/statistics/test/del")
    public ResultObject delTest(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfStatisticsService.delTest(dto) ?
                ResultObject.createSuccess("delTest", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delTest", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

}
