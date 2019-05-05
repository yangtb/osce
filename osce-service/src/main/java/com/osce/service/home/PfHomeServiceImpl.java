package com.osce.service.home;

import com.osce.api.home.PfHomeService;
import com.osce.api.system.org.PfOrgService;
import com.osce.api.user.menu.PfMenuService;
import com.osce.api.user.role.PfRoleService;
import com.osce.dto.home.PfHomeDto;
import com.osce.entity.SysOrg;
import com.osce.vo.home.PfHomeVo;
import com.osce.vo.user.menu.PfMenuVo;
import com.sm.open.care.core.enums.YesOrNoNum;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PfHomeServiceImpl implements PfHomeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfHomeServiceImpl.class);

    @Resource
    private PfMenuService pfMenuService;

    @Resource
    private PfOrgService pfOrgService;

    @Resource
    private PfRoleService pfRoleService;

    @Override
    public PfHomeVo selectHomeInfo(PfHomeDto dto) {
        PfHomeVo result = new PfHomeVo();
        result.setAnonymousUser(dto.isAnonymousUser());
        // 菜单
        List<PfMenuVo> menuVos = pfMenuService.listMyMenus(dto.isSuper(), dto.isAnonymousUser(), dto.getUserId());
        Map<String, List<PfMenuVo>> menus = menuVos.stream().collect(Collectors.groupingBy(PfMenuVo::getPosition));
        result.setTopMenus(menus.get("top"));
        result.setLeftMenus(menus.get("left"));

        // 机构
        if (dto.getIdOrg() != null) {
            SysOrg sysOrg = pfOrgService.selectOrgInfoById(dto.getIdOrg());
            result.setSysOrg(sysOrg);
            if (sysOrg.getGmtValid() != null) {
                result.setExpireNotice(pfRoleService.needExpireNotice(dto.getUserId()) ?
                        YesOrNoNum.YES.getCode() : YesOrNoNum.NO.getCode());
            }
        }
        return result;
    }
}
