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
public class ExecDto implements Serializable {

    private static final long serialVersionUID = -5217621444979592758L;

    /**
     * 身份证id
     */
    private String idCard;

    /**
     * 房间ID
     */
    private Long idRoom;

    /**
     * 机构id
     */
    private Long idOrg;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 考场ID
     */
    private Long idArea;

    /**
     * 时段
     */
    private float timeSection;

}
