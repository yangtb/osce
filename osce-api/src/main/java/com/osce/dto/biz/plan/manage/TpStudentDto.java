package com.osce.dto.biz.plan.manage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: TpStudentDto
 * @Description: 分配学员
 * @Author yangtongbin
 * @Date 2019-06-18
 */
@Setter
@Getter
@ToString
public class TpStudentDto implements Serializable {

    private static final long serialVersionUID = 1560820742309L;

    /**
    * 计划ID
    */
    private Long idPlan;

    /**
    * 学员ids
    */
    private List<Long> idUsers;


}
