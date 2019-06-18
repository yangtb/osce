package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TpStudent
 * @Description:
 * @Author yangtongbin
 * @Date 2019-06-18
 */
@Setter
@Getter
@ToString
public class TpStudent implements Serializable {

    private static final long serialVersionUID = 1560820742309L;

    /**
    * 主键
    * 分配学员ID
    */
    private Long idTpStudent;

    /**
    * 计划ID
    */
    private Long idPlan;

    /**
    * 学员ID
    */
    private Long idUser;


}
