package com.osce.service.biz.training.structure.dept;

import com.osce.api.biz.training.structure.dept.PfDeptService;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.OrgDepart;
import com.osce.exception.RestErrorCode;
import com.osce.exception.RestException;
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
    public List<PfDeptZtreeVo> selectDeptTree(Long idOrg, Long idGrade) {
        return pfDeptDao.selectDeptTree(idOrg, idGrade);
    }

    @Override
    public OrgDepart selectDeptDetail(Long idDepart) {
        return pfDeptDao.selectDeptDetail(idDepart);
    }

    @Override
    public List<OrgDepart> listAllDept(Long idOrg) {
        return pfDeptDao.listAllDept(idOrg);
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
        //部门下如果有学员，则不允许删除
        if (pfDeptDao.countDeptByIds(dto) > 0) {
           throw new RestException(RestErrorCode.DEPT_DEL_LIMIT);
        }
        return pfDeptDao.delDept(dto) >= 1 ? true : false;
    }

}
