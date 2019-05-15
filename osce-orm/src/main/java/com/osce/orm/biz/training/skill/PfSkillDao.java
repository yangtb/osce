package com.osce.orm.biz.training.skill;


import com.osce.dto.biz.training.skill.SkillDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.CobSkillCase;
import com.osce.entity.CobSkillDevice;

import java.util.List;

/**
 * @ClassName: PfSkillDao
 * @Description: 技能病例
 * @Author yangtongbin
 * @Date 2019-05-14
 */
public interface PfSkillDao {

    /**
     * 获取列表
     *
     * @param dto
     * @return
     */
    List<CobSkillCase> listSkill(SkillDto dto);

    /**
     * 获取总数
     *
     * @param dto
     * @return
     */
    Long countSkill(SkillDto dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long addSkill(CobSkillCase dto);

    /**
     * 编辑
     *
     * @param dto
     * @return
     */
    int editSkill(CobSkillCase dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    int delSkill(PfBachChangeStatusDto dto);

    /**
     * 获取设备列表
     *
     * @param dto
     * @return
     */
    List<CobSkillDevice> listSkillDevice(SkillDto dto);

    /**
     * 获取设备总数
     *
     * @param dto
     * @return
     */
    Long countSkillDevice(SkillDto dto);

    /**
     * 新增设备
     *
     * @param dto
     * @return
     */
    Long addSkillDevice(CobSkillDevice dto);

    /**
     * 编辑设备
     *
     * @param dto
     * @return
     */
    int editSkillDevice(CobSkillDevice dto);

    /**
     * 删除设备
     *
     * @param dto
     * @return
     */
    int delSkillDevice(PfBachChangeStatusDto dto);

}
