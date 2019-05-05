package com.osce.dto.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PfCommonListDto
 * @Description: 通用参数:批量修改状态
 * @Author yangtongbin
 * @Date 2018/9/17 16:52
 */
@Setter
@Getter
@ToString
public class PfBachChangeStatusDto implements Serializable {

    private static final long serialVersionUID = 6005656188483189531L;

    private List<Long> list;

    private String status;

    /**
     * 修改人员
     */
    private String operator;

    /**
     * 操作类型 del=删除 updateStatus=修改状态
     */
    private String operationType;

    /**
     * 扩展ID，公用
     */
    private Long extId;

    /**
     * 扩展类型
     */
    private String extType;
}
