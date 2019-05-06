package com.osce.vo.system.org;

import com.osce.vo.PfCommonZtreeVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfOrgZtreeVo
 * @Description: 机构ztree
 * @Author yangtongbin
 * @Date 2019-05-05
 */
@Setter
@Getter
@ToString
public class PfOrgZtreeVo extends PfCommonZtreeVo implements Serializable {
    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 激活标志
     */
    private String fgActive;

}
