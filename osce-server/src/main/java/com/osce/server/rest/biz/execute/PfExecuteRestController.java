package com.osce.server.rest.biz.execute;

import com.osce.api.biz.execute.PfExecuteService;
import com.osce.dto.biz.execute.ExecAuthDto;
import com.osce.dto.biz.execute.ExecDto;
import com.osce.dto.biz.execute.TestDto;
import com.osce.entity.WeItem;
import com.osce.vo.biz.execute.TestStuVo;
import com.sm.open.care.core.ResultObject;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: PfExecuteRestController
 * @Description: 理论考试
 * @Author yangtongbin
 * @Date 2019-07-16
 */
@RestController
public class PfExecuteRestController {

    @Reference
    private PfExecuteService pfExecuteService;

    /**
     * 理论考试首页 - 认证
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/exec/auth")
    public ResultObject execAuth(@RequestBody ExecDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdCard() != null, "idCard");
        Assert.isTrue(dto.getIdOrg() != null, "idOrg");
        Assert.isTrue(dto.getIdRoom() != null, "idRoom");
        return ResultObject.createSuccess("execAuth", ResultObject.DATA_TYPE_OBJECT,
                pfExecuteService.execAuth(dto));
    }

    /**
     * 理论考试首页 - 待考&已考
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/exec/list/test")
    public ResultObject listTested(@RequestBody ExecDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdOrg() != null, "idOrg");
        Assert.isTrue(dto.getIdRoom() != null, "idRoom");
        return ResultObject.createSuccess("listTested", ResultObject.DATA_TYPE_LIST,
                pfExecuteService.listTested(dto));
    }

    /**
     * 理论考试首页 - 开考
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/exec/start")
    public ResultObject startTest(@RequestBody ExecAuthDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getParIdExecQueue() != null, "parIdExecQueue");
        Assert.isTrue(dto.getParSdExecQueue() != null, "parSdExecQueue");
        return ResultObject.createSuccess("startTest", ResultObject.DATA_TYPE_OBJECT,
                pfExecuteService.startTest(dto));
    }

    /**
     * 考试 - 学员信息
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/exec/stu/info")
    public ResultObject selectsStuTestInfo(@RequestBody TestDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdExecQueue() != null, "idExecQueue");
        TestStuVo testStuVo = pfExecuteService.selectsStuTestInfo(dto);
        if (testStuVo != null) {
            testStuVo.setNowTime(DateUtil.getCurrentDateTime());
        }
        return ResultObject.createSuccess("selectsStuTestInfo", ResultObject.DATA_TYPE_OBJECT, testStuVo);
    }

    /**
     * 考试 - 题目信息
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/exec/item/info")
    public ResultObject selectsItemInfo(@RequestBody TestDto dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdExecQueue() != null, "idExecQueue");
        Assert.isTrue(dto.getIdPaper() != null, "idPaper");
        return ResultObject.createSuccess("selectsItemInfo", ResultObject.DATA_TYPE_LIST,
                pfExecuteService.selectsItemInfo(dto));
    }

    /**
     * 考试 - 单条保存
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/r/exec/item/save")
    public ResultObject saveItem(@RequestBody WeItem dto) {
        /* 参数校验 */
        Assert.isTrue(dto.getIdExec() != null, "idExec");
        Assert.isTrue(dto.getIdItem() != null, "idItem");
        Assert.isTrue(StringUtils.isNotBlank(dto.getCdIteStr()), "cdIteStr");
        return ResultObject.createSuccess("saveItem", ResultObject.DATA_TYPE_OBJECT,
                pfExecuteService.saveItem(dto));
    }

}
