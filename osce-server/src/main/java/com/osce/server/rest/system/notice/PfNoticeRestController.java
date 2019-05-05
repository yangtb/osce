package com.osce.server.rest.system.notice;

import com.osce.api.system.notice.PfNoticeService;
import com.osce.dto.common.PfCommonListDto;
import com.osce.entity.SysNotice;
import com.osce.server.security.CurrentUserUtils;
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
 * @ClassName: PfNoticeRestController
 * @Description: 公告模块
 * @Author yangtongbin
 * @Date 2017/10/3 21:43
 */
@Controller
@RequestMapping(value = "/pf/r/notice")
public class PfNoticeRestController {

    @Reference
    private PfNoticeService pfNoticeService;

    /**
     * 新增公告
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_NOTICE_ADD','ROLE_SUPER')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject addNotice(@RequestBody SysNotice dto) {
        /* 参数校验 */
        Assert.isTrue(StringUtils.isNotBlank(dto.getNoticeTitle()), "noticeTitle");
        Assert.isTrue(StringUtils.isNotBlank(dto.getNoticeType()), "noticeType");
        Assert.isTrue(StringUtils.isNotBlank(dto.getNoticeContent()), "noticeContent");
        dto.setOperator(CurrentUserUtils.getCurrentUsername());
        return ResultObject.createSuccess("addNotice", ResultObject.DATA_TYPE_OBJECT, pfNoticeService.addNotice(dto));
    }

    /**
     * 编辑公告
     *
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_NOTICE_EDIT','ROLE_SUPER')")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject editNotice(@RequestBody SysNotice dto) {
        /* 参数校验 */
        Assert.isTrue(StringUtils.isNotBlank(dto.getNoticeTitle()), "noticeTitle");
        Assert.isTrue(StringUtils.isNotBlank(dto.getNoticeType()), "noticeType");
        Assert.isTrue(StringUtils.isNotBlank(dto.getNoticeContent()), "noticeContent");
        return ResultObject.createSuccess("editNotice", ResultObject.DATA_TYPE_OBJECT, pfNoticeService.editNotice(dto));
    }

    /**
     * 删除公告
     *
     * @param dto 公告id集合
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_NOTICE_DEL','ROLE_SUPER')")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject delNotice(@RequestBody PfCommonListDto dto) {
        /* 参数校验 */
        Assert.isTrue(CollectionUtils.isNotEmpty(dto.getList()), "入参不能为空");
        return ResultObject.createSuccess("delNotice",
                ResultObject.DATA_TYPE_OBJECT, pfNoticeService.delNotice(dto.getList()));
    }


}
