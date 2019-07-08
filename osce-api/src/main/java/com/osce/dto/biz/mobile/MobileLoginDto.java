package com.osce.dto.biz.mobile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: MobieLoginDto
 * @Description: 移动端-登录
 * @Author yangtongbin
 * @Date 2019-07-08
 */
@Setter
@Getter
@ToString
public class MobileLoginDto implements Serializable {

    private static final long serialVersionUID = 2235293869755697516L;

    /**
     * 授权码 - 明码
     */
    private String authCode;

}
