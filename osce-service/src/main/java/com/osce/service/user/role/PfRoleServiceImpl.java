package com.osce.service.user.role;

import com.osce.api.user.role.PfRoleService;
import com.osce.dto.user.role.PfRoleDto;
import com.osce.entity.SysRole;
import com.osce.entity.SysRoleMenu;
import com.osce.orm.user.role.PfRoleDao;
import com.osce.vo.user.role.PfRoleVo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PfRoleServiceImpl
 * @Description: 用户角色接口实现
 * @Author yangtongbin
 * @Date 2017/9/9 15:52
 */
@Component
@Service
public class PfRoleServiceImpl implements PfRoleService {

    @Resource
    private PfRoleDao pfRoleDao;

    @Override
    public List<PfRoleVo> listRoles(PfRoleDto dto) {
        return pfRoleDao.listRoles(dto);
    }

    @Override
    public List<PfRoleVo> list() {
        return pfRoleDao.list();
    }

    @Override
    public List<PfRoleVo> listUserRole(Long userId) {
        return pfRoleDao.listUserRole(userId);
    }

    @Override
    public Long countRoles(PfRoleDto dto) {
        return pfRoleDao.countRoles(dto);
    }

    @Override
    public boolean addRole(SysRole dto) {
        return pfRoleDao.addRole(dto) == 1 ? true : false;
    }

    @Override
    public boolean isExistRole(String roleName) {
        return pfRoleDao.isExistRole(roleName);
    }

    @Override
    public boolean editRole(SysRole dto) {
        return pfRoleDao.updateRole(dto) == 1 ? true : false;
    }

    @Override
    public boolean delRole(List<Long> roles) {
        return pfRoleDao.delRole(roles) == 1 ? true : false;
    }

    @Override
    public boolean cancelRole(List<SysRole> roles) {
        for (SysRole sysRole : roles) {
            pfRoleDao.cancelRole(sysRole.getState(), sysRole.getRoleId());
        }
        return true;
    }

    @Override
    public PfRoleVo selectRoleInfoByCode(String code) {
        return pfRoleDao.selectRoleInfoByCode(code);
    }

    @Override
    public PfRoleVo selectRoleLevel(Long userId) {
        return pfRoleDao.selectRoleLevel(userId);
    }

    @Override
    public boolean needExpireNotice(Long userId) {
        return pfRoleDao.needExpireNotice(userId);
    }

    @Override
    public List<String> selectUserRoleCode(Long userId) {
        return pfRoleDao.selectUserRoleCode(userId);
    }

    @Override
    public boolean delRoleMenu(Long roleId) {
        return pfRoleDao.delRoleMenu(roleId) >= 1 ? true : false;
    }

    @Override
    public boolean saveRoleMenu(List<SysRoleMenu> dto) {
        return pfRoleDao.saveRoleMenu(dto) >= 1 ? true : false;
    }

}
