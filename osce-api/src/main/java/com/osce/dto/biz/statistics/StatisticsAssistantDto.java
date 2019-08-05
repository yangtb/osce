package com.osce.dto.biz.statistics;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: AssistantDto
 * @Description: 考官
 * @Author yangtongbin
 * @Date 2019-08-05
 */
@Setter
@Getter
@ToString
public class StatisticsAssistantDto extends PageParam implements Serializable {

    private static final long serialVersionUID = 2199138745798640158L;

    /**
     * 执行id
     */
    private Long idExec;

    /**
     * 1 主考官 2 考官 3 中控考官
     */
    private String cdAssistantCa;

}
