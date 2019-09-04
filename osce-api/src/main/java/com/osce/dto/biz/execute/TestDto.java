package com.osce.dto.biz.execute;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TestDto
 * @Description: 考试
 * @Author yangtongbin
 * @Date 2019-07-19
 */
@Setter
@Getter
@ToString
public class TestDto implements Serializable {

    private static final long serialVersionUID = 7445834863796072199L;

    /**
     * 执行ID
     */
    private Long idExecQueue;

    /**
     * 试卷ID
     */
    private Long idPaper;

    /**
     * 执行id
     */
    private Long idExec;

}
