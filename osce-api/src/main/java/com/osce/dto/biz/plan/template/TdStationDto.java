package com.osce.dto.biz.plan.template;

import com.osce.entity.TdSite;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: TdStation
 * @Description: 模板_考站
 * @Author yangtongbin
 * @Date 2019-05-25
 */
@Setter
@Getter
@ToString
public class TdStationDto implements Serializable {

    private static final long serialVersionUID = 1558791623113L;

    /**
     * 主键
     * 考站ID
     */
    private Long idStation;

    /**
     * 考场ID
     */
    private Long idArea;

    /**
     * 考站名称
     */
    private String naStation;

    /**
     * 1 内科 2 外科 3 妇产科 4 儿科 5 急诊 6 医患沟通 7 神经内科 8 眼鼻喉 9 皮肤科 10 肿瘤 11 传染 12 心理精神 13 循证医学 14 康复 15 伦理
     */
    private String sdStationCa;

    /**
     * 1 理论试题 2 技能操作 3 病史采集
     */
    private String sdSkillCa;

    /**
     * 站点耗时
     */
    private Date minCost;

    /**
     * 必过标志
     */
    private String fgMust;

    /**
     * 站点
     */
    private List<TdSite> tdSites;

}
