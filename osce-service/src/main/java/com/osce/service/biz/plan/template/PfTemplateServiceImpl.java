package com.osce.service.biz.plan.template;

import com.osce.api.biz.plan.template.PfTemplateService;
import com.osce.dto.biz.plan.template.*;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdModel;
import com.osce.entity.TdSite;
import com.osce.exception.RestException;
import com.osce.orm.biz.plan.template.PfTemplateDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.vo.biz.plan.template.TdModelVo;
import com.osce.vo.biz.plan.template.station.TdStationInfoVo;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PfTemplateServiceImpl
 * @Description: 实训模板管理
 * @Author yangtongbin
 * @Date 2019-05-27
 */
@Service
public class PfTemplateServiceImpl implements PfTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(PfTemplateServiceImpl.class);

    @Resource
    private PfTemplateDao pfTemplateDao;

    @Override
    public PageResult pageTemplate(TemplateDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfTemplateDao.countTemplate(dto),
                pfTemplateDao.listTemplate(dto));
    }

    /**
     * 考站定义 add or edit
     *
     * <pre>
     *     1、保存模板
     *     2、保存考场
     *     3、保存考站
     *     4、保存站点
     * </pre>
     *
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long addTemplate(TdModelInfo dto) {
        TdModelVo tdModelVo = new TdModelVo();
        // 1、保存模板
        TdModel tdModel = dto.getTdModel();
        tdModel.setCreator(dto.getCreator());
        tdModel.setOperator(dto.getOperator());
        tdModel.setIdOrg(dto.getIdOrg());
        if (tdModel.getIdModel() == null) {
            pfTemplateDao.addTemplate(tdModel);
        } else {
            pfTemplateDao.editTemplate(tdModel);
        }
        tdModelVo.setIdModel(tdModel.getIdModel());
        // 删除考场信息
        pfTemplateDao.delSite(tdModel.getIdModel());
        pfTemplateDao.delStation(tdModel.getIdModel());
        pfTemplateDao.delArea(tdModel.getIdModel());
        // 2、保存考场
        int i = 0;
        for (TdAreaDto tdAreaDto : dto.getTdAreas()) {
            i++;
            tdAreaDto.setIdModel(tdModel.getIdModel());
            pfTemplateDao.addTdArea(tdAreaDto);
            tdModelVo.setIdArea(tdAreaDto.getIdArea());
            // 3、保存考站
            int j = 0;
            for (TdStationDto tdStationDto : tdAreaDto.getTdStations()) {
                j++;
                tdStationDto.setIdArea(tdAreaDto.getIdArea());
                tdStationDto.setNaStation("考站" + i + "-" + j);
                pfTemplateDao.addTdStation(tdStationDto);
                tdModelVo.setIdStation(tdStationDto.getIdStation());
                // 4、保存站点
                for (TdSite tdSite : tdStationDto.getTdSites()) {
                    tdSite.setIdArea(tdAreaDto.getIdArea());
                    tdSite.setIdStation(tdStationDto.getIdStation());
                    pfTemplateDao.addTdSite(tdSite);
                    tdModelVo.setIdSite(tdSite.getIdSite());
                }
            }
        }
        return tdModel.getIdModel();
    }

    @Override
    public void callStationModelOrder(Long idModel) {
        // 5、排站
        PfStationModelDto pfStationModelDto = new PfStationModelDto();
        pfStationModelDto.setParIdDemo(idModel);
        pfTemplateDao.callStationModelOrder(pfStationModelDto);
        if (pfStationModelDto.getParCode() != 0) {
            throw new RestException(String.valueOf(pfStationModelDto.getParCode()), pfStationModelDto.getParMsg());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delTemplate(PfBachChangeStatusDto dto) {
        int num = pfTemplateDao.delTemplate(dto);
        return num >= 1 ? true : false;
    }

    /**
     * 获取考站定义信息
     *
     * <pre>
     *     1、模板信息
     *     2、考场信息
     *     3、考站信息
     *     4、站点信息
     * </pre>
     *
     * @param idModel 模板id
     * @return
     */
    @Override
    public TdModelInfo selectTdModelInfo(Long idModel, Long idOrg) {
        TdModelInfo tdModelInfo = new TdModelInfo();
        // 1、模板信息
        TdModel tdModel = pfTemplateDao.selectTemplateInfoById(idModel, idOrg);
        if (tdModel == null) {
            throw new RuntimeException("模板记录不存在");
        }
        tdModelInfo.setTdModel(tdModel);
        // 2、考场信息
        List<TdAreaDto> tdAreas = pfTemplateDao.listTdArea(idModel);
        tdModelInfo.setTdAreas(tdAreas);
        return tdModelInfo;
    }

    @Override
    public String selectModelName(Long idModel) {
        return pfTemplateDao.selectModelName(idModel);
    }

    @Override
    public List<TdStationInfoVo> selectStationInfo(Long idModel) {
        List<TdStationInfoVo> stationVos = pfTemplateDao.selectStationInfo(idModel);
        stationVos.forEach(tdInsStationVo -> tdInsStationVo.getDayData().forEach(tdDayInfo -> { tdDayInfo.getAreaData().forEach(tdAreaInfo -> tdAreaInfo.getStationData().forEach(tdStation -> {
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
    public boolean editSkill(TdInsStationDto dto) {
        int num = pfTemplateDao.editSkill(dto);
        return num == 1 ? true : false;
    }

    @Override
    public List<TdStationInfoVo> selectStationDetail(Long idModel) {
        return pfTemplateDao.selectStationDetail(idModel);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delStation(TemplateDto dto) {
        pfTemplateDao.delStationById(dto.getIdStation());
        pfTemplateDao.delSiteByIdStation(dto.getIdStation());
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean cancelStation(TemplateDto dto) {
        pfTemplateDao.delTdInsStationDetail(dto.getIdModel());
        pfTemplateDao.delTdInsStation(dto.getIdModel());
        return true;
    }

    @Override
    public boolean saveStationPaper(PfAddTpPaperDto dto) {
        int num = pfTemplateDao.saveStationPaper(dto);
        return num >= 1 ? true : false;
    }

}
