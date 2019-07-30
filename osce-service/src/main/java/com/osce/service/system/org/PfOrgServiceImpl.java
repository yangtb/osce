package com.osce.service.system.org;

import com.osce.api.system.org.PfOrgService;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.SysOrg;
import com.osce.orm.system.org.PfOrgDao;
import com.osce.vo.PfTreeSelectVo;
import com.osce.vo.system.org.PfOrgZtreeVo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PfOrgServiceImpl
 * @Description: 机构实现
 * @Author yangtongbin
 * @Date 2019-05-05
 */
@Component
@Service
public class PfOrgServiceImpl implements PfOrgService {

    @Resource
    private PfOrgDao pfOrgDao;

    @Override
    public List<PfOrgZtreeVo> selectOrgTree() {
        return pfOrgDao.selectOrgTree();
    }

    @Override
    public SysOrg selectOrgDetail(Long idOrg) {
        return pfOrgDao.selectOrgDetail(idOrg);
    }

    @Override
    public List<SysOrg> listAllOrg() {
        return pfOrgDao.listAllOrg();
    }

    @Override
    public Long addOrg(SysOrg dto) {
        if (dto.getIdOrg() == null) {
            pfOrgDao.addOrg(dto);
        } else {
            pfOrgDao.editOrg(dto);
        }
        return dto.getIdOrg();
    }

    @Override
    public boolean editOrg(SysOrg dto) {
        return pfOrgDao.editOrg(dto) == 1 ? true : false;
    }

    @Override
    public boolean delOrg(PfBachChangeStatusDto dto) {
        return pfOrgDao.delOrg(dto) >= 1 ? true : false;
    }

    @Override
    public SysOrg selectOrgInfoById(Long idOrg) {
        return pfOrgDao.selectOrgInfoById(idOrg);
    }

    @Override
    public List<PfTreeSelectVo> selectOrgTreeSelect() {
        return pfOrgDao.selectOrgTreeSelect();
    }

}
