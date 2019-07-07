package com.osce.vo.biz.show;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: ShowAioRegisteredVo
 * @Description: 一体机 - 已登记
 * @Author yangtongbin
 * @Date 2019-06-30
 */
@Setter
@Getter
@ToString
public class ShowAioRegisteredVo implements Serializable {

    private static final long serialVersionUID = 8634256215333137033L;

    /**
     * 入场登记ID
     */
    private Long idWaitingReg;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 登记序号
     */
    private String noReg;

    /**
     * 手机号
     */
    private String phoneNo;

    /**
     * 身份证号
     */
    private String idcard;

    /**
     * 入场时间
     */
    private String gmtReg;


}
