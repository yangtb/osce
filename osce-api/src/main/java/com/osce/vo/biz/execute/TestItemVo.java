package com.osce.vo.biz.execute;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: TestItemVo
 * @Description: 题目选项
 * @Author yangtongbin
 * @Date 2019-07-19
 */
@Setter
@Getter
@ToString
public class TestItemVo implements Serializable {

    private static final long serialVersionUID = -3574758639313243501L;

    /**
     * 题目ID
     */
    private Long idItem;

    /**
     * 题目主干
     */
    private String mainItem;

    /**
     * 答题id
     */
    private Long idWeItem;

    /**
     * 答案
     */
    private String answer;

    /**
     * 得分
     */
    private Integer scoreResult;

    /**
     * 题目选项
     */
    private List<TestItemOptionVo> itemOptions;


}
