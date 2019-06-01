package com.osce.api.biz.plan.template;

import com.osce.dto.biz.plan.template.TdInsStationDto;
import com.osce.dto.biz.plan.template.TdModelInfo;
import com.osce.dto.biz.plan.template.TemplateDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.result.PageResult;
import com.osce.vo.biz.plan.template.TdInsStationDetailVo;
import com.osce.vo.biz.plan.template.TdInsStationVo;
import com.osce.vo.biz.plan.template.TdModelVo;

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
    TdModelVo addTemplate(TdModelInfo dto);

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
     * @return
     */
    TdModelInfo selectTdModelInfo(Long idModel);

    /**
     * 获取排站信息
     *
     * @param idModel 模板id
     * @return
     */
    List<TdInsStationVo> selectStationInfo(Long idModel);

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
    List<TdInsStationDetailVo> selectStationDetail(Long idModel);


}
