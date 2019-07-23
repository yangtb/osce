package com.osce.dto.biz.monitor;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: MonitorDto
 * @Description: 考场监控
 * @Author yangtongbin
 * @Date 2019-07-23
 */
@Setter
@Getter
@ToString
public class MonitorDto extends PageParam implements Serializable {

    private static final long serialVersionUID = -861391956336866601L;

    /**
     * 机构id
     */
    private Long idOrg;

    /**
     * 计划id
     */
    private Long idPlan;

    /**
     * 考场
     */
    private Long idArea;

    /**
     * 时间：上午、下午
     */
    private float timeSection;

    /**
     * 学生id
     */
    private Long idUserStudent;

}
