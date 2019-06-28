package com.osce.service.user.menu;

import com.osce.api.user.menu.PfMenuService;
import com.osce.dto.user.menu.MenuDto;
import com.osce.entity.SysFunction;
import com.osce.orm.user.menu.PfMenuDao;
import com.osce.vo.user.menu.PfBaseMenuVo;
import com.osce.vo.user.menu.PfMenuVo;
import com.osce.vo.user.menu.PfMenuZtreeVo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class PfMenuServiceImpl implements PfMenuService {

    @Resource
    private PfMenuDao pfMenuDao;

    @Override
    public List<SysFunction> listMenus(MenuDto dto) {
        return pfMenuDao.listMenus(dto);
    }

    @Override
    public Long countMenus(MenuDto dto) {
        return pfMenuDao.countMenus(dto);
    }

    @Override
    public boolean addMenu(SysFunction dto) {
        return pfMenuDao.addMenu(dto);
    }

    @Override
    public boolean isExistMenu(String code) {
        return pfMenuDao.isExistMenu(code);
    }

    @Override
    public boolean changeStatusMenu(List<Long> list, String status) {
        return pfMenuDao.changeStatusMenu(list, status) == 1 ? true : false;
    }

    @Override
    public boolean updateMenu(SysFunction dto) {
        return pfMenuDao.updateMenu(dto);
    }

    @Override
    public List<PfMenuZtreeVo> listMenuTree() {
        return pfMenuDao.listSysMenus();
    }

    @Override
    public List<PfMenuZtreeVo> listMenuRoleTree(Long roleId) {
        return pfMenuDao.listMenuRoleTree(roleId);
    }

    @Override
    public List<PfMenuVo> listMyMenus(boolean isSuper, boolean isAnonymousUser, Long userId) {
        List<SysFunction> menus;
        if (isAnonymousUser) {
            menus = pfMenuDao.listAnonymousUserMenus();
        } else {
            menus = isSuper ? pfMenuDao.listAllMenus() : pfMenuDao.listMyMenus(userId);
        }
        // 一级菜单
        List<PfMenuVo> parentMenuList = new ArrayList<>();
        PfMenuVo pfMenuVo;
        for (SysFunction sysMenu : menus) {
            pfMenuVo = new PfMenuVo();
            if (sysMenu.getLevel() == 1) {
                pfMenuVo.setParentMenuId(sysMenu.getId());
                pfMenuVo.setParentCode(sysMenu.getCode());
                pfMenuVo.setParentMenuName(sysMenu.getName());
                pfMenuVo.setParentImg(sysMenu.getIconSource());
                pfMenuVo.setParentUrl(sysMenu.getFunctionUrl());
                pfMenuVo.setPosition(sysMenu.getPosition());
                pfMenuVo.setTarget(sysMenu.getTarget());
                parentMenuList.add(pfMenuVo);
            }
        }

        // 二级菜单
        List<PfBaseMenuVo> groupList, thirdList;
        PfBaseMenuVo pfBaseMenuVo, thirdMenuVo;
        for (PfMenuVo parentMenuVo : parentMenuList) {
            groupList = new ArrayList<>();
            for (SysFunction sysMenu : menus) {
                if (sysMenu.getLevel() == 1) {
                    continue;
                }
                if (sysMenu.getParentCode().equals(parentMenuVo.getParentCode())) {
                    pfBaseMenuVo = new PfBaseMenuVo();
                    pfBaseMenuVo.setMenuId(sysMenu.getId());
                    pfBaseMenuVo.setCode(sysMenu.getCode());
                    pfBaseMenuVo.setName(sysMenu.getName());
                    pfBaseMenuVo.setUrl(sysMenu.getFunctionUrl());
                    pfBaseMenuVo.setImg(sysMenu.getIconSource());
                    pfBaseMenuVo.setTarget(sysMenu.getTarget());
                    // 三级菜单
                    thirdList = new ArrayList<>();
                    for (SysFunction thirdMenu : menus) {
                        if (thirdMenu.getLevel() != 3) {
                            continue;
                        }
                        if (thirdMenu.getParentCode().equals(pfBaseMenuVo.getCode())) {
                            thirdMenuVo = new PfBaseMenuVo();
                            thirdMenuVo.setMenuId(thirdMenu.getId());
                            thirdMenuVo.setCode(thirdMenu.getCode());
                            thirdMenuVo.setName(thirdMenu.getName());
                            thirdMenuVo.setUrl(thirdMenu.getFunctionUrl());
                            thirdMenuVo.setImg(thirdMenu.getIconSource());
                            thirdMenuVo.setTarget(thirdMenu.getTarget());
                            thirdList.add(thirdMenuVo);
                        }
                    }
                    pfBaseMenuVo.setChildren(thirdList);
                    groupList.add(pfBaseMenuVo);
                }
            }
            parentMenuVo.setGroupList(groupList);
        }

        return parentMenuList;
    }

}
