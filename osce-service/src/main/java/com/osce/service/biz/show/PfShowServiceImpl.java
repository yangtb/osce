package com.osce.service.biz.show;

import com.osce.api.biz.show.PfShowService;
import com.osce.dto.biz.show.PfAioStationDto;
import com.osce.dto.biz.show.PfAioStuRegisterDto;
import com.osce.dto.biz.show.ShowDto;
import com.osce.exception.RestErrorCode;
import com.osce.exception.RestException;
import com.osce.orm.biz.plan.template.PfTemplateDao;
import com.osce.orm.biz.show.PfShowDao;
import com.osce.orm.user.login.PfUserDao;
import com.osce.vo.biz.show.*;
import com.sm.open.care.core.utils.DateUtil;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: PfShowServiceImpl
 * @Description: 显示屏
 * @Author yangtongbin
 * @Date 2019-06-30
 */
@Service
public class PfShowServiceImpl implements PfShowService {

    private static final Logger logger = LoggerFactory.getLogger(PfShowServiceImpl.class);

    @Resource
    private PfTemplateDao pfTemplateDao;

    @Resource
    private PfShowDao pfShowDao;

    @Resource
    private PfUserDao pfUserDao;

    @Override
    public ShowBigScreenMainVo selectBigScreenMain(ShowDto dto) {
        ShowBigScreenMainVo showBigScreenMainVo = pfShowDao.selectBigScreenMain(dto);
        dto.setIdPlan(showBigScreenMainVo.getIdPlan());
        dto.setIdArea(showBigScreenMainVo.getIdArea());
        dto.setTimeSection(showBigScreenMainVo.getTimeSection());
        if (showBigScreenMainVo.getIdPlan() != null) {
            showBigScreenMainVo.setItemNum(pfShowDao.countBigScreenDetail(dto));
        } else {
            showBigScreenMainVo.setItemNum(0L);
        }
        return showBigScreenMainVo;
    }

    @Override
    public List<ShowBigScreenDetailVo> selectBigScreenDetail(ShowDto dto) {
        return pfShowDao.selectBigScreenDetail(dto);
    }

    @Override
    public ShowAioMainVo selectAioMain(ShowDto dto) {
        ShowBigScreenMainVo showBigScreenMainVo = pfShowDao.selectBigScreenMain(dto);
        ShowAioMainVo showAioMainVo = new ShowAioMainVo();
        BeanUtils.copyProperties(showBigScreenMainVo, showAioMainVo);
        return showAioMainVo;
    }

    @Override
    public List<ShowAioMainVo> listAioMain(ShowDto dto) {
        return pfShowDao.listAioMain(dto);
    }

    @Override
    public ShowStuVo aioStudentRegister(PfAioStuRegisterDto dto) {
        // 获取考试信息
        ShowStuVo showStuVo = pfUserDao.selectStudentByIdCard(dto.getParIdCard());
        if (showStuVo == null) {
            throw new RestException(RestErrorCode.STUDENT_NOT_EXIST);
        }
        // 查询入场序号
        dto.setUserId(showStuVo.getUserId());
        Integer noReg = pfShowDao.selectNoReg(dto);
        if (noReg == null) {
            pfShowDao.aioStudentRegister(dto);
            if (dto.getParCode() != 0) {
                logger.error("调用存储过程[P_WAITING_REG][待考登记]出错, param : {} ", dto.toString());
                throw new RestException(String.valueOf(dto.getParCode()), dto.getParMsg());
            }
        }
        showStuVo.setNoReg(noReg == null ? pfShowDao.selectNoReg(dto) : noReg);
        return showStuVo;
    }

    @Override
    public ShowStuVo countAioStuRegisterNum(ShowDto dto) {
        ShowStuVo showStuVo = new ShowStuVo();
        showStuVo.setRegisterNum(pfShowDao.countRegisterNum(dto));
        showStuVo.setStuTotalNum(0);
        return showStuVo;
    }

    @Override
    public List<ShowAioRegisteredVo> listAioRegistered(ShowDto dto) {
        return pfShowDao.listAioRegistered(dto);
    }

    @Override
    public List<ShowAioExecQueueVo> listAioExecQueue(ShowDto dto) {
        List<ShowAioExecQueueVo> execQueueVos = pfShowDao.listAioExecQueue(dto);
        for (ShowAioExecQueueVo showAioExecQueueVo : execQueueVos) {
            showAioExecQueueVo.setIdPaperText(pfTemplateDao.selectSkillName(showAioExecQueueVo.getSdSkillCa(), showAioExecQueueVo.getIdPaper()));
        }
        return execQueueVos;
    }

    @Override
    public ShowStationVo selectStationRoomInfo(PfAioStationDto dto) {
        ShowStationVo showStationVo = pfShowDao.selectStationRoomInfo(dto);
        return showStationVo;
    }

    @Override
    public ShowStationRightVo listRoomStudent(PfAioStationDto dto) {
        ShowStationRightVo showStationRightVo = new ShowStationRightVo();
        showStationRightVo.setStudentNum(pfShowDao.countRoomStudent(dto));
        showStationRightVo.setRoomStudents(pfShowDao.listRoomStudent(dto));
        showStationRightVo.setNowTime(DateUtil.getCurrentDateTime());
        return showStationRightVo;
    }

}
