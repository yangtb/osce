package com.osce.server.rest.biz.training.res.pick;

import com.osce.api.biz.training.res.pick.PfPickService;
import com.osce.dto.biz.training.res.pick.PfPickDeviceDto;
import com.osce.dto.biz.training.res.pick.PfTpPickCaseDto;
import com.osce.entity.TpPicking;
import com.osce.server.portal.BaseController;
import com.sm.open.care.core.ResultObject;
import com.sm.open.care.core.utils.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: PfPickController
 * @Description: 实训领料
 * @Author yangtongbin
 * @Date 2019-08-18
 */
@RestController
public class PfPickRestController extends BaseController {

    @Reference
    private PfPickService pfPickService;

    /**
     * 获取设备列表
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_02_003', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/pick/device/case/list")
    public ResultObject listDeviceCase(@RequestBody PfPickDeviceDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdDevice() != null, "idDevice");
        Assert.isTrue(dto.getIdTpPicking() != null, "idTpPicking");
        return ResultObject.createSuccess("listDeviceCase", ResultObject.DATA_TYPE_LIST,
                pfPickService.listDeviceCase(dto));
    }

    /**
     * save领料实例
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_02_003', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/pick/case/save")
    public ResultObject saveTpPickCase(@RequestBody PfTpPickCaseDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getIdDeviceCases()), "idDeviceCases");
        Assert.isTrue(dto.getIdTpPicking() != null, "idTpPicking");
        return ResultObject.createSuccess("saveTpPickCase", ResultObject.DATA_TYPE_LIST,
                pfPickService.saveTpPickCase(dto));
    }


    /**
     * 更新实领数量
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_02_003', 'ROLE_SUPER')")
    @PostMapping(value = "/pf/r/pick/edit/pickedNum")
    public ResultObject updateTpPick(@RequestBody TpPicking dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdTpPicking() != null, "idTpPicking");
        Assert.isTrue(dto.getFgPicked() != null, "fgPicked");
        return ResultObject.createSuccess("updateTpPick", ResultObject.DATA_TYPE_LIST,
                pfPickService.updateTpPick(dto));
    }


}
