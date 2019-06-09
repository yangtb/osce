package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TdItemArgLevel
 * @Description: 模板_难易比例参数
 * @Author yangtongbin
 * @Date 2019-06-08
 */
@Setter
@Getter
@ToString
public class TdItemArgLevel implements Serializable {

    private static final long serialVersionUID = 1559994292273L;


    /**
     * 主键
     * 难易比例参数ID
     */
    private Long idItemArgLevel;

    /**
     * 题集ID
     */
    private Long idItemStore;

    /**
     * 1 易 2 较易 3 中 4 难 5 较难
     */
    private String sdItemLevel;

    /**
     * 难度比例%
     */
    private Integer weightLevel;


}
