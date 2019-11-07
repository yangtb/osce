package com.osce.service.biz.plan.manage;

import com.osce.api.biz.plan.manage.PfPlanStationService;
import com.osce.dto.biz.plan.manage.AssistantDto;
import com.osce.dto.biz.plan.manage.PlanDto;
import com.osce.dto.biz.plan.manage.PlanSpDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TpAssistant;
import com.osce.entity.TpSp;
import com.osce.entity.TpSpCache;
import com.osce.exception.RestErrorCode;
import com.osce.exception.RestException;
import com.osce.orm.biz.plan.manage.PfPlanManageDao;
import com.osce.orm.biz.plan.manage.PfPlanStationDao;
import com.osce.orm.biz.plan.template.PfTemplateDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.vo.biz.plan.manage.PlanPublishItemVo;
import com.osce.vo.biz.plan.manage.PlanSpStationVo;
import com.osce.vo.biz.plan.manage.PlanSpVo;
import com.osce.vo.biz.plan.template.station.PlanAssistant;
import com.osce.vo.biz.plan.template.station.PlanSp;
import com.osce.vo.biz.plan.template.station.TdStationInfoVo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfPlanStationServiceImpl implements PfPlanStationService {

    @Resource
    private PfPlanStationDao pfPlanStationDao;

    @Resource
    private PfTemplateDao pfTemplateDao;

    @Resource
    private PfPlanManageDao pfPlanManageDao;

    @Override
    public List<TdStationInfoVo> selectStationInfo(Long idPlan) {
        List<TdStationInfoVo> stationVos = pfPlanStationDao.selectStationInfo(idPlan);
        stationVos.forEach(tdInsStationVo ->
                tdInsStationVo.getDayData().forEach(tdDayInfo -> {
                    tdDayInfo.getAreaData().forEach(tdAreaInfo ->
                            tdAreaInfo.getStationData().forEach(tdStation -> {
                                tdStation.getRoomData().forEach(tdRoomInfo -> {
                                    if (tdRoomInfo.getIdPaper() != null) {
                                        String idPaperText = pfTemplateDao.selectSkillName(tdStation.getSdSkillCa(), tdRoomInfo.getIdPaper());
                                        tdRoomInfo.setIdPaperText(idPaperText);
                                    }
                                });
                            }));
                }));
        return stationVos;
    }

    @Override
    public List<TdStationInfoVo> selectStationSpDetail(PlanDto dto) {
        Long idPlan = dto.getIdPlan();
        List<TdStationInfoVo> stationVos = pfPlanStationDao.selectStationSpDetail(idPlan);
        stationVos.forEach(tdInsStationVo ->
                tdInsStationVo.getDayData().forEach(tdDayInfo -> {
                    tdDayInfo.getAreaData().forEach(tdAreaInfo ->
                            tdAreaInfo.getStationData().forEach(tdStation -> {
                                tdStation.getRoomData().forEach(tdRoomInfo -> {
                                    if (tdStation.getSdSkillCa().equals("3")) {
                                        // 查询sp
                                        tdRoomInfo.setPlanSp(pfPlanStationDao.listPlanSp(
                                                idPlan,
                                                tdAreaInfo.getIdArea(),
                                                tdStation.getIdStation(),
                                                tdDayInfo.getTimeSection(),
                                                tdRoomInfo.getIdRoom()
                                        ));
                                    }
                                    // 查询考官
                                    tdRoomInfo.setPlanAssistant(pfPlanStationDao.selectPlanAssistant(
                                            idPlan,
                                            tdAreaInfo.getIdArea(),
                                            tdStation.getIdStation(),
                                            tdDayInfo.getTimeSection(),
                                            tdRoomInfo.getIdRoom()
                                    ));

                                    // 试卷
                                    if (tdRoomInfo.getIdPaper() != null) {
                                        String idPaperText = pfTemplateDao.selectSkillName(tdStation.getSdSkillCa(), tdRoomInfo.getIdPaper());
                                        tdRoomInfo.setIdPaperText(idPaperText);
                                    }
                                });
                            }));
                }));
        return stationVos;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveStationSp(TpSp dto) {
        String sdPlanStatus = pfPlanManageDao.selectPlanStatus(dto.getIdPlan());
        if ("5".equals(sdPlanStatus)) {
            throw new RestException(RestErrorCode.PLAN_END);
        }
        pfPlanStationDao.delStationSp(dto);
        return pfPlanStationDao.saveStationSp(dto) >= 1 ? true : false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveStationAssistant(TpAssistant dto) {
        String sdPlanStatus = pfPlanManageDao.selectPlanStatus(dto.getIdPlan());
        if ("5".equals(sdPlanStatus)) {
            throw new RestException(RestErrorCode.PLAN_END);
        }
        pfPlanStationDao.delStationAssistant(dto);
        return pfPlanStationDao.saveStationAssistant(dto) == 1 ? true : false;
    }

    @Override
    public PageResult pageAssistant(AssistantDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfPlanStationDao.countAssistant(dto),
                pfPlanStationDao.listAssistant(dto));
    }

    @Override
    public PlanAssistant selectStationAssistant(TpAssistant dto) {
        return pfPlanStationDao.selectPlanAssistant(
                dto.getIdPlan(),
                dto.getIdArea(),
                dto.getIdStation(),
                dto.getTimeSection(),
                dto.getIdRoom());
    }

    @Override
    public List<PlanSp> selectStationSp(TpSp dto) {
        return pfPlanStationDao.listPlanSp(dto.getIdPlan(),
                dto.getIdArea(),
                dto.getIdStation(),
                dto.getTimeSection(),
                dto.getIdRoom());
    }

    @Override
    public List<PlanPublishItemVo> listStudentItem(String idPlan) {
        return pfPlanStationDao.listStudentItem(idPlan);
    }

    @Override
    public List<PlanPublishItemVo> listSpItem(String idPlan) {
        return pfPlanStationDao.listSpItem(idPlan);
    }

    @Override
    public List<PlanPublishItemVo> listAssistantItem(String idPlan) {
        return pfPlanStationDao.listAssistantItem(idPlan);
    }

    @Override
    public List<PlanSpStationVo> listPlanSpStation(String idPlan) {
        return pfPlanStationDao.listPlanSpStation(idPlan);
    }

    @Override
    public PageResult listPlanSp1(PlanSpDto dto) {
        PageParam.initPageDto(dto);
        // 获取SP动态sql字符串
        String spSql = pfPlanStationDao.getSpSql(dto.getIdOrg());
        return ResultFactory.initPageResultWithSuccess(pfPlanStationDao.countPlanSp1(dto, spSql),
                pfPlanStationDao.listPlanSp1(dto, spSql));
    }

    @Override
    public List<PlanSpVo> listPlanSpCache(PlanSpDto dto) {
        return pfPlanStationDao.listPlanSpCache(dto);
    }

    @Override
    public boolean addPlanSpCache(List<TpSpCache> list) {
        for (TpSpCache tpSpCache : list) {
            if (!pfPlanStationDao.isExistPlanSpCache(tpSpCache)) {
                pfPlanStationDao.addPlanSpCache(tpSpCache);
            }
        }
        return true;
    }

    @Override
    public boolean delPlanSpCache(PfBachChangeStatusDto dto) {
        return pfPlanStationDao.delPlanSpCache(dto) >= 1 ? true : false;
    }
}
