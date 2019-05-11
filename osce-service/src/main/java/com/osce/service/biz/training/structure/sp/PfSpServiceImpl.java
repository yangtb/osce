package com.osce.service.biz.training.structure.sp;

import com.osce.api.biz.training.structure.sp.PfSpService;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.OrgSpDefine;
import com.osce.orm.biz.training.structure.sp.PfSpDao;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PfSpServiceImpl
 * @Description: Sp接口实现
 * @Author yangtongbin
 * @Date 2019-05-10
 */
@Service
public class PfSpServiceImpl implements PfSpService {

    @Resource
    private PfSpDao pfSpDao;

    @Override
    public List<OrgSpDefine> listSpTag(Long idOrg) {
        return pfSpDao.listSpTag(idOrg);
    }

    @Override
    public Long addSpTag(OrgSpDefine dto) {
        return pfSpDao.addSpTag(dto) == 1 ? dto.getIdSpTag() : null;
    }

    @Override
    public boolean delSpTag(PfBachChangeStatusDto dto) {
        return pfSpDao.delSpTag(dto) >= 1 ? true : false;
    }

}
