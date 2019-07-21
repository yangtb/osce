package com.osce.vo.biz.statistics;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TdModel
 * @Description: 模板_模板管理
 * @Author yangtongbin
 * @Date 2019-05-25
 */
@Setter
@Getter
@ToString
public class StatisticsTestResultVo implements Serializable {

    private static final long serialVersionUID = -6873783614886332991L;

    /**
     * 主键
     * 模板ID
     */
    private Long idModel;


}
