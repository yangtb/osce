package com.osce.service.biz.plan.template;

import com.osce.api.biz.plan.template.PfTemplateService;
import com.osce.dto.biz.plan.template.*;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdModel;
import com.osce.entity.TdSite;
import com.osce.orm.biz.plan.template.PfTemplateDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.vo.biz.plan.template.TdInsStationDetailVo;
import com.osce.vo.biz.plan.template.TdInsStationVo;
import com.osce.vo.biz.plan.template.TdModelVo;
import com.sm.open.care.core.exception.BizRuntimeException;
import org.apache.dubbo.config.annotation.Service;
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
     *     5、排站
     * </pre>
     *
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public TdModelVo addTemplate(TdModelInfo dto) {
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
        // 2、保存考场
        for (TdAreaDto tdAreaDto : dto.getTdAreas()) {
            tdAreaDto.setIdModel(tdModel.getIdModel());
            if (tdAreaDto.getIdArea() == null) {
                pfTemplateDao.addTdArea(tdAreaDto);
            } else {
                pfTemplateDao.editTdArea(tdAreaDto);
            }
            tdModelVo.setIdArea(tdAreaDto.getIdArea());
            // 3、保存考站
            for (TdStationDto tdStationDto : tdAreaDto.getTdStations()) {
                tdStationDto.setIdArea(tdAreaDto.getIdArea());
                if (tdStationDto.getIdStation() == null) {
                    pfTemplateDao.addTdStation(tdStationDto);
                } else {
                    pfTemplateDao.editTdStation(tdStationDto);
                }
                tdModelVo.setIdStation(tdStationDto.getIdStation());
                // 4、保存站点
                for (TdSite tdSite : tdStationDto.getTdSites()) {
                    tdSite.setIdArea(tdAreaDto.getIdArea());
                    tdSite.setIdStation(tdStationDto.getIdStation());
                    if (tdSite.getIdSite() == null) {
                        pfTemplateDao.addTdSite(tdSite);
                    } else {
                        pfTemplateDao.editTdSite(tdSite);
                    }
                    tdModelVo.setIdSite(tdSite.getIdSite());
                }
            }
        }
        // 5、排站
        PfStationModelDto pfStationModelDto = new PfStationModelDto();
        pfStationModelDto.setParIdDemo(tdModel.getIdModel());
        pfTemplateDao.callStationModelOrder(pfStationModelDto);
        if (pfStationModelDto.getParCode() != 0) {
            throw new BizRuntimeException(String.valueOf(pfStationModelDto.getParCode()), pfStationModelDto.getParMsg());
        }
        return tdModelVo;
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
    public TdModelInfo selectTdModelInfo(Long idModel) {
        TdModelInfo tdModelInfo = new TdModelInfo();
        // 1、模板信息
        TdModel tdModel = pfTemplateDao.selectTemplateInfoById(idModel);
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
    public List<TdInsStationVo> selectStationInfo(Long idModel) {
        List<TdInsStationVo> stationVos = pfTemplateDao.selectStationInfo(idModel);
        for (TdInsStationVo tdInsStationVo : stationVos) {
            String idPaperText = pfTemplateDao.selectSkillName(tdInsStationVo.getSdSkillCa(), tdInsStationVo.getIdPaper());
            tdInsStationVo.setIdPaperText(idPaperText);
        }
        return stationVos;
    }

    @Override
    public boolean editSkill(TdInsStationDto dto) {
        int num = pfTemplateDao.editSkill(dto);
        return num == 1 ? true : false;
    }

    @Override
    public List<TdInsStationDetailVo> selectStationDetail(Long idModel) {
        return pfTemplateDao.selectStationDetail(idModel);
    }

}
