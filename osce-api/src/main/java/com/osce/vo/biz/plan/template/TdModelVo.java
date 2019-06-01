package com.osce.vo.biz.plan.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TdModelVo
 * @Description: 考站定义
 * @Author yangtongbin
 * @Date 2019-06-01
 */
@Setter
@Getter
@ToString
public class TdModelVo implements Serializable {

    private static final long serialVersionUID = -434984873252679307L;

    /**
     * 模板ID
     */
    private Long idModel;

    /**
     * 考场ID
     */
    private Long idArea;

    /**
     * 考站ID
     */
    private Long idStation;

    /**
     * 站点ID
     */
    private Long idSite;

}
