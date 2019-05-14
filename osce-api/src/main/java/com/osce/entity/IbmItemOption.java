package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: IbmItemOption
 * @Description: IbmItemOption
 * @Author yangtongbin
 * @Date 2019-05-14
 */
@Setter
@Getter
@ToString
public class IbmItemOption implements Serializable {

    private static final long serialVersionUID = 1557804846604L;


    /**
     * 主键
     * 选项ID
     */
    private Long idItemOption;

    /**
     * 题目ID
     */
    private Long idItem;

    /**
     * 选项编码
     */
    private String cdIte;

    /**
     * 选项内容
     */
    private String naOption;

    /**
     * 正确答案标志
     */
    private String fgRight;

}
