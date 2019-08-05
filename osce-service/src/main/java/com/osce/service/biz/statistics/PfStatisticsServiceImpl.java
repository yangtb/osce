package com.osce.service.biz.statistics;

import com.osce.api.biz.statistics.PfStatisticsService;
import com.osce.dto.biz.statistics.StatisticsAssistantDto;
import com.osce.dto.biz.statistics.StatisticsDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.WeExec;
import com.osce.orm.biz.plan.template.PfTemplateDao;
import com.osce.orm.biz.statistics.PfStatisticsDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.vo.biz.statistics.StatisticsAssistantStuScoreVo;
import com.osce.vo.biz.statistics.StatisticsStuScore1Vo;
import com.osce.vo.biz.statistics.StatisticsStuScoreVo;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PfStatisticsServiceImpl
 * @Description: 统计实现
 * @Author yangtongbin
 * @Date 2019-07-20
 */
@Service
public class PfStatisticsServiceImpl implements PfStatisticsService {

    @Resource
    private PfTemplateDao pfTemplateDao;

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

    @Override
    public PageResult pageTestScore(StatisticsDto dto) {
        PageParam.initPageDto(dto);
        List<StatisticsStuScoreVo> list = pfStatisticsDao.listStuScore(dto);
        for (StatisticsStuScoreVo statisticsStuScoreVo : list) {
            statisticsStuScoreVo.setIdPaperText(pfTemplateDao.selectSkillName(statisticsStuScoreVo.getSdSkillCa(),
                    statisticsStuScoreVo.getIdPaper()));
        }
        return ResultFactory.initPageResultWithSuccess(pfStatisticsDao.countStuScore(dto), list);
    }

    @Override
    public List<StatisticsStuScore1Vo> listStuScore1(StatisticsDto dto) {
        return pfStatisticsDao.listStuScore1(dto);
    }

    @Override
    public List<StatisticsAssistantStuScoreVo> listAssistantStuScore(StatisticsAssistantDto dto) {
        return pfStatisticsDao.listAssistantStuScore(dto);
    }

    @Override
    public WeExec selectTestScore(Long idExec) {
        return pfStatisticsDao.selectTestScore(idExec);
    }

}
