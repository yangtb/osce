package com.osce.service.task;

import com.osce.orm.biz.plan.manage.PfPlanManageDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PlanStartTask
 * @Description: 到了时间，自动把计划的状态更新为正在考试
 * @Author yangtongbin
 * @Date 2019-11-04
 */
@Component
public class PlanStartTask {

    @Resource
    private PfPlanManageDao pfPlanManageDao;

    @Scheduled(fixedDelay = 600000)
    public void handleExpireOrders() {
        List<Long> idPlans= pfPlanManageDao.listPlanStart();
        if (CollectionUtils.isEmpty(idPlans)) {
            return;
        }

        pfPlanManageDao.updatePlanStatusByIds(idPlans);
    }

}
