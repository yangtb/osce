package com.osce.dto.biz.plan.template;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfPaperDto
 * @Description: 试卷设计
 * @Author yangtongbin
 * @Date 2019-06-08
 */
@Setter
@Getter
@ToString
public class PfPaperDto extends PageParam implements Serializable {

    private static final long serialVersionUID = 1559975708033L;

    /**
     * 模板ID
     */
    private Long idModel;

    /**
     * 试卷ID
     */
    private Long idPaper;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 题集ID
     */
    private Long idItemStore;

    /**
     * 激活标志
     */
    private String fgActive;

    /**
     * 搜索关键字
     */
    private String keywords;

    /**
     * 技能类型
     */
    private String sdSkillCa;

}
