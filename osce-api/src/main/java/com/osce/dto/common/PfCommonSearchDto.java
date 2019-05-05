package com.osce.dto.common;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfCommonSearchDto extends PageParam implements Serializable {

    private static final long serialVersionUID = -7008573359759429447L;

    /**
     * 问题内容
     */
    private String keywords;
}
