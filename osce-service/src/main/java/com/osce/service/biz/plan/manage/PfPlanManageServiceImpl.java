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
import com.osce.vo.biz.plan.manage.TdPlanStepCheckVo;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

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
    public Integer selectCurrentStep(PlanDto dto) {
        Long idPlan = dto.getIdPlan();
        Integer stepNum = 1;
        if (pfPlanManageDao.isExistStep7(idPlan)) {
            stepNum = 7;
        } else if (pfPlanManageDao.isExistStep6(idPlan)) {
            stepNum = 6;
        } else if (pfPlanManageDao.isExistStep5(idPlan)) {
            stepNum = 5;
        } else if (pfPlanManageDao.isExistStep4(idPlan)) {
            stepNum = 4;
        } else if (pfPlanManageDao.isExistStep3(idPlan)) {
            stepNum = 3;
        } else if (pfPlanManageDao.isExistStep2(idPlan)) {
            stepNum = 2;
        }
        return stepNum;
    }

    @Override
    public PageResult pagePlan(PlanDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfPlanManageDao.countPlanManage(dto),
                pfPlanManageDao.listPlanManage(dto));
    }

    @Override
    public PageResult pagePlan1(PlanDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfPlanManageDao.countPlanManage1(dto),
                pfPlanManageDao.listPlanManage1(dto));
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
        return pfPlanManageDao.delPlan(dto) >= 1 ? true : false;
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

    @Override
    public String checkPlanStep(PlanDto dto) {
        String msg = "";
        if (dto.getCheckStep() == 3) {
            // 查询分配考卷页面未填信息
            List<TdPlanStepCheckVo> list = pfPlanManageDao.selectUnfilledPlanStep3(dto.getIdPlan());
            for (TdPlanStepCheckVo tdPlanStepCheckVo : list) {
                String checkMsg = "";
                if (tdPlanStepCheckVo.getSdSkillCa().equals("1")) {
                    checkMsg = "请填写试卷";
                } else {
                    if (tdPlanStepCheckVo.getIdPaper() == null && tdPlanStepCheckVo.getIdPaper() == null) {
                        checkMsg = "请填写试卷、评分表";
                    } else if (tdPlanStepCheckVo.getIdPaper() == null) {
                        checkMsg = "请填写试卷";
                    } else if (tdPlanStepCheckVo.getIdPaper() == null) {
                        checkMsg = "请填写评分表";
                    }
                }
                msg += tdPlanStepCheckVo.getPlanTime() + " "
                        + tdPlanStepCheckVo.getNaArea() + " "
                        + tdPlanStepCheckVo.getNaStation() + " "
                        + tdPlanStepCheckVo.getNaRoom() + " "
                        + " <span style='color: red; font-weight: bold'>" + checkMsg + "</span>" + "<br>";
            }
        } else if (dto.getCheckStep() == 4) {
            // 查询分配SP页面未填信息
            List<TdPlanStepCheckVo> list = pfPlanManageDao.selectUnfilledPlanStep4(dto.getIdPlan());
            for (TdPlanStepCheckVo tdPlanStepCheckVo : list) {
                msg += tdPlanStepCheckVo.getPlanTime() + " "
                        + tdPlanStepCheckVo.getNaArea() + " "
                        + tdPlanStepCheckVo.getNaStation() + " "
                        + tdPlanStepCheckVo.getNaRoom()
                        + " <span style='color: red; font-weight: bold'>请选择SP</span>" + "<br>";
            }
        } else if (dto.getCheckStep() == 5) {
            // 查询分配考官页面未填信息
            List<TdPlanStepCheckVo> list = pfPlanManageDao.selectUnfilledPlanStep5(dto.getIdPlan());
            for (TdPlanStepCheckVo tdPlanStepCheckVo : list) {
                msg += tdPlanStepCheckVo.getPlanTime() + " "
                        + tdPlanStepCheckVo.getNaArea() + " "
                        + tdPlanStepCheckVo.getNaStation() + " "
                        + tdPlanStepCheckVo.getNaRoom()
                        + " <span style='color: red; font-weight: bold'>至少有一个主考官</span>" + "<br>";
            }
        }
        return msg;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean endPlan(PfBachChangeStatusDto dto) {
        // 1.更新计划状态
        // 2.更新实际完成时间
        pfPlanManageDao.updatePlanStatus(dto);
        // 3.清空该计划下的队列
        pfPlanManageDao.clearEsExecQueue(dto);
        return true;
    }

}
