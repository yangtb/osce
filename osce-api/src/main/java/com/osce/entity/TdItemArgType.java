package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TdItemArgType
 * @Description: 模板_题型参数
 * @Author yangtongbin
 * @Date 2019-06-08
 */
@Setter
@Getter
@ToString
public class TdItemArgType implements Serializable {

    private static final long serialVersionUID = 1559994295226L;

    /**
     * 主键
     * 题型参数ID
     */
    private Long idItemArgType;

    /**
     * 题集ID
     */
    private Long idItemStore;

    /**
     * 1=A1, 2=A2, 3=B1
     */
    private String sdItemCa;

    /**
     * 类型数量
     */
    private Integer numType;

    /**
     * 类型分值
     */
    private Integer scoreType;

}
