package com.osce.dto.system.menu;

import com.osce.dto.common.PfCommonListDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfMenuListDto extends PfCommonListDto implements Serializable {

    private static final long serialVersionUID = 321752553115657904L;

    private String status;
}
