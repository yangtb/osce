package com.osce.api.biz.plan.template.skill;

import com.osce.dto.biz.training.skill.SkillDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdSkillDevice;
import com.osce.result.PageResult;

/**
 * @ClassName: PfExamSkillService
 * @Description: 技能病例接口
 * @Author yangtongbin
 * @Date 2019-05-15
 */
public interface PfExamSkillService {

    /**
     * 设备列表
     *
     * @param dto
     * @return
     */
    PageResult pageSkillDevice(SkillDto dto);

    /**
     * 增加设备
     *
     * @param dto
     * @return
     */
    Long addSkillDevice(TdSkillDevice dto);

    /**
     * 删除设备
     *
     * @param dto
     * @return
     */
    boolean delSkillDevice(PfBachChangeStatusDto dto);


}
