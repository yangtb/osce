package com.osce.dto.biz.training.skill;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: SkillDto
 * @Description: 技能库管理
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@Setter
@Getter
@ToString
public class SkillDto extends PageParam implements Serializable {

    private static final long serialVersionUID = 1556984315086L;

    /**
     * 机构id
     */
    private Long idOrg;

    /**
     * 技能病例名称
     */
    private String naSkillCase;

    /**
     * 技能病例ID
     */
    private Long idSkillCase;

    /**
     * 设备名称
     */
    private String naDevice;


}
