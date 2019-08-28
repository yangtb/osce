package com.osce.dto.biz.plan.template;

import com.osce.entity.TdItemArgLevel;
import com.osce.entity.TdItemArgType;
import com.osce.entity.TdItemSection;
import com.osce.vo.biz.plan.template.PaperItemTotalVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PfPaperDto
 * @Description: 试卷参数
 * @Author yangtongbin
 * @Date 2019-06-08
 */
@Setter
@Getter
@ToString
public class PfPaperParam implements Serializable {

    private static final long serialVersionUID = 1559975708033L;

    /**
     * 模板ID
     */
    private Long idModel;

    /**
     * 目录信息
     */
    private List<TdItemSection> tdItemSections;

    /**
     * 目录信息ids
     */
    private List<Long> idItemSections;

    /**
     * 题型参数
     */
    private List<TdItemArgType> tdItemArgTypes;

    /**
     * 难易程度比例
     */
    private List<TdItemArgLevel> sdItemLevels;

    /**
     * 题目总数
     */
    private List<PaperItemTotalVo> itemTotals;

}
