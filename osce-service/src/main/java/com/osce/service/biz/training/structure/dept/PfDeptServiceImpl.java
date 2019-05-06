package com.osce.service.biz.training.structure.dept;

import com.osce.api.biz.training.structure.dept.PfDeptService;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.OrgDepart;
import com.osce.orm.biz.training.structure.dept.PfDeptDao;
import com.osce.vo.biz.training.structure.dept.PfDeptZtreeVo;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: PfDeptServiceImpl
 * @Description: 部门服务实现
 * @Author yangtongbin
 * @Date 2019-05-06
 */
@Service
public class PfDeptServiceImpl implements PfDeptService {

    @Resource
    private PfDeptDao pfDeptDao;

    @Override
    public List<PfDeptZtreeVo> selectDeptTree() {
        return pfDeptDao.selectDeptTree();
    }

    @Override
    public OrgDepart selectDeptDetail(Long idDepart) {
        return pfDeptDao.selectDeptDetail(idDepart);
    }

    @Override
    public List<OrgDepart> listAllDept() {
        return pfDeptDao.listAllDept();
    }

    @Override
    public Long saveDept(OrgDepart dto) {
        if (dto.getIdDepart() == null) {
            pfDeptDao.addDept(dto);
        } else {
            dto.setGmtModify(new Date());
            pfDeptDao.editDept(dto);
        }
        return dto.getIdDepart();
    }

    @Override
    public boolean delDept(PfBachChangeStatusDto dto) {
        return pfDeptDao.delDept(dto) >= 1 ? true : false;
    }

}
