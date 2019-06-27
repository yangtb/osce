package com.osce.orm.biz.plan.template;

import com.osce.dto.biz.plan.template.*;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdModel;
import com.osce.entity.TdSite;
import com.osce.vo.biz.plan.template.TdInsStationDetailVo;
import com.osce.vo.biz.plan.template.station.TdStationInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: PfTemplateDao
 * @Description: 模板
 * @Author yangtongbin
 * @Date 2019-05-27
 */
public interface PfTemplateDao {

    /**
     * 获取列表
     *
     * @param dto
     * @return
     */
    List<TdModel> listTemplate(TemplateDto dto);

    /**
     * 获取总数
     *
     * @param dto
     * @return
     */
    Long countTemplate(TemplateDto dto);

    /**
     * 新增模板
     *
     * @param tdModel
     * @return
     */
    int addTemplate(TdModel tdModel);

    /**
     * 编辑模板
     *
     * @param tdModel
     * @return
     */
    int editTemplate(TdModel tdModel);

    /**
     * add 考场
     *
     * @param tdAreaDto
     * @return
     */
    int addTdArea(TdAreaDto tdAreaDto);

    /**
     * edit 考场
     *
     * @param tdAreaDto
     * @return
     */
    int editTdArea(TdAreaDto tdAreaDto);

    /**
     * add 考站
     *
     * @param tdStationDto
     * @return
     */
    int addTdStation(TdStationDto tdStationDto);

    /**
     * edit 考站
     *
     * @param tdStationDto
     * @return
     */
    int editTdStation(TdStationDto tdStationDto);

    /**
     * add 站点
     *
     * @param tdSite
     * @return
     */
    int addTdSite(TdSite tdSite);

    /**
     * edit 站点
     *
     * @param tdSite
     * @return
     */
    int editTdSite(TdSite tdSite);

    /**
     * 删除站点
     *
     * @param idModel
     * @return
     */
    int delSite(@Param("idModel") Long idModel);

    /**
     * 删除站点
     *
     * @param idModel
     * @return
     */
    int delStation(@Param("idModel") Long idModel);

    /**
     * 删除站点
     *
     * @param idModel
     * @return
     */
    int delArea(@Param("idModel") Long idModel);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    int delTemplate(PfBachChangeStatusDto dto);

    /**
     * 排站
     *
     * @param dto
     */
    void callStationModelOrder(PfStationModelDto dto);

    /**
     * 查询模板信息
     *
     * @param idModel 模板id
     * @param idOrg   机构id
     * @return
     */
    TdModel selectTemplateInfoById(@Param("idModel") Long idModel,
                                   @Param("idOrg") Long idOrg);

    /**
     * 获取考场信息
     *
     * @param idModel 模板id
     * @return
     */
    List<TdAreaDto> listTdArea(@Param("idModel") Long idModel);

    /**
     * 获取排站信息
     *
     * @param idModel 模板id
     * @return
     */
    List<TdStationInfoVo> selectStationInfo(@Param("idModel") Long idModel);

    /**
     * 查询技能名称
     *
     * @param sdSkillCa
     * @param idPaper
     * @return
     */
    String selectSkillName(@Param("sdSkillCa") String sdSkillCa,
                           @Param("idPaper") Long idPaper);

    /**
     * 修改技能
     *
     * @param dto
     * @return
     */
    int editSkill(TdInsStationDto dto);

    /**
     * 获取模拟排考信息
     *
     * @param idModel 模板id
     * @return
     */
    List<TdStationInfoVo> selectStationDetail(@Param("idModel") Long idModel);

    /**
     * 删除站点
     *
     * @param idStation
     * @return
     */
    int delStationById(Long idStation);

    /**
     * 删除site
     *
     * @param idStation
     * @return
     */
    int delSiteByIdStation(Long idStation);

    /**
     * 撤销排站 - 详情
     *
     * @param idModel 模板id
     * @return
     */
    int delTdInsStationDetail(@Param("idModel") Long idModel);

    /**
     * 撤销排站
     *
     * @param idModel 模板id
     * @return
     */
    int delTdInsStation(@Param("idModel") Long idModel);

    /**
     * 保存排站试卷
     *
     * @param dto
     * @return
     */
    int saveStationPaper(PfAddTpPaperDto dto);

    /**
     * 查询模板名称
     *
     * @param idModel 模板id
     * @return
     */
    String selectModelName(Long idModel);

}
