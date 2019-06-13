package com.osce.vo.biz.plan.template.station;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: TdAreaInfo
 * @Description: 考场数据
 * @Author yangtongbin
 * @Date 2019-06-12
 */
@Setter
@Getter
@ToString
public class TdStation implements Serializable {

    private static final long serialVersionUID = 1601242425858124107L;

    /**
     * 考站ID
     */
    private Long idStation;

    /**
     * 考站名称
     */
    private String naStation;

    /**
     * 1 内科 2 外科 3 妇产科 4 儿科 5 急诊 6 医患沟通 7 神经内科 8 眼鼻喉 9 皮肤科 10 肿瘤 11 传染 12 心理精神 13 循证医学 14 康复 15 伦理
     */
    private String sdStationCa;

    /**
     * 基地名称
     */
    private String sdStationCaText;

    /**
     * 1 理论试题 2 技能操作 3 病史采集
     */
    private String sdSkillCa;

    /**
     * 房间信息
     */
    private List<TdRoomInfo> roomData;

}
