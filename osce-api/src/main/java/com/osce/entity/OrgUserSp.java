package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: OrgUserSp
 * @Description: 组织_SP标签记录
 * @Author yangtongbin
 * @Date 2019-05-12
 */
@Setter
@Getter
@ToString
public class OrgUserSp implements Serializable {

    private static final long serialVersionUID = 1557656686107L;

    /**
     * 主键
     * SP标签记录ID
     */
    private Long idSpTagRecord;

    /**
     * 用户ID
     */
    private Long idUser;

    /**
     * SP标签ID
     */
    private Long idSpTag2;

    /**
     * 标签值
     */
    private String value;


}
