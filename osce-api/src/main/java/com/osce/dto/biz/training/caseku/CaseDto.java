package com.osce.dto.biz.training.caseku;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: CaseDto
 * @Description: 病例dto
 * @Author yangtongbin
 * @Date 2019-05-12
 */
@Setter
@Getter
@ToString
public class CaseDto extends PageParam implements Serializable {

    private static final long serialVersionUID = 1884737987077356819L;

    /**
     * 机构id
     */
    private Long idOrg;

    /**
     * 病例id
     */
    private String idCase;

    /**
     * 病例名称
     */
    private String naSpCase;

    /**
     * 评分表ID
     */
    private Long idScoreSheet;
}
