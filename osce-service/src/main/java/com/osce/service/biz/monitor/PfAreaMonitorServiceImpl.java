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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
        GregorianCalendar ca = new GregorianCalendar();
        dto.setAmPmFlag(ca.get(GregorianCalendar.AM_PM));
        List<ShowAioMainVo> planList = pfAreaMonitorDao.listCurrentPlan(dto);
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
        return list;
    }

    @Override
    public MonitorAreaDetailVo selectMonitorAreaDetail(MonitorDto dto) {
        return pfAreaMonitorDao.selectMonitorAreaDetail(dto);
    }

    private int getAmPmFlag() {
        GregorianCalendar ca = new GregorianCalendar();
        return ca.get(GregorianCalendar.AM_PM);
    }

    @Override
    public List<MonitorStuVo> listToBeExaminedStu(MonitorDto dto) {
        dto.setAmPmFlag(getAmPmFlag());
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
        dto.setAmPmFlag(getAmPmFlag());
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
        dto.setAmPmFlag(getAmPmFlag());
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean recoveryTest(PfBachChangeStatusDto dto) {
        // 检查已有执行记录、删除
        pfAreaMonitorDao.delExecRecord(dto);
        // 恢复考试
        pfAreaMonitorDao.recoveryTest(dto);
        return true;
    }

    @Override
    public List<TdScoreItem> pageItem(CaseDto dto) {
        return pfAreaMonitorDao.listItem(dto);
    }

}
