package com.osce.vo.biz.mobile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: MobileQueueVo
 * @Description: 等待学员
 * @Author yangtongbin
 * @Date 2019-08-12
 */
@Setter
@Getter
@ToString
public class MobileQueueVo implements Serializable {

    private static final long serialVersionUID = -8484989484445556209L;

    /**
     * 等待人数
     */
    private Integer waitingNum;

    /**
     * 等待学员队列
     */
    private List<MobileStudentInfoVo> studentQueues;


}
