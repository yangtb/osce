package com.osce.service.biz.plan.template.skill;

import com.osce.api.biz.plan.template.skill.PfExamSkillService;
import com.osce.dto.biz.training.skill.SkillDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdSkillDevice;
import com.osce.orm.biz.plan.template.skill.PfExamSkillDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @ClassName: PfSkillServiceImpl
 * @Description: 技能病例库实现
 * @Author yangtongbin
 * @Date 2019-05-15
 */
@Service
public class PfExamSkillServiceImpl implements PfExamSkillService {

    @Resource
    private PfExamSkillDao pfExamSkillDao;


    @Override
    public PageResult pageSkillDevice(SkillDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfExamSkillDao.countSkillDevice(dto),
                pfExamSkillDao.listSkillDevice(dto));
    }

    @Override
    public Long addSkillDevice(TdSkillDevice dto) {
        if (dto.getIdSkillDevice() == null) {
            pfExamSkillDao.addSkillDevice(dto);
        } else {
            pfExamSkillDao.editSkillDevice(dto);
        }
        return dto.getIdSkillDevice();
    }

    @Override
    public boolean delSkillDevice(PfBachChangeStatusDto dto) {
        int num = pfExamSkillDao.delSkillDevice(dto);
        return num >= 1 ? true : false;
    }

}
