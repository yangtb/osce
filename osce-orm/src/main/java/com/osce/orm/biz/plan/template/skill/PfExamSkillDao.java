package com.osce.orm.biz.plan.template.skill;


import com.osce.dto.biz.training.skill.SkillDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdSkillDevice;

import java.util.List;

/**
 * @ClassName: PfExamSkillDao
 * @Description: 技能病例
 * @Author yangtongbin
 * @Date 2019-05-14
 */
public interface PfExamSkillDao {

    /**
     * 获取设备列表
     *
     * @param dto
     * @return
     */
    List<TdSkillDevice> listSkillDevice(SkillDto dto);

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
    Long addSkillDevice(TdSkillDevice dto);

    /**
     * 编辑设备
     *
     * @param dto
     * @return
     */
    int editSkillDevice(TdSkillDevice dto);

    /**
     * 删除设备
     *
     * @param dto
     * @return
     */
    int delSkillDevice(PfBachChangeStatusDto dto);

}
