package com.osce.dto.biz.plan.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TemplateDto
 * @Description: 模板_排站
 * @Author yangtongbin
 * @Date 2019-05-27
 */
@Setter
@Getter
@ToString
public class TdInsStationDto implements Serializable {

    private static final long serialVersionUID = 4512838127154302689L;

    /**
     * 主键
     * 排站ID
     */
    private Long idInsStation;

    /**
     * 技能ID
     */
    private Long idPaper;

    /**
     * 是否应用到该考站的所有时段 1=是 0=否
     */
    private int status;

}
