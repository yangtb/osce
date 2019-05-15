package com.osce.api.biz.training.skill;

import com.osce.dto.biz.training.skill.SkillDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.CobSkillCase;
import com.osce.entity.CobSkillDevice;
import com.osce.result.PageResult;

/**
 * @ClassName: PfSkillService
 * @Description: 技能病例接口
 * @Author yangtongbin
 * @Date 2019-05-15
 */
public interface PfSkillService {

    /**
     * 列表
     *
     * @param dto
     * @return
     */
    PageResult pageSkill(SkillDto dto);

    /**
     * 增加
     *
     * @param dto
     * @return
     */
    Long addSkill(CobSkillCase dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    boolean delSkill(PfBachChangeStatusDto dto);

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
    Long addSkillDevice(CobSkillDevice dto);

    /**
     * 删除设备
     *
     * @param dto
     * @return
     */
    boolean delSkillDevice(PfBachChangeStatusDto dto);


}
