package com.osce.vo.biz.show;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: ShowDto
 * @Description: 显示屏detail
 * @Author yangtongbin
 * @Date 2019-06-30
 */
@Setter
@Getter
@ToString
public class ShowBigScreenDetailVo implements Serializable {

    private static final long serialVersionUID = -6026943475970981541L;

    /**
     * 序号
     */
    private Long rownum;

    /**
     * 学号
     */
    private Long userCd;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 站点
     */
    private String siteName;

    /**
     * 状态数字
     */
    private Integer statusNum;

    /**
     * 状态
     */
    private String status;

}
