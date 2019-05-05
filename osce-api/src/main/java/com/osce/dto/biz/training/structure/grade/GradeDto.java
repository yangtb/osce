package com.osce.dto.biz.training.structure.grade;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: OrgGrade
 * @Description: 组织_学届管理
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@Setter
@Getter
@ToString
public class GradeDto extends PageParam implements Serializable {

    private static final long serialVersionUID = 1556984315086L;

    /**
     * 学届名称
     */
    private String naGrade;

    /**
     * 机构id
     */
    private Long idOrg;

}
