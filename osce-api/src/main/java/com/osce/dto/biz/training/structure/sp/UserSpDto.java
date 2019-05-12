package com.osce.dto.biz.training.structure.sp;

import com.osce.dto.user.login.RegisterDto;
import com.osce.entity.OrgUserSp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: UserSpDto
 * @Description: user sp信息
 * @Author yangtongbin
 * @Date 2019-05-12
 */
@Setter
@Getter
@ToString
public class UserSpDto implements Serializable {

    private static final long serialVersionUID = 1557302773987L;

    /**
     * sp信息
     */
    private List<OrgUserSp> userSpList;

    /**
     * 用户注册信息
     */
    private RegisterDto registerInfo;

}
