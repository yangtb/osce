package com.osce.service.biz.training.skill;

import com.osce.api.biz.training.skill.PfSkillService;
import com.osce.dto.biz.training.skill.SkillDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.CobSkillCase;
import com.osce.entity.CobSkillDevice;
import com.osce.orm.biz.training.skill.PfSkillDao;
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
public class PfSkillServiceImpl implements PfSkillService {

    @Resource
    private PfSkillDao pfSkillDao;

    @Override
    public PageResult pageSkill(SkillDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfSkillDao.countSkill(dto),
                pfSkillDao.listSkill(dto));
    }

    @Override
    public Long addSkill(CobSkillCase dto) {
        if (dto.getIdSkillCase() == null) {
            pfSkillDao.addSkill(dto);
        } else {
            pfSkillDao.editSkill(dto);
        }
        return dto.getIdSkillCase();
    }

    @Override
    public boolean delSkill(PfBachChangeStatusDto dto) {
        int num = pfSkillDao.delSkill(dto);
        return num >= 1 ? true : false;
    }

    @Override
    public PageResult pageSkillDevice(SkillDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfSkillDao.countSkillDevice(dto),
                pfSkillDao.listSkillDevice(dto));
    }

    @Override
    public Long addSkillDevice(CobSkillDevice dto) {
        if (dto.getIdSkillDevice() == null) {
            pfSkillDao.addSkillDevice(dto);
        } else {
            pfSkillDao.editSkillDevice(dto);
        }
        return dto.getIdSkillDevice();
    }

    @Override
    public boolean delSkillDevice(PfBachChangeStatusDto dto) {
        int num = pfSkillDao.delSkillDevice(dto);
        return num >= 1 ? true : false;
    }

}
