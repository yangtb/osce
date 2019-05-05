package com.osce.server.rest.system.param;

import com.osce.api.system.param.PfParamService;
import com.osce.dto.system.param.PfParamListDto;
import com.osce.entity.SysParam;
import com.osce.server.security.CurrentUserUtils;
import com.osce.server.utils.ParamUtil;
import com.sm.open.care.core.ResultObject;
import com.sm.open.care.core.utils.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @ClassName: PfParamRestController
 * @Description: 参数管理
 * @Author yangtongbin
 * @Date 2017/10/9 11:05
 */
@Controller
@RequestMapping(value = "/pf/r/param")
public class PfParamRestController {

    @Reference
    private PfParamService pfParamService;

    @Resource
    private ParamUtil paramUtil;

    /**
     * 新增参数
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_PARAM_ADD','ROLE_SUPER')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject addParam(@RequestBody SysParam dto) {
        /* 参数校验 */
        Assert.isTrue(StringUtils.isNotBlank(dto.getParamCode()), "paramCode");
        Assert.isTrue(StringUtils.isNotBlank(dto.getParamName()), "paramName");
        Assert.isTrue(StringUtils.isNotBlank(dto.getSysType()), "sysType");
        Assert.isTrue(StringUtils.isNotBlank(dto.getBizModule()), "bizModule");
        Assert.isTrue(StringUtils.isNotBlank(dto.getDataType()), "dataType");
        Assert.isTrue(StringUtils.isNotBlank(dto.getDefaultValue()), "defaultValue");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.create("addParam", ResultObject.SUCCESS_CODE, ResultObject.MSG_SUCCESS,
                ResultObject.DATA_TYPE_OBJECT, pfParamService.addParam(dto));
    }

    /**
     * 编辑参数
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_PARAM_EDIT','ROLE_SUPER')")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject editParam(@RequestBody SysParam dto) {
        /* 参数校验 */
        Assert.isTrue(StringUtils.isNotBlank(dto.getParamCode()), "paramCode");
        Assert.isTrue(StringUtils.isNotBlank(dto.getParamName()), "paramName");
        Assert.isTrue(StringUtils.isNotBlank(dto.getSysType()), "sysType");
        Assert.isTrue(StringUtils.isNotBlank(dto.getBizModule()), "bizModule");
        Assert.isTrue(StringUtils.isNotBlank(dto.getDataType()), "dataType");
        Assert.isTrue(StringUtils.isNotBlank(dto.getDefaultValue()), "defaultValue");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.create("editParam", ResultObject.SUCCESS_CODE, ResultObject.MSG_SUCCESS,
                ResultObject.DATA_TYPE_OBJECT, pfParamService.editParam(dto));
    }

    /**
     * 停用、启用参数
     *
     * @param dto 参数id集合
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_PARAM_CHANGESTATUS','ROLE_SUPER')")
    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject changeStatus(@RequestBody PfParamListDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "入参不能为空");
        return ResultObject.create("changeStatus", ResultObject.SUCCESS_CODE, ResultObject.MSG_SUCCESS,
                ResultObject.DATA_TYPE_OBJECT, pfParamService.changeStatus(dto.getList(), dto.getStatus()));
    }

    /**
     * 刷新缓存
     *
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_PARAM_REFRESHCACHE','ROLE_SUPER')")
    @RequestMapping(value = "/refreshCache", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject refreshCache() {
        paramUtil.init();
        return ResultObject.createSuccess("refreshCache", ResultObject.DATA_TYPE_OBJECT, true);
    }

}
