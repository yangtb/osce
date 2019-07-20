package com.osce.vo.biz.execute;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TestItemVo
 * @Description: 题目选项
 * @Author yangtongbin
 * @Date 2019-07-19
 */
@Setter
@Getter
@ToString
public class TestItemOptionVo implements Serializable {

    private static final long serialVersionUID = -3761813463778746093L;

    /**
     * 题目选项ID
     */
    private Long idItemOption;

    /**
     * 选项号码
     */
    private String cdIteStr;

    /**
     * 选项
     */
    private String naOption;


}
