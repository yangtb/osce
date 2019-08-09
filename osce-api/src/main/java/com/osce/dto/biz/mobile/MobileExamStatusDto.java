package com.osce.dto.biz.mobile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: MobileExamStatusDto
 * @Description: 叫号、开考、缺考登记
 * @Author yangtongbin
 * @Date 2019-08-08
 */
@Setter
@Getter
@ToString
public class MobileExamStatusDto implements Serializable {

    private static final long serialVersionUID = 1720777186163130499L;
    
    /**
     * 序列ID
     */
    private Long idExecQueue;

    /**
     * 序列操作 2 叫号 3 叫号认证 4 开考 5结束考试 6	缺考
     */
    private Integer sdExecQueue;

}
