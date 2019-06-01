package com.osce.dto.biz.plan.template;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.osce.entity.TdArea;
import com.osce.entity.TdModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: TdModelDto
 * @Description: 考站定义
 * @Author yangtongbin
 * @Date 2019-06-01
 */
@Setter
@Getter
@ToString
public class TdModelInfo implements Serializable {

    /**
     * 模板
     */
    private TdModel tdModel;

    /**
     * 考场
     */
    private List<TdAreaDto> tdAreas;




    /**
     * 机构ID
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
