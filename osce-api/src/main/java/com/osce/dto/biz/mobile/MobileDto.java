package com.osce.dto.biz.mobile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: MobileDto
 * @Description: 移动端参数
 * @Author yangtongbin
 * @Date 2019-07-08
 */
@Setter
@Getter
@ToString
public class MobileDto implements Serializable {

    private static final long serialVersionUID = -8290772814227742842L;

    /**
     * 计划ID
     */
    private Long idPlan;

    /**
     * 考场ID
     */
    private Long idArea;

    /**
     * 时段
     */
    private float timeSection;

    /**
     * 房间ID
     */
    private Long idRoom;


}
