package com.osce.dto.biz.plan.manage;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: AssistantDto
 * @Description: sp
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@Setter
@Getter
@ToString
public class AssistantDto extends PageParam implements Serializable {

    private static final long serialVersionUID = 3726091871862302827L;

    /**
     * 电话 or 姓名
     */
    private String keywords;

    /**
     * 机构id
     */
    private Long idOrg;

}
