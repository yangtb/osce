package com.osce.server.rest.biz.monitor;

import com.osce.api.biz.monitor.PfAreaMonitorService;
import com.osce.dto.biz.monitor.MonitorDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.SysParam;
import com.osce.enums.SysParamEnum;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import com.osce.server.utils.ParamUtil;
import com.osce.vo.biz.monitor.MonitorAreaDetailVo;
import com.sm.open.care.core.ErrorCode;
import com.sm.open.care.core.ErrorMessage;
import com.sm.open.care.core.ResultObject;
import com.sm.open.care.core.utils.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: PfMonitorRestController
 * @Description: 考场监控
 * @Author yangtongbin
 * @Date 2019-07-20
 */
@RestController
public class PfAreaMonitorRestController extends BaseController {

    @Resource
    private ParamUtil paramUtil;

    @Reference
    private PfAreaMonitorService pfAreaMonitorService;

    /**
     * 考场监控主页
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_05','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/monitor/area/list")
    public ResultObject listMonitorArea(@RequestBody MonitorDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("listMonitorArea", ResultObject.DATA_TYPE_LIST,
                pfAreaMonitorService.listMonitorArea(dto));
    }

    /**
     * 考场监控 - 站点详情
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_05','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/monitor/area/selectDetail")
    public ResultObject selectMonitorAreaDetail(@RequestBody MonitorDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdInsStation() != null, "idInsStation");
        MonitorAreaDetailVo monitorAreaDetailVo = pfAreaMonitorService.selectMonitorAreaDetail(dto);
        if (monitorAreaDetailVo != null) {
            // 二维码链接参数
            SysParam sysParam = paramUtil.getParamInfo(SysParamEnum.STATION_QR_CODE_URL.getCode());
            String qrCodeUrl = null;
            if (sysParam != null) {
                qrCodeUrl = sysParam.getParamValue();
                if (StringUtils.isBlank(qrCodeUrl)) {
                    qrCodeUrl = sysParam.getDefaultValue();
                }
            }
            if (StringUtils.isNotBlank(qrCodeUrl)) {
                qrCodeUrl += "?idPlan=" + monitorAreaDetailVo.getIdPlan()
                        + "&idArea=" + monitorAreaDetailVo.getIdArea()
                        + "&timeSection=" + monitorAreaDetailVo.getTimeSection()
                        + "&idRoom=" + monitorAreaDetailVo.getIdRoom();
            }
            monitorAreaDetailVo.setStationQrCodeUrl(qrCodeUrl);
        }

        return ResultObject.createSuccess("selectMonitorAreaDetail", ResultObject.DATA_TYPE_OBJECT, monitorAreaDetailVo);
    }

    /**
     * 删除学员
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_05','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/monitor/area/student/del")
    public ResultObject delAreaStu(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfAreaMonitorService.delAreaStu(dto) ? ResultObject.createSuccess("delAreaStu", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("delAreaStu", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 恢复考试
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_05','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/monitor/area/student/recovery")
    public ResultObject recoveryTest(@RequestBody PfBachChangeStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "list");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return pfAreaMonitorService.recoveryTest(dto) ? ResultObject.createSuccess("recoveryTest", ResultObject.DATA_TYPE_OBJECT, true)
                : ResultObject.create("recoveryTest", ErrorCode.ERROR_SYS_160002, ErrorMessage.MESSAGE_SYS_160002);
    }

    /**
     * 考场监控 - 监控设备列表
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_01_05','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/monitor/room/device/list")
    public ResultObject listRoomDevice(@RequestBody MonitorDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdInsStation() != null, "idInsStation");

        return ResultObject.createSuccess("selectMonitorAreaDetail", ResultObject.DATA_TYPE_LIST,
                pfAreaMonitorService.listRoomDevice(dto.getIdInsStation()));
    }

}
