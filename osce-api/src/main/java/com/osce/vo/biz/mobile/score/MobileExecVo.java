package com.osce.vo.biz.mobile.score;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: MobileExecVo
 * @Description: 执行信息
 * @Author yangtongbin
 * @Date 2019-08-17
 */
@Setter
@Getter
@ToString
public class MobileExecVo implements Serializable {

    private static final long serialVersionUID = -3269687316753548419L;

    /**
     * 考官ID
     */
    private Long idUser;

    /**
     * 评分表ID
     */
    private Long idScoreSheet;

    /**
     * 1 理论试题 2 技能操作 3 病史采集
     */
    private String sdSkillCa;

}
