package com.osce.vo.biz.plan.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PaperLeftVo
 * @Description: 试卷左侧列表
 * @Author yangtongbin
 * @Date 2019-06-08
 */
@Setter
@Getter
@ToString
public class PaperLeftVo implements Serializable {

    /**
     * 技能类型
     */
    private String sdSkillCa;

    /**
     * 技能类型
     */
    private String sdSkillCaText;

    /**
     * 基地类型
     */
    private String sdStationCa;

    /**
     * 基地类型
     */
    private String sdStationCaText;


    /**
     * 房间
     */
    private String rooms;

}
