package com.osce.server.rest.biz.mobile;

import com.osce.api.biz.mobile.PfMobileService;
import com.osce.dto.biz.mobile.MobileDto;
import com.osce.dto.biz.mobile.MobileLoginDto;
import com.osce.entity.SysParam;
import com.osce.enums.SysParamEnum;
import com.osce.exception.RestErrorCode;
import com.osce.exception.RestException;
import com.osce.server.utils.ParamUtil;
import com.sm.open.care.core.ResultObject;
import com.sm.open.care.core.utils.Assert;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: PfMobileRestController
 * @Description: 移动端
 * @Author yangtongbin
 * @Date 2019-07-08
 */
@RestController
public class PfMobileRestController {

    @Reference
    private PfMobileService pfMobileService;

    @Resource
    private ParamUtil paramUtil;

    /**
     * 简易授权接口
     * <pre>
     *      110001       未指定参数
     *      1000101001   授权码参数未配置
     *      1000101002   授权码不正确，请重新输入
     * </pre>
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/1000/101")
    public ResultObject mobileLogin(@RequestBody MobileLoginDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getAuthCode() != null, "authCode");
        // 二维码链接参数
        SysParam sysParam = paramUtil.getParamInfo(SysParamEnum.MOBILE_AUTH_CODE.getCode());
        String mobileAuthCode = null;
        if (sysParam != null) {
            mobileAuthCode = sysParam.getParamValue();
            if (StringUtils.isBlank(mobileAuthCode)) {
                mobileAuthCode = sysParam.getDefaultValue();
            }
        }
        if (StringUtils.isBlank(mobileAuthCode)) {
            throw new RestException(RestErrorCode.AUTH_CODE_NOT_SET);
        }
        if (!dto.getAuthCode().equals(mobileAuthCode)) {
            throw new RestException(RestErrorCode.AUTH_CODE_ERROR);
        }
        return ResultObject.createSuccess("mobileLogin", ResultObject.DATA_TYPE_OBJECT, true);
    }

    /**
     * 移动端 - 首页
     * <pre>
     *      110001       未指定参数
     * </pre>
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/2000/101")
    public ResultObject mobileMain(@RequestBody MobileDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        Assert.isTrue(dto.getIdArea() != null, "idArea");
        Assert.isTrue(dto.getTimeSection() > 0, "timeSection");
        Assert.isTrue(dto.getIdRoom() != null, "idRoom");

        return ResultObject.createSuccess("mobileMain", ResultObject.DATA_TYPE_LIST,
                pfMobileService.mobileMain(dto));
    }

}
