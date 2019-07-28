package com.osce.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PfTreeSelectTableVo
 * @Description: SelectTree
 * @Author yangtongbin
 * @Date 2019-07-27
 */
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PfTreeSelectTableVo implements Serializable {

    private static final long serialVersionUID = 4335806567264028944L;

    private String value;

    private String name;

    private String disabled;

    private String sdDepartCa;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PfTreeSelectTableVo> children;

}
