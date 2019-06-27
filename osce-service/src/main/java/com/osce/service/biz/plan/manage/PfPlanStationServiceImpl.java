package com.osce.service.biz.plan.manage;

import com.osce.api.biz.plan.manage.PfPlanStationService;
import com.osce.dto.biz.plan.manage.AssistantDto;
import com.osce.dto.biz.plan.manage.PlanDto;
import com.osce.entity.TpAssistant;
import com.osce.entity.TpSp;
import com.osce.orm.biz.plan.manage.PfPlanStationDao;
import com.osce.orm.biz.plan.template.PfTemplateDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.vo.biz.plan.manage.PlanPublishItemVo;
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
                                    if (dto.getSpFlag().equals("sp")) {
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
                                    } else {
                                        // 查询考官
                                        tdRoomInfo.setPlanAssistant(pfPlanStationDao.selectPlanAssistant(
                                                idPlan,
                                                tdAreaInfo.getIdArea(),
                                                tdStation.getIdStation(),
                                                tdDayInfo.getTimeSection(),
                                                tdRoomInfo.getIdRoom()
                                        ));
                                    }
                                });
                            }));
                }));
        return stationVos;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveStationSp(TpSp dto) {
        pfPlanStationDao.delStationSp(dto);
        return pfPlanStationDao.saveStationSp(dto) >= 1 ? true : false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveStationAssistant(TpAssistant dto) {
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
}
