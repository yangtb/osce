package com.osce.vo.biz.show;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: ShowStuVo
 * @Description: 考试登记信息
 * @Author yangtongbin
 * @Date 2019-07-11
 */
@Setter
@Getter
@ToString
public class ShowStuVo implements Serializable {

    private static final long serialVersionUID = 8603473722199675492L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 联系电话
     */
    private String phoneNo;

    /**
     * 身份证号
     */
    private String idcard;

    /**
     * 头像url
     */
    private String photoAddr;

    /**
     * 入场序号
     */
    private Integer noReg;

    /**
     * 已登记数
     */
    private Integer registerNum;

    /**
     * 总数
     */
    private Integer stuTotalNum;

}
