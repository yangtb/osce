package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: CobSkillDevice
 * @Description: CobSkillDevice
 * @Author yangtongbin
 * @Date 2019-05-15
 */
@Setter
@Getter
@ToString
public class TdSkillDevice implements Serializable {

    private static final long serialVersionUID = 1557887027614L;

    /**
     * 主键
     * 病例模型ID
     */
    private Long idSkillDevice;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 技能病例ID
     */
    private String idSkillCase;

    /**
     * 设备ID
     */
    private Long idDevice;

    /**
     * 设备名称
     */
    private String naDevice;

    /**
     * 设备数量
     */
    private Integer numDevice;

}
