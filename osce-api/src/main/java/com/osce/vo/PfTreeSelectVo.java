package com.osce.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PfTreeSelectVo
 * @Description: TreeSelect
 * @Author yangtongbin
 * @Date 2019-05-09
 */
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PfTreeSelectVo implements Serializable {

    private static final long serialVersionUID = -4044844488756130510L;

    private Long id;

    private String name;

    private boolean open;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PfTreeSelectVo> children;

}
