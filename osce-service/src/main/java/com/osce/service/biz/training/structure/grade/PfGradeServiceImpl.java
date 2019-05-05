package com.osce.service.biz.training.structure.grade;

import com.osce.api.biz.training.structure.grade.PfGradeService;
import com.osce.dto.biz.training.structure.grade.GradeDto;
import com.osce.orm.biz.training.structure.grade.PfGradeDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

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
}
