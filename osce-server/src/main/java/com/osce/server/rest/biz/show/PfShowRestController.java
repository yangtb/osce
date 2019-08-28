package com.osce.server.rest.biz.show;

import com.osce.api.biz.show.PfShowService;
import com.osce.dto.biz.show.PfAioStationDto;
import com.osce.dto.biz.show.PfAioStuRegisterDto;
import com.osce.dto.biz.show.ShowDto;
import com.osce.entity.SysParam;
import com.osce.enums.SysParamEnum;
import com.osce.exception.RestException;
import com.osce.param.PageParam;
import com.osce.server.security.CurrentUserUtils;
import com.osce.server.utils.ParamUtil;
import com.osce.vo.biz.show.ShowBigScreenMainVo;
import com.osce.vo.biz.show.ShowStationVo;
import com.sm.open.care.core.ErrorCode;
import com.sm.open.care.core.ResultObject;
import com.sm.open.care.core.utils.Assert;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: PfShowRestController
 * @Description: 显示屏
 * @Author yangtongbin
 * @Date 2019-06-30
 */
@RestController
public class PfShowRestController {

    @Reference
    private PfShowService pfShowService;

    @Resource
    private ParamUtil paramUtil;

    /**
     * 查询机构下，当天的考试计划
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_03_01','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/show/big/screen/main")
    public ResultObject selectBigScreenMain(@RequestBody ShowDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        SysParam sysParam = paramUtil.getParamInfo(SysParamEnum.BIG_SCREEN_NUM.getCode());
        Integer bigScreenNum = 10;
        if (sysParam != null) {
            try {
                if (StringUtils.isNotBlank(sysParam.getParamValue())) {
                    bigScreenNum = Integer.parseInt(sysParam.getParamValue());
                }
                if (bigScreenNum == null) {
                    bigScreenNum = Integer.parseInt(sysParam.getDefaultValue());
                }
            } catch (NumberFormatException e) {
                throw new RestException(ErrorCode.ERROR_GENERAL_110001, "参数配置有误：待考区大屏显示条数必须是数字");
            }
        }
        ShowBigScreenMainVo showBigScreenMainVo = pfShowService.selectBigScreenMain(dto);
        showBigScreenMainVo.setLimit(bigScreenNum);
        showBigScreenMainVo.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("selectBigScreenMain", ResultObject.DATA_TYPE_OBJECT, showBigScreenMainVo);
    }

    /**
     * 查询计划、考场、时段下的学员当前状态清单:
     * <p>
     * 用处：该视图用于待考区（大屏幕）的表单
     * <p>
     * 频率：5秒刷新一次滚动显示，每页显示10行
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_03_01','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/show/big/screen/detail")
    public ResultObject selectBigScreenDetail(@RequestBody ShowDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        PageParam.initPageDto(dto);
        return ResultObject.createSuccess("selectBigScreenDetail", ResultObject.DATA_TYPE_LIST,
                pfShowService.selectBigScreenDetail(dto));
    }


    /**
     * 待考区一体机首页
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_03_01','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/aio/main")
    public ResultObject selectAioMain(@RequestBody ShowDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("selectAioMain", ResultObject.DATA_TYPE_OBJECT,
                pfShowService.selectAioMain(dto));
    }

    /**
     * 待考区一体机首页 - 列表
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_03_01','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/aio/main/list")
    public ResultObject listAioMain(@RequestBody ShowDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("listAioMain", ResultObject.DATA_TYPE_LIST,
                pfShowService.listAioMain(dto));
    }

    /**
     * 待考登记
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_03_01','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/aio/student/register")
    public ResultObject aioStudentRegister(@RequestBody PfAioStuRegisterDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        Assert.isTrue(dto.getParIdPlan() != null, "parIdPlan");
        Assert.isTrue(dto.getParIdArea() != null, "parIdArea");
        Assert.isTrue(dto.getParTimeSection() >= 0, "parTimeSection");
        Assert.isTrue(StringUtils.isNotBlank(dto.getParIdCard()), "parIdCard");
        return ResultObject.createSuccess("aioStudentRegister", ResultObject.DATA_TYPE_OBJECT,
                pfShowService.aioStudentRegister(dto));
    }

    /**
     * 学员登记数目
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_03_01','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/aio/student/register/num")
    @ResponseBody
    public ResultObject countAioStuRegisterNum(@RequestBody ShowDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        Assert.isTrue(dto.getIdArea() != null, "idArea");
        Assert.isTrue(dto.getTimeSection() >= 0, "timeSection");
        return ResultObject.createSuccess("countAioStuRegisterNum", ResultObject.DATA_TYPE_OBJECT,
                pfShowService.countAioStuRegisterNum(dto));
    }

    /**
     * 学员待考队列
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_03_01','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/aio/student/exec/queue")
    @ResponseBody
    public ResultObject listAioExecQueue(@RequestBody ShowDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        Assert.isTrue(dto.getIdArea() != null, "idArea");
        Assert.isTrue(dto.getTimeSection() >= 0, "timeSection");
        Assert.isTrue(dto.getIdUserStudent() != null, "idUserStudent");
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return ResultObject.createSuccess("listAioExecQueue", ResultObject.DATA_TYPE_LIST,
                pfShowService.listAioExecQueue(dto));
    }

    /**
     * 查询房间的考试信息
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_03_01','ROLE_SUPER')")
    @PostMapping(value = "/pf/r/aio/station/room/info")
    public ResultObject selectStationRoomInfo(@RequestBody PfAioStationDto dto) {
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        Assert.isTrue(dto.getIdArea() != null, "idArea");
        Assert.isTrue(dto.getTimeSection() >= 0, "timeSection");
        Assert.isTrue(dto.getIdRoom() != null, "idRoom");
        ShowStationVo showStationVo = pfShowService.selectStationRoomInfo(dto);
        if (showStationVo != null) {
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
                qrCodeUrl += "?id_plan=" + dto.getIdPlan()
                        + "&id_area=" + dto.getIdArea()
                        + "&time_section=" + dto.getTimeSection()
                        + "&id_room=" + dto.getIdRoom();
            }
            showStationVo.setQrCodeUrl(qrCodeUrl);
        }
        return ResultObject.createSuccess("selectStationRoomInfo", ResultObject.DATA_TYPE_OBJECT, showStationVo);
    }

    /**
     * 学员列表 - 房间
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_03_01','ROLE_SUPER')")
    @RequestMapping(value = "/pf/r/aio/station/room/student/list")
    @ResponseBody
    public ResultObject listRoomStudent(@RequestBody PfAioStationDto dto) {
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        Assert.isTrue(dto.getIdArea() != null, "idArea");
        Assert.isTrue(dto.getTimeSection() >= 0, "timeSection");
        Assert.isTrue(dto.getIdRoom() != null, "idRoom");
        SysParam sysParam = paramUtil.getParamInfo(SysParamEnum.ROOM_SHOW_NUM.getCode());
        Integer roomShowNum = 10;
        if (sysParam != null) {
            try {
                if (StringUtils.isNotBlank(sysParam.getParamValue())) {
                    roomShowNum = Integer.parseInt(sysParam.getParamValue());
                }
                if (roomShowNum == null) {
                    roomShowNum = Integer.parseInt(sysParam.getDefaultValue());
                }
            } catch (NumberFormatException e) {
                throw new RestException(ErrorCode.ERROR_GENERAL_110001, "参数配置有误：考站等待学员展示条数必须是数字");
            }
        }
        dto.setLimit(roomShowNum);
        return ResultObject.createSuccess("listRoomStudent", ResultObject.DATA_TYPE_OBJECT,
                pfShowService.listRoomStudent(dto));
    }

}
