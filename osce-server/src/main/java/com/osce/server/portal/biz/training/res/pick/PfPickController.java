package com.osce.server.portal.biz.training.res.pick;

import com.osce.api.biz.training.res.pick.PfPickService;
import com.osce.dto.biz.training.res.pick.PfPickDto;
import com.osce.result.PageResult;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: PfPickController
 * @Description: 实训领料
 * @Author yangtongbin
 * @Date 2019-08-18
 */
@Controller
public class PfPickController extends BaseController {

    @Reference
    private PfPickService pfPickService;

    @PreAuthorize("hasAnyRole('ROLE_01_02_003','ROLE_SUPER')")
    @RequestMapping("/pf/p/pick/page")
    public String page() {
        return "pages/biz/training/res/pick/pickingPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_02_003','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/pick/list")
    @ResponseBody
    public PageResult pagePick(PfPickDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfPickService.pagePick(dto);
    }


}
