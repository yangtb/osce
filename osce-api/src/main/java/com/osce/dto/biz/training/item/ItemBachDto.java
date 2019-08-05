package com.osce.dto.biz.training.item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ItemBachDto
 * @Description:
 * @Author yangtongbin
 * @Date 2019-08-02
 */
@Setter
@Getter
@ToString
public class ItemBachDto implements Serializable {

    private static final long serialVersionUID = -1318875385874691256L;

    /**
     * 题目
     */
    private List<ItemBach> items;

    /**
     * 机构id
     */
    private Long idOrg;

    /**
     * 创建人员
     */
    private String creator;

    /**
     * 修改人员
     */
    private String operator;

}
