package com.osce.dto.biz.training.structure.dept;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: DeptDto
 * @Description: 部门dto
 * @Author yangtongbin
 * @Date 2019-05-06
 */
@Setter
@Getter
@ToString
public class DeptDto extends PageParam implements Serializable {

    private static final long serialVersionUID = -4898841200647002170L;

    /**
     * 学届名称
     */
    private String naGrade;

    /**
     * 机构id
     */
    private Long idOrg;
}
