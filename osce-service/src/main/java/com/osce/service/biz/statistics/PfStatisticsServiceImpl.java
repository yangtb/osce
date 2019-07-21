package com.osce.service.biz.statistics;

import com.osce.api.biz.statistics.PfStatisticsService;
import com.osce.dto.biz.statistics.StatisticsDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.orm.biz.statistics.PfStatisticsDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @ClassName: PfStatisticsServiceImpl
 * @Description: 统计实现
 * @Author yangtongbin
 * @Date 2019-07-20
 */
@Service
public class PfStatisticsServiceImpl implements PfStatisticsService {

    @Resource
    private PfStatisticsDao pfStatisticsDao;

    @Override
    public PageResult pageTests(StatisticsDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfStatisticsDao.countStatistics(dto),
                pfStatisticsDao.listStatistics(dto));
    }

    @Override
    public boolean delTest(PfBachChangeStatusDto dto) {
        int num = pfStatisticsDao.delTest(dto);
        return num >= 1 ? true : false;
    }

}
