package com.osce.vo.biz.execute;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TestItemAnswerVo
 * @Description: 答案
 * @Author yangtongbin
 * @Date 2019-07-19
 */
@Setter
@Getter
@ToString
public class TestItemAnswerVo implements Serializable {

    private static final long serialVersionUID = -3574758639313243501L;

    /**
     * 默认分值
     */
    private Integer scoreDefault;

    /**
     * 答案
     */
    private String cdIteStr;


}
