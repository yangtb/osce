package com.osce.server.portal.system.org;

import com.osce.api.system.org.PfOrgService;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.dto.system.org.PfBachOrgDto;
import com.osce.entity.SysOrg;
import com.osce.entity.SysOrgReg;
import com.osce.server.security.CurrentUserUtils;
import com.sm.open.care.core.ErrorCode;
import com.sm.open.care.core.ErrorMessage;
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

/**
 * @ClassName: PfOrgRestController
 * @Description: 机构rest服务
 * @Author yangtongbin
 * @Date 2018/9/20 13:59
 */
@Controller
public class PfOrgController {

    @Reference
    private PfOrgService pfOrgService;


    @PreAuthorize("hasAnyRole('ROLE_ORG_MG','ROLE_SUPER')")
    @RequestMapping("/page")
    public String page() {
        return "pages/system/org/orgPage";
    }

}
