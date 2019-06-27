package com.osce.service.biz.plan.manage;

import com.osce.api.biz.plan.manage.PfPlanManageService;
import com.osce.dto.biz.plan.manage.PfCallPlanDto;
import com.osce.dto.biz.plan.manage.PfCopyModelDto;
import com.osce.dto.biz.plan.manage.PlanDto;
import com.osce.dto.biz.plan.manage.TpStudentDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TpPlan;
import com.osce.exception.RestException;
import com.osce.orm.biz.plan.manage.PfPlanManageDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.vo.biz.plan.manage.AssignedStudentVo;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PfPlanManageServiceImpl
 * @Description: 实训计划管理
 * @Author yangtongbin
 * @Date 2019-06-16
 */
@Service
public class PfPlanManageServiceImpl implements PfPlanManageService {

    private static final Logger logger = LoggerFactory.getLogger(PfPlanManageServiceImpl.class);

    @Resource
    private PfPlanManageDao pfPlanManageDao;

    @Override
    public PageResult pagePlan(PlanDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfPlanManageDao.countPlanManage(dto),
                pfPlanManageDao.listPlanManage(dto));
    }

    @Override
    public Long addPlan(TpPlan dto) {
        if (dto.getIdPlan() == null) {
            dto.setPercentPicking(0);
            dto.setFgActive("1");
            dto.setSdPlanStatus("1");
            pfPlanManageDao.addPlan(dto);
        } else {
            pfPlanManageDao.editPlan(dto);
        }
        return dto.getIdPlan();
    }

    @Override
    public boolean delPlan(PfBachChangeStatusDto dto) {
        return false;
    }

    @Override
    public String copyTdModel(PfCopyModelDto dto, boolean addFlag) {
        if (addFlag) {
            pfPlanManageDao.copyTdModel(dto);
            if (dto.getParCode() != 0) {
                logger.error("调用存储过程另存[模板]出错, param : {} ", dto.toString());
                throw new RestException(String.valueOf(dto.getParCode()), dto.getParMsg());
            }
        }
        return pfPlanManageDao.getIdModel(dto.getParIdPlan());
    }

    @Override
    public TpPlan selectPlan(PlanDto dto) {
        return pfPlanManageDao.selectPlan(dto);
    }

    @Override
    public List<AssignedStudentVo> listAssignedStudent(PlanDto dto) {
        return pfPlanManageDao.listAssignedStudent(dto.getIdPlan());
    }

    @Override
    public boolean addStudent(TpStudentDto dto) {
        return pfPlanManageDao.addStudent(dto) >= 1 ? true : false;
    }

    @Override
    public boolean delStudent(PfBachChangeStatusDto dto) {
        int num = pfPlanManageDao.delStudent(dto);
        return num >= 1 ? true : false;
    }

    @Override
    public void callStationPlanOrder(PfCallPlanDto dto) {
        pfPlanManageDao.callStationPlanOrder(dto);
        if (dto.getParCode() != 0) {
            logger.error("调用存储过程[P_STATION_PLAN_ORDER][实训计划排站]出错, param : {} ", dto.toString());
            throw new RestException(String.valueOf(dto.getParCode()), dto.getParMsg());
        }
    }

    @Override
    public void callStationPlanPick(PfCallPlanDto dto) {
        pfPlanManageDao.callStationPlanPick(dto);
        if (dto.getParCode() != 0) {
            logger.error("调用存储过程[P_STATION_PLAN_PICK][生成领料计划]出错, param : {} ", dto.toString());
            throw new RestException(String.valueOf(dto.getParCode()), dto.getParMsg());
        }
    }

    @Override
    public PageResult pagePick(PlanDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfPlanManageDao.countPick(dto),
                pfPlanManageDao.listPick(dto));
    }

    @Override
    public boolean publishPlan(PlanDto dto) {
        int num = pfPlanManageDao.publishPlan(dto.getIdPlan());
        return num == 1 ? true : false;
    }

}
