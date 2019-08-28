package com.osce.service.mobile;

import com.osce.api.mobile.PfMobileService;
import com.osce.dto.biz.execute.ExecAuthDto;
import com.osce.dto.biz.mobile.MobileDto;
import com.osce.dto.biz.mobile.MobileScoreAddDto;
import com.osce.dto.biz.mobile.MobileScoreDto;
import com.osce.entity.WeEvaluate;
import com.osce.entity.WeEvaluateDetail;
import com.osce.entity.WeScore;
import com.osce.exception.RestErrorCode;
import com.osce.exception.RestException;
import com.osce.orm.biz.execute.PfExecDao;
import com.osce.orm.biz.mobile.PfMobileDao;
import com.osce.orm.biz.plan.template.PfTemplateDao;
import com.osce.vo.biz.mobile.MobileMainVo;
import com.osce.vo.biz.mobile.MobileQueueVo;
import com.osce.vo.biz.mobile.MobileStudentInfoVo;
import com.osce.vo.biz.mobile.score.*;
import com.sm.open.care.core.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: PfMobileServiceImpl
 * @Description: 移动端接口实现
 * @Author yangtongbin
 * @Date 2019-07-07
 */
@Service
public class PfMobileServiceImpl implements PfMobileService {

    private static final Logger logger = LoggerFactory.getLogger(PfMobileServiceImpl.class);

    @Resource
    private PfExecDao pfExecDao;

    @Resource
    private PfMobileDao pfMobileDao;

    @Resource
    private PfTemplateDao pfTemplateDao;

    @Override
    public MobileMainVo mobileMain(MobileDto dto) {
        logger.info("[移动端]首页, param:{}", dto.toString());
        return pfMobileDao.mobileMain(dto);
    }

    @Override
    public MobileStudentInfoVo selectCurrentStudentInfo(MobileDto dto) {
        logger.info("[移动端]当前学员信息, param:{}", dto.toString());
        return pfMobileDao.selectCurrentStudentInfo(dto);
    }

    @Override
    public MobileQueueVo listWaitingStudentInfo(MobileDto dto) {
        logger.info("[移动端]待考学员信息, param:{}", dto.toString());
        MobileQueueVo mobileQueueVo = new MobileQueueVo();
        mobileQueueVo.setWaitingNum(pfMobileDao.countWaitingStudent(dto));
        mobileQueueVo.setStudentQueues(pfMobileDao.listWaitingStudentInfo(dto));
        return mobileQueueVo;
    }

    @Override
    public boolean handleExamStatus(ExecAuthDto dto) {
        logger.info("[移动端]叫号、开考、缺考登记, param:{}", dto.toString());
        pfExecDao.execAuth(dto);
        if (dto.getParCode() != 0) {
            logger.error("调用存储过程[P_QUEUE_UPDATE]出错, param : {} ", dto.toString());
            throw new RestException(String.valueOf(dto.getParCode()), dto.getParMsg());
        }
        return true;
    }

    @Override
    public MobileScoreHeaderVo selectScoreHeader(MobileScoreDto dto) {
        logger.info("[移动端]获取评分页面头部信息, param: {}", dto.toString());
        if (dto.getIdExecQueue() == null) {
            MobileStudentInfoVo mobileStudentInfoVo = pfMobileDao.selectCurrentStudentInfo(dto);
            if (mobileStudentInfoVo != null) {
                dto.setIdExecQueue(mobileStudentInfoVo.getIdExecQueue());
            }
        }
        if (dto.getIdExecQueue() == null) {
            throw new RestException(RestErrorCode.MOBILE_SCORE_EXEC_QUEUE_NOT_EXIST);
        }
        MobileScoreHeaderVo mobileScoreHeaderVo = pfMobileDao.selectScoreHeader(dto.getIdExecQueue());
        if (mobileScoreHeaderVo != null) {
            mobileScoreHeaderVo.setNaPaper(pfTemplateDao.selectSkillName(mobileScoreHeaderVo.getSdSkillCa(),
                    mobileScoreHeaderVo.getIdPaper()));
            // 计算倒计时
            if (StringUtils.isNotBlank(mobileScoreHeaderVo.getActBegin())) {
                String actBegin = DateUtil.getDate(new Date()) + " " + mobileScoreHeaderVo.getActBegin();
                Long countdownSecond = DateUtil.subDateSecond(actBegin, DateUtil.getCurrentDateTime()) / 1000;
                mobileScoreHeaderVo.setCountdownSecond(countdownSecond > 0 ? countdownSecond : 0);
            }
        }
        return mobileScoreHeaderVo;
    }

    @Override
    public List<MobileScoreSheetVo> listScoreSheet(MobileScoreDto dto) {
        logger.info("[移动端]主考官评分表（第一个sheet页）, param: {}", dto.toString());
        return pfMobileDao.listScoreSheet(dto);
    }

    @Override
    public Long saveSheetScore(MobileScoreAddDto dto) {
        logger.info("[移动端]评分（第一个sheet页）, param: {}", dto.toString());
        MobileExecVo mobileExecVo = pfMobileDao.selectExecInfo(dto.getIdExec(), dto.getCdAssistantCa());
        WeScore weScore = new WeScore();
        weScore.setIdWeScore(dto.getIdWeScore());
        weScore.setIdExec(dto.getIdExec());
        weScore.setCdAssistantCa(dto.getCdAssistantCa());
        weScore.setIdUser(mobileExecVo.getIdUser());
        weScore.setIdScoreSheet(mobileExecVo.getIdScoreSheet());
        weScore.setIdScoreItem(dto.getIdScoreItem());
        weScore.setScoreResult(dto.getScoreResult());

        if (dto.getIdWeScore() == null) {
            pfMobileDao.saveSheetScore(weScore);
        } else {
            pfMobileDao.editSheetScore(weScore);
        }
        return weScore.getIdWeScore();
    }

    @Override
    public List<MobileEvaluateVo> listCobEvaluate(Long idExec) {
        logger.info("[移动端]查询评量表, idExec: {}", idExec);
        String sdSkillCa = pfMobileDao.selectSdSkillCa(idExec);
        if (StringUtils.isBlank(sdSkillCa)) {
            throw new RestException(RestErrorCode.MOBILE_SCORE_EXEC_QUEUE_NOT_EXIST);

        }
        String cdCobEvaluate = "2".equals(sdSkillCa) ? "1" : "2";
        return pfMobileDao.listCobEvaluate(idExec, cdCobEvaluate);
    }

    @Override
    public List<MobileEvaluateDetail> listEvaluateDetail(Long idExec, Long idCobEvaluate) {
        logger.info("[移动端]查询评量表明细, idExec: {}, idCobEvaluate: {}", idExec, idCobEvaluate);
        return pfMobileDao.listEvaluateDetail(idExec, idCobEvaluate);
    }

    @Override
    public Long saveEvaluate(WeEvaluate dto) {
        logger.info("[移动端]保存评量表, param: {}", dto.toString());
        if (dto.getIdWeEvaluate() == null) {
            pfMobileDao.addEvaluate(dto);
        } else {
            pfMobileDao.editEvaluate(dto);
        }
        return dto.getIdWeEvaluate();
    }

    @Override
    public Long saveEvaluateDetail(WeEvaluateDetail dto) {
        logger.info("[移动端]保存评量表明细, param: {}", dto.toString());
        if (dto.getIdWeEvaluateDetail() == null) {
            pfMobileDao.addEvaluateDetail(dto);
        } else {
            pfMobileDao.editEvaluateDetail(dto);
        }
        return dto.getIdWeEvaluate();
    }


}