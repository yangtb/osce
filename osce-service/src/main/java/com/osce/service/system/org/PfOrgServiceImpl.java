package com.osce.service.system.org;

import com.osce.api.system.org.PfOrgService;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.dto.system.org.PfBachOrgDto;
import com.osce.dto.system.org.PfOrgAuthDto;
import com.osce.dto.system.org.PfOrgDto;
import com.osce.entity.SysOrg;
import com.osce.entity.SysOrgReg;
import com.osce.enums.SdRegEnum;
import com.osce.orm.system.org.PfOrgDao;
import com.osce.vo.system.org.SysOrgAuthVo;
import com.sm.open.care.core.enums.YesOrNoNum;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Service
public class PfOrgServiceImpl implements PfOrgService {

    @Resource
    private PfOrgDao pfOrgDao;

    @Override
    public Long countOrgs(PfOrgDto dto) {
        return pfOrgDao.countOrgs(dto);
    }

    @Override
    public List<SysOrg> listOrgs(PfOrgDto dto) {
        return pfOrgDao.listOrgs(dto);
    }

    @Override
    public Long countAuthOrg(PfOrgAuthDto dto) {
        return pfOrgDao.countAuthOrg(dto);
    }

    @Override
    public List<SysOrgAuthVo> listAuthOrg(PfOrgAuthDto dto) {
        return pfOrgDao.listAuthOrg(dto);
    }

    @Override
    public List<SysOrg> listAllOrg() {
        return pfOrgDao.listAllOrg();
    }

    @Override
    public Long addOrg(SysOrg dto) {
        if (pfOrgDao.countOrgByEmail(dto.getEmail()) >= 1) {
            //throw new BizRuntimeException(PfOrgConstant.ORG_EMAIL_ERROR, PfOrgConstant.ORG_EMAIL_ERROR_MSG);
        }
        return pfOrgDao.addOrg(dto) == 1 ? dto.getIdOrg() : null;
    }

    @Override
    public boolean isExistOrgEmail(String email) {
        return pfOrgDao.countOrgByEmail(email) >= 1 ? true : false;
    }

    @Override
    public boolean editOrg(SysOrg dto) {
        return pfOrgDao.editOrg(dto) == 1 ? true : false;
    }

    @Override
    public boolean delOrg(PfBachChangeStatusDto dto) {
        return pfOrgDao.delOrg(dto) >= 1 ? true : false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean authOrg(PfBachOrgDto dto) {
        // 修改机构认证表状态
        pfOrgDao.updateAuthRecord(dto.getIdRegList(), dto.getConfirmor(), dto.getOperator(), SdRegEnum.PASS.getCode());
        // 修改机构表状态
        pfOrgDao.authOrg(dto.getIdOrgList(), dto.getOperator(), YesOrNoNum.YES.getCode());
        return true;
    }

    @Override
    public boolean rejectOrg(PfBachOrgDto dto) {
        int num = pfOrgDao.updateAuthRecord(dto.getIdRegList(), dto.getConfirmor(),
                dto.getOperator(), SdRegEnum.REJECT.getCode());
        return num >= 1 ? true : false;
    }

    @Override
    public SysOrg selectOrgInfoById(Long idOrg) {
        return pfOrgDao.selectOrgInfoById(idOrg);
    }

    @Override
    public boolean activeOrg(SysOrgReg dto) {
        return pfOrgDao.addActiveOrg(dto);
    }

    @Override
    public boolean isExistApplyActiveRecord(Long idOrg) {
        return pfOrgDao.isExistApplyActiveRecord(idOrg) >= 1 ? true : false;
    }

}
