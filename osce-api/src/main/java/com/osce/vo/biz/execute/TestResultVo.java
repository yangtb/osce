package com.osce.vo.biz.execute;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TestResultVo
 * @Description: 学员考试结果统计
 * @Author yangtongbin
 * @Date 2019-07-19
 */
@Setter
@Getter
@ToString
public class TestResultVo implements Serializable {

    private static final long serialVersionUID = -1318755469105053678L;

    /**
     * 正确数
     */
    private Integer rightNum;

    /**
     * 错误数
     */
    private Integer errorNum;

    /**
     * 未完成数
     */
    private Integer noDoneNum;

    /**
     * 得分
     */
    private Integer totalScore;

}
