package com.osce.service.biz.mobile;

import com.osce.api.biz.mobile.PfMobileService;
import com.osce.dto.biz.execute.ExecAuthDto;
import com.osce.dto.biz.mobile.MobileDto;
import com.osce.exception.RestException;
import com.osce.orm.biz.execute.PfExecDao;
import com.osce.orm.biz.mobile.PfMobileDao;
import com.osce.vo.biz.mobile.MobileMainVo;
import com.osce.vo.biz.mobile.MobileQueueVo;
import com.osce.vo.biz.mobile.MobileStudentInfoVo;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

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

    @Override
    public MobileMainVo mobileMain(MobileDto dto) {
        return pfMobileDao.mobileMain(dto);
    }

    @Override
    public MobileStudentInfoVo selectCurrentStudentInfo(MobileDto dto) {
        return pfMobileDao.selectCurrentStudentInfo(dto);
    }

    @Override
    public MobileQueueVo listWaitingStudentInfo(MobileDto dto) {
        MobileQueueVo mobileQueueVo = new MobileQueueVo();
        mobileQueueVo.setWaitingNum(pfMobileDao.countWaitingStudent(dto));
        mobileQueueVo.setStudentQueues(pfMobileDao.listWaitingStudentInfo(dto));
        return mobileQueueVo;
    }

    @Override
    public boolean handleExamStatus(ExecAuthDto dto) {
        pfExecDao.execAuth(dto);
        if (dto.getParCode() != 0) {
            logger.error("调用存储过程[P_QUEUE_UPDATE]出错, param : {} ", dto.toString());
            throw new RestException(String.valueOf(dto.getParCode()), dto.getParMsg());
        }
        return true;
    }

}
