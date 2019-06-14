package com.osce.vo.biz.plan.template.sp;

import com.osce.entity.TdSpCase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TdSpCase
 * @Description: 模板_SP病例实例
 * @Author yangtongbin
 * @Date 2019-06-13
 */
@Setter
@Getter
@ToString
public class TdSpCaseVo extends TdSpCase implements Serializable {

    private static final long serialVersionUID = 1560435539746L;

    /**
     * 评分表数量
     */
    private Integer sheetNum;

    /**
     * 评分表总分
     */
    private Integer sheetTotalScore;

    /**
     * 病例来源
     */
    private String naSpCaseFrom;

}
