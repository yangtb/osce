package com.osce.server.rest.biz.mobile;

import com.osce.api.biz.mobile.PfMobileService;
import com.osce.dto.biz.show.ShowDto;
import com.osce.server.utils.ParamUtil;
import com.sm.open.care.core.ResultObject;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: PfShowRestController
 * @Description: 显示屏
 * @Author yangtongbin
 * @Date 2019-06-30
 */
@RestController
public class PfMobileRestController {

    @Reference
    private PfMobileService pfMobileService;

    @Resource
    private ParamUtil paramUtil;

    /**
     * 登陆
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_03_01','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/show/big/screen/main")
    public ResultObject login(@RequestBody ShowDto dto) {

        return ResultObject.createSuccess("selectBigScreenMain", ResultObject.DATA_TYPE_OBJECT,
                null);
    }


}
