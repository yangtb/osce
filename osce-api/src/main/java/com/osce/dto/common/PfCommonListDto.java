package com.osce.dto.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PfCommonListDto
 * @Description: 通用参数
 * @Author yangtongbin
 * @Date 2018/9/17 16:52
 */
@Setter
@Getter
@ToString
public class PfCommonListDto implements Serializable {

    private static final long serialVersionUID = 6005656188483189531L;

    /**
     * 业务主键集合
     */
    private List<Long> list;

    /**
     * 当前用户机构id
     */
    private Long currentUserOrgId;

    /**
     * 平台或超级管理员用户
     */
    private boolean platOrSuper;

    /**
     * 扩展id
     */
    private Long extId;

    /**
     * 扩展type
     */
    private String extType;
}
