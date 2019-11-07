package com.osce.dto.biz.training.structure.sp;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: SpDto
 * @Description: sp
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@Setter
@Getter
@ToString
public class SpDto extends PageParam implements Serializable {

    private static final long serialVersionUID = 1556984315086L;

    /**
     * 电话 or 姓名
     */
    private String keywords;

    /**
     * 机构id
     */
    private Long idOrg;

    /**
     * 计划ID
     */
    private Long idPlan;

}
