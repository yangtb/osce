package com.osce.server.rest.biz.show;

import com.osce.api.biz.show.PfShowService;
import com.osce.dto.biz.show.ShowDto;
import com.osce.entity.SysParam;
import com.osce.enums.SysParamEnum;
import com.osce.param.PageParam;
import com.osce.server.security.CurrentUserUtils;
import com.osce.server.utils.ParamUtil;
import com.osce.vo.biz.show.ShowBigScreenMainVo;
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
        Integer bigScreenNum = sysParam != null ? Integer.parseInt(sysParam.getParamValue()) : 10;

        ShowBigScreenMainVo showBigScreenMainVo = pfShowService.selectBigScreenMain(dto);
        showBigScreenMainVo.setLimit(bigScreenNum);
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
        return ResultObject.createSuccess("selectBigScreenDetail", ResultObject.DATA_TYPE_OBJECT,
                pfShowService.selectBigScreenDetail(dto));
    }

}
