package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: OrgSpDefine
 * @Description: OrgSp
 * @Author yangtongbin
 * @Date 2019-05-10
 */
@Setter
@Getter
@ToString
public class OrgSpDefine implements Serializable {

    private static final long serialVersionUID = 5072059484067178668L;

    /**
     * 主键
     * SP标签ID
     */
    private Long idSpTag;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 标签内容
     */
    private String descript;
}
