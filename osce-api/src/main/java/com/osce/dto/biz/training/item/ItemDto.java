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

}
