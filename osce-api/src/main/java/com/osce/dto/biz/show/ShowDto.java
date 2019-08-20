package com.osce.dto.biz.show;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: ShowDto
 * @Description: 显示屏
 * @Author yangtongbin
 * @Date 2019-06-30
 */
@Setter
@Getter
@ToString
public class ShowDto extends PageParam implements Serializable {

    private static final long serialVersionUID = -861391956336866601L;

    /**
     * 机构id
     */
    private Long idOrg;

    /**
     * 计划id
     */
    private Long idPlan;

    /**
     * 考场
     */
    private Long idArea;

    /**
     * 时间：上午、下午
     */
    private float timeSection;

    /**
     * 学生id
     */
    private Long idUserStudent;

    /**
     * 上午下午标识
     */
    private int amPmFlag;

}
