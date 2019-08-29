package com.osce.dto.system.org;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfDeptDto
 * @Description: 班级
 * @Author yangtongbin
 * @Date 2019-08-29
 */
@Setter
@Getter
@ToString
public class PfDeptDto implements Serializable {

    private static final long serialVersionUID = -8065165575922718762L;

    /**
     * 是否当前学届
     */
    private boolean currentGrade;

    /**
     * 当前用户所在机构id
     */
    private Long idOrg;

}
