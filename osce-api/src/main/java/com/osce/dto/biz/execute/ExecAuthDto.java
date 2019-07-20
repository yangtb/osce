package com.osce.dto.biz.execute;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: ExecDto
 * @Description: 执行dto
 * @Author yangtongbin
 * @Date 2019-07-17
 */
@Setter
@Getter
@ToString
public class ExecAuthDto implements Serializable {

    private static final long serialVersionUID = -5217621444979592758L;

    /**
     * 序列ID
     */
    private Long parIdExecQueue;

    /**
     * 序列操作 2 叫号 3 叫号认证 4 开考 5结束考试 6	缺考
     */
    private Integer parSdExecQueue;

    /**
     * 错误编码
     */
    private Integer parCode;

    /**
     * 错误信息
     */
    private String parMsg;

}
