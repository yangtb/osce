package com.osce.api.biz.plan.template;

import com.osce.dto.biz.plan.template.PfAddTpPaperDto;
import com.osce.dto.biz.plan.template.TdInsStationDto;
import com.osce.dto.biz.plan.template.TdModelInfo;
import com.osce.dto.biz.plan.template.TemplateDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.result.PageResult;
import com.osce.vo.biz.plan.template.TdInsStationDetailVo;
import com.osce.vo.biz.plan.template.station.TdStationInfoVo;

import java.util.List;

/**
 * @ClassName: PfTemplateService
 * @Description: 实训模板接口
 * @Author yangtongbin
 * @Date 2019-05-25
 */
public interface PfTemplateService {

    /**
     * 列表
     *
     * @param dto
     * @return
     */
    PageResult pageTemplate(TemplateDto dto);

    /**
     * 考站定义
     *
     * @param dto
     * @return
     */
    Long addTemplate(TdModelInfo dto);

    /**
     * 排站
     *
     * @param idModel
     */
    void callStationModelOrder(Long idModel);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    boolean delTemplate(PfBachChangeStatusDto dto);

    /**
     * 获取考站定义信息
     *
     * @param idModel 模板id
     * @param idOrg   机构id
     * @return
     */
    TdModelInfo selectTdModelInfo(Long idModel, Long idOrg);

    /**
     * 获取排站信息
     *
     * @param idModel 模板id
     * @return
     */
    List<TdStationInfoVo> selectStationInfo(Long idModel);

    /**
     * 排站信息 - 修改技能
     *
     * @param dto
     * @return
     */
    boolean editSkill(TdInsStationDto dto);

    /**
     * 获取模拟排考信息
     *
     * @param idModel 模板id
     * @return
     */
    List<TdStationInfoVo> selectStationDetail(Long idModel);

    /**
     * 删除考站
     *
     * @param dto
     * @return
     */
    boolean delStation(TemplateDto dto);

    /**
     * 撤销排站
     *
     * @param dto
     * @return
     */
    boolean cancelStation(TemplateDto dto);

    /**
     * 保存排站试卷
     *
     * @param dto
     * @return
     */
    boolean saveStationPaper(PfAddTpPaperDto dto);

}
