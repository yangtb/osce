package com.osce.vo.biz.plan.template.skill;

import com.osce.entity.TdSkillCase;
import com.osce.entity.TdSpCase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TdSkillCaseVo
 * @Description: 模板_Skill病例实例
 * @Author yangtongbin
 * @Date 2019-06-13
 */
@Setter
@Getter
@ToString
public class TdSkillCaseVo extends TdSkillCase implements Serializable {

    private static final long serialVersionUID = 4094768997572198643L;

    /**
     * 评分表数量
     */
    private Integer sheetNum;

    /**
     * 评分表总分
     */
    private Integer sheetTotalScore;

    /**
     * 病例来源
     */
    private String naSkillCaseFrom;

}
