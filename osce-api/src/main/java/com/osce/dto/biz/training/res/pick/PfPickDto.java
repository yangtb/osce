package com.osce.dto.biz.training.res.pick;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfPickDto
 * @Description: 领料计划
 * @Author yangtongbin
 * @Date 2019-08-19
 */
@Setter
@Getter
@ToString
public class PfPickDto extends PageParam implements Serializable {

    private static final long serialVersionUID = -106453937456576129L;

    /**
     * 机构id
     */
    private Long idOrg;

    /**
     * 0 未领 1 已领
     */
    private String fgPicking;

}
