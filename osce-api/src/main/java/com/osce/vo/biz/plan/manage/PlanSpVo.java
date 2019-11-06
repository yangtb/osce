package com.osce.vo.biz.plan.manage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PlanSpVo
 * @Description: sp
 * @Author yangtongbin
 * @Date 2019-11-05
 */
@Setter
@Getter
@ToString
public class PlanSpVo implements Serializable {

    private static final long serialVersionUID = -7162523379807579111L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 身份证号
     */
    private String idcard;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 身高
     */
    private String height;

    /**
     * 体重
     */
    private String weight;

    /**
     * 暂存sp id
     */
    private Long idTpSpCache;

}
