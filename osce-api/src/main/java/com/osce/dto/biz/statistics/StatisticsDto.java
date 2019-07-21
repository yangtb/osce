package com.osce.dto.biz.statistics;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: StatisticsDto
 * @Description: 统计
 * @Author yangtongbin
 * @Date 2019-07-20
 */
@Setter
@Getter
@ToString
public class StatisticsDto extends PageParam implements Serializable {

    private static final long serialVersionUID = 5099860794897999869L;

    /**
     * 机构id
     */
    private Long idOrg;
}
