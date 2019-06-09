package com.osce.dto.biz.training.item;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: ItemDto
 * @Description: 题库dto
 * @Author yangtongbin
 * @Date 2019-05-14
 */
@Setter
@Getter
@ToString
public class ItemDto extends PageParam implements Serializable {

    private static final long serialVersionUID = -5199724562569567793L;

    /**
     * 机构id
     */
    private Long idOrg;

    /**
     * 题目ID
     */
    private Long idItem;

    /**
     * 题集ID
     */
    private Long idItemStore;

    /**
     * 题集名称
     */
    private String naItemStore;

    /**
     * 目录ID
     */
    private Long idItemSection;

}
