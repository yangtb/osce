package com.osce.dto.system.param;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class ParamDto extends PageParam implements Serializable {

    private static final long serialVersionUID = -7532471455664606363L;

    private String      paramName;          // 字典名称
    private String      status;             // 状态

}
