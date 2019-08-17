package com.osce.server.rest.biz.mobile;

import com.osce.api.biz.mobile.PfMobileService;
import com.osce.dto.biz.execute.ExecAuthDto;
import com.osce.dto.biz.mobile.*;
import com.osce.entity.SysParam;
import com.osce.entity.WeEvaluate;
import com.osce.entity.WeEvaluateDetail;
import com.osce.enums.SysParamEnum;
import com.osce.exception.RestErrorCode;
import com.osce.exception.RestException;
import com.osce.server.utils.ParamUtil;
import com.sm.open.care.core.ErrorCode;
import com.sm.open.care.core.ResultObject;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.MediaType;
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
    @PostMapping(value = "/r/1000/101", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultObject mobileLogin(@RequestBody MobileLoginDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getAuthCode() != null, "authCode");
        // 授权码参数
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
    @PostMapping(value = "/r/2000/101", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultObject mobileMain(@RequestBody MobileDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        Assert.isTrue(dto.getIdArea() != null, "idArea");
        Assert.isTrue(dto.getTimeSection() > 0, "timeSection");
        Assert.isTrue(dto.getIdRoom() != null, "idRoom");

        return ResultObject.createSuccess("mobileMain", ResultObject.DATA_TYPE_OBJECT,
                pfMobileService.mobileMain(dto));
    }

    /**
     * 当前学员信息
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/2000/102", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultObject selectCurrentStudentInfo(@RequestBody MobileDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        Assert.isTrue(dto.getIdArea() != null, "idArea");
        Assert.isTrue(dto.getTimeSection() > 0, "timeSection");
        Assert.isTrue(dto.getIdRoom() != null, "idRoom");

        return ResultObject.createSuccess("selectCurrentStudentInfo", ResultObject.DATA_TYPE_OBJECT,
                pfMobileService.selectCurrentStudentInfo(dto));
    }


    /**
     * 待考学员信息
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/2000/103", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultObject listWaitingStudentInfo(@RequestBody MobileDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdPlan() != null, "idPlan");
        Assert.isTrue(dto.getIdArea() != null, "idArea");
        Assert.isTrue(dto.getTimeSection() > 0, "timeSection");
        Assert.isTrue(dto.getIdRoom() != null, "idRoom");

        return ResultObject.createSuccess("listWaitingStudentInfo", ResultObject.DATA_TYPE_LIST,
                pfMobileService.listWaitingStudentInfo(dto));
    }

    /**
     * 叫号、开考、缺考登记
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/2000/104", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultObject handleExamStatus(@RequestBody MobileExamStatusDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdExecQueue() != null, "idExecQueue");
        Assert.isTrue(dto.getSdExecQueue() != null, "sdExecQueue");
        if (dto.getSdExecQueue() != 2 && dto.getSdExecQueue() != 4 && dto.getSdExecQueue() != 6) {
            throw new BizRuntimeException(ErrorCode.ERROR_GENERAL_110001, "sdExecQueue不正确");
        }

        ExecAuthDto execAuthDto = new ExecAuthDto();
        execAuthDto.setParIdExecQueue(dto.getIdExecQueue());
        execAuthDto.setParSdExecQueue(dto.getSdExecQueue());

        return ResultObject.createSuccess("handleExamStatus", ResultObject.DATA_TYPE_OBJECT,
                pfMobileService.handleExamStatus(execAuthDto));
    }

    /**
     * 获取评分页面头部信息
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/3000/101", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultObject selectScoreHeader(@RequestBody MobileScoreDto dto) {
        /* 参数校验 */
        if (dto.getIdExecQueue() == null) {
            Assert.isTrue(dto.getIdPlan() != null, "idPlan");
            Assert.isTrue(dto.getIdArea() != null, "idArea");
            Assert.isTrue(dto.getTimeSection() > 0, "timeSection");
            Assert.isTrue(dto.getIdRoom() != null, "idRoom");
        }
        return ResultObject.createSuccess("selectScoreHeader", ResultObject.DATA_TYPE_OBJECT,
                pfMobileService.selectScoreHeader(dto));
    }

    /**
     * 第一个SHEET页,根据执行记录里的评分表ID，加载评分表，结果另存到评分明细
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/3000/102", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultObject listScoreSheet(@RequestBody MobileScoreDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdExec() != null, "idExec");
        return ResultObject.createSuccess("listScoreSheet", ResultObject.DATA_TYPE_LIST,
                pfMobileService.listScoreSheet(dto));
    }

    /**
     * 评分 -（第一个SHEET页）
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/3000/103", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultObject saveSheetScore(@RequestBody MobileScoreAddDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdExec() != null, "idExec");
        Assert.isTrue(dto.getIdScoreItem() != null, "idScoreItem");
        Assert.isTrue(dto.getScoreResult() != null, "scoreResult");
        Assert.isTrue(StringUtils.isNotBlank(dto.getCdAssistantCa()), "cdAssistantCa");
        if (!("1".equals(dto.getCdAssistantCa()) || "2".equals(dto.getCdAssistantCa())
                || "3".equals(dto.getCdAssistantCa()))) {
            throw new BizRuntimeException(ErrorCode.ERROR_GENERAL_110001, "cdAssistantCa不正确");
        }
        return ResultObject.createSuccess("saveSheetScore", ResultObject.DATA_TYPE_OBJECT,
                pfMobileService.saveSheetScore(dto));
    }

    /**
     * 临床技能操作评量 - 第二个SHEET页
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/3000/104", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultObject listEvaluate(@RequestBody MobileScoreDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdExec() != null, "idExec");
        return ResultObject.createSuccess("listEvaluate", ResultObject.DATA_TYPE_LIST,
                pfMobileService.listCobEvaluate(dto.getIdExec()));
    }

    /**
     * 保存临床技能操作评量 - 第二个SHEET页
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/3000/105", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultObject saveEvaluate(@RequestBody WeEvaluate dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdExec() != null, "idExec");
        Assert.isTrue(dto.getIdCobEvaluate() != null, "idCobEvaluate");
        Assert.isTrue(dto.getScore() != null, "score");

        return ResultObject.createSuccess("saveEvaluate", ResultObject.DATA_TYPE_LIST,
                pfMobileService.saveEvaluate(dto));
    }

    /**
     * 操作评量明细 - 第二个SHEET页
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/3000/106", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultObject listEvaluateDetail(@RequestBody MobileScoreDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdCobEvaluate() != null, "idCobEvaluate");
        Assert.isTrue(dto.getIdExec() != null, "idExec");
        return ResultObject.createSuccess("listEvaluate", ResultObject.DATA_TYPE_LIST,
                pfMobileService.listEvaluateDetail(dto.getIdExec(), dto.getIdCobEvaluate()));
    }

    /**
     * save操作评量 - 第二个SHEET页
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/3000/107", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultObject saveEvaluateDetail(@RequestBody WeEvaluateDetail dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdWeEvaluate() != null, "idWeEvaluate");
        Assert.isTrue(dto.getIdCobEvaluateDetail() != null, "idCobEvaluateDetail");
        Assert.isTrue(dto.getScore() != null, "score");
        return ResultObject.createSuccess("saveEvaluateDetail", ResultObject.DATA_TYPE_LIST,
                pfMobileService.saveEvaluateDetail(dto));
    }

    /**
     * 提交
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/3000/108", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultObject submit(@RequestBody MobileScoreDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdExecQueue() != null, "idExecQueue");
        ExecAuthDto execAuthDto = new ExecAuthDto();
        execAuthDto.setParIdExecQueue(dto.getIdExecQueue());
        execAuthDto.setParSdExecQueue(5);

        return ResultObject.createSuccess("submit", ResultObject.DATA_TYPE_OBJECT,
                pfMobileService.handleExamStatus(execAuthDto));
    }


}
