package com.osce.dto.biz.plan.template;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TemplateDto
 * @Description: 模板
 * @Author yangtongbin
 * @Date 2019-05-27
 */
@Setter
@Getter
@ToString
public class TemplateDto extends PageParam implements Serializable {

    private static final long serialVersionUID = 4512838127154302689L;

    /**
     * 机构id
     */
    private Long idOrg;

    /**
     * 模板名称
     */
    private String naModel;

    /**
     * 模板id
     */
    private Long idModel;

}
