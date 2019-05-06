package com.osce.service.biz.training.structure.grade;

import com.osce.api.biz.training.structure.grade.PfGradeService;
import com.osce.dto.biz.training.structure.grade.GradeDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.OrgGrade;
import com.osce.enums.OperationTypeEnum;
import com.osce.orm.biz.training.structure.grade.PfGradeDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import com.osce.vo.biz.training.structure.grade.GradeVo;
import com.sm.open.care.core.enums.YesOrNoNum;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PfGradeService
 * @Description: 学届管理
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@Service
public class PfGradeServiceImpl implements PfGradeService {

    @Resource
    private PfGradeDao pfGradeDao;

    @Override
    public PageResult pageGrades(GradeDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfGradeDao.countGrade(dto),
                pfGradeDao.listGrades(dto));
    }

    @Override
    public Long addGrade(OrgGrade dto) {
        if (dto.getIdGrade() == null) {
            pfGradeDao.addGrade(dto);
        } else {
            pfGradeDao.editGrade(dto);
        }
        return dto.getIdGrade();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delGrade(PfBachChangeStatusDto dto) {
        int num = pfGradeDao.delGrade(dto);
        if (dto.getOperationType().equals(OperationTypeEnum.UPDATE_STATUS.getCode())) {
            if (dto.getStatus().equals(YesOrNoNum.YES.getCode())) {
                // 更新其他状态
                pfGradeDao.updateOtherGrade(dto.getExtId(), dto.getList().get(0));
            }
        }
        return num >= 1 ? true : false;
    }

    @Override
    public List<GradeVo> listAllGrades(Long idOrg) {
        return pfGradeDao.listAllGrades(idOrg);
    }
}
