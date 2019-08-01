package com.osce.service.biz.monitor;

import com.osce.api.biz.monitor.PfAreaMonitorService;
import com.osce.dto.biz.monitor.MonitorDto;
import com.osce.dto.biz.training.caseku.CaseDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdScoreItem;
import com.osce.orm.biz.monitor.PfAreaMonitorDao;
import com.osce.vo.biz.monitor.MonitorAreaDetailVo;
import com.osce.vo.biz.monitor.MonitorAreaVo;
import com.osce.vo.biz.monitor.MonitorStuVo;
import com.osce.vo.biz.show.ShowAioMainVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public List<MonitorAreaVo> listMonitorArea(MonitorDto dto) {
        return pfAreaMonitorDao.listMonitorArea(null, null, 0);

        /*List<ShowAioMainVo> planList = pfAreaMonitorDao.listCurrentPlan(dto);
        if (CollectionUtils.isEmpty(planList)) {
            return null;
        }
        List<MonitorAreaVo> list = new ArrayList<>();
        for (ShowAioMainVo showAioMainVo : planList) {
            List<MonitorAreaVo> monitorAreaVos = pfAreaMonitorDao.listMonitorArea(showAioMainVo.getIdPlan(),
                    showAioMainVo.getIdArea(), showAioMainVo.getTimeSection());
            if (CollectionUtils.isNotEmpty(monitorAreaVos)) {
                list.addAll(monitorAreaVos);
            }
        }
        return list;*/
    }

    @Override
    public MonitorAreaDetailVo selectMonitorAreaDetail(MonitorDto dto) {
        return pfAreaMonitorDao.selectMonitorAreaDetail(dto);
    }

    @Override
    public List<MonitorStuVo> listToBeExaminedStu(MonitorDto dto) {
        List<ShowAioMainVo> planList = pfAreaMonitorDao.listCurrentPlan(dto);
        if (CollectionUtils.isEmpty(planList)) {
            return null;
        }
        List<MonitorStuVo> list = new ArrayList<>();
        for (ShowAioMainVo showAioMainVo : planList) {
            List<MonitorStuVo> monitorStuVos = pfAreaMonitorDao.listToBeExaminedStu(showAioMainVo.getIdPlan(),
                    showAioMainVo.getIdArea(), showAioMainVo.getTimeSection());
            if (CollectionUtils.isNotEmpty(monitorStuVos)) {
                list.addAll(monitorStuVos);
            }
        }
        return list;
    }

    @Override
    public List<MonitorStuVo> listOnSiteStu(MonitorDto dto) {
        List<ShowAioMainVo> planList = pfAreaMonitorDao.listCurrentPlan(dto);
        if (CollectionUtils.isEmpty(planList)) {
            return null;
        }
        List<MonitorStuVo> list = new ArrayList<>();
        for (ShowAioMainVo showAioMainVo : planList) {
            List<MonitorStuVo> monitorStuVos = pfAreaMonitorDao.listOnSiteStu(showAioMainVo.getIdPlan(),
                    showAioMainVo.getIdArea(), showAioMainVo.getTimeSection());
            if (CollectionUtils.isNotEmpty(monitorStuVos)) {
                list.addAll(monitorStuVos);
            }
        }
        return list;
    }

    @Override
    public List<MonitorStuVo> listEndStu(MonitorDto dto) {
        List<ShowAioMainVo> planList = pfAreaMonitorDao.listCurrentPlan(dto);
        if (CollectionUtils.isEmpty(planList)) {
            return null;
        }
        List<MonitorStuVo> list = new ArrayList<>();
        for (ShowAioMainVo showAioMainVo : planList) {
            List<MonitorStuVo> monitorStuVos = pfAreaMonitorDao.listEndStu(showAioMainVo.getIdPlan(),
                    showAioMainVo.getIdArea(), showAioMainVo.getTimeSection());
            if (CollectionUtils.isNotEmpty(monitorStuVos)) {
                list.addAll(monitorStuVos);
            }
        }
        return list;
    }

    @Override
    public boolean delAreaStu(PfBachChangeStatusDto dto) {
        return pfAreaMonitorDao.delAreaStu(dto) >= 1 ? true : false;
    }

    @Override
    public boolean recoveryTest(PfBachChangeStatusDto dto) {
        return pfAreaMonitorDao.recoveryTest(dto) >= 1 ? true : false;
    }

    @Override
    public List<TdScoreItem> pageItem(CaseDto dto) {
        return pfAreaMonitorDao.listItem(dto);
    }

}
