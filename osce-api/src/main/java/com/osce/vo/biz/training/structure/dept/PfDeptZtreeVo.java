package com.osce.vo.biz.training.structure.dept;

import com.osce.vo.PfCommonZtreeVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfOrgZtreeVo
 * @Description: 部门ztree
 * @Author yangtongbin
 * @Date 2019-05-05
 */
@Setter
@Getter
@ToString
public class PfDeptZtreeVo extends PfCommonZtreeVo implements Serializable {

    /**
     * 机构ID
     */
    private Long idDepart;

    /**
     * 年级
     */
    private Long idGrade;

    /**
     * 1 院系 2 专业 3 班级
     */
    private String sdDepartCa;

    /**
     * 激活标志
     */
    private String fgActive;

}
