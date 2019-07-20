package com.osce.service.biz.execute;

import com.osce.api.biz.execute.PfExecuteService;
import com.osce.dto.biz.execute.ExecAuthDto;
import com.osce.dto.biz.execute.ExecDto;
import com.osce.dto.biz.execute.TestDto;
import com.osce.dto.biz.show.ShowDto;
import com.osce.entity.WeItem;
import com.osce.exception.RestErrorCode;
import com.osce.exception.RestException;
import com.osce.orm.biz.execute.PfExecDao;
import com.osce.orm.biz.show.PfShowDao;
import com.osce.vo.biz.execute.*;
import com.osce.vo.biz.show.ShowBigScreenMainVo;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PfExecuteServiceImpl
 * @Description: 考试执行实现
 * @Author yangtongbin
 * @Date 2019-07-17
 */
@Service
public class PfExecuteServiceImpl implements PfExecuteService {

    private static final Logger logger = LoggerFactory.getLogger(PfExecuteServiceImpl.class);

    @Resource
    private PfShowDao pfShowDao;

    @Resource
    private PfExecDao pfExecDao;

    @Override
    public ExecAuthVo execAuth(ExecDto dto) {
        // 1.获取学员信息
        ExecAuthVo execAuthVo = pfExecDao.selectStuInfo(dto);
        if (execAuthVo == null) {
            throw new RestException(RestErrorCode.EXEC_AUTH_STU_NOT_EXIST);
        }
        // 2.认证
        ExecAuthDto execAuthDto = new ExecAuthDto();
        execAuthDto.setParIdExecQueue(execAuthVo.getIdExecQueue());
        execAuthDto.setParSdExecQueue(execAuthVo.getSdExecQueue());

        pfExecDao.execAuth(execAuthDto);
        if (execAuthDto.getParCode() != 0) {
            logger.error("调用存储过程[P_QUEUE_UPDATE]认证出错, param : {} ", execAuthDto.toString());
            throw new RestException(String.valueOf(execAuthDto.getParCode()), execAuthDto.getParMsg());
        }
        return pfExecDao.selectStuInfo(dto);
    }

    @Override
    public List<ExecTestVo> listTested(ExecDto dto) {
        List<ExecTestVo> execTestVos = new ArrayList<>();
        // 待考
        ShowDto showDto = new ShowDto();
        showDto.setIdOrg(dto.getIdOrg());
        ShowBigScreenMainVo showVo = pfShowDao.selectBigScreenMain(showDto);
        if (showVo != null && showVo.getIdArea() != null) {
            dto.setIdArea(showVo.getIdArea());
            dto.setTimeSection(showVo.getTimeSection());
            ExecTestVo execTestVo = pfExecDao.selectTest(dto);
            if (execTestVo != null) {
                execTestVos.add(execTestVo);
            }
        }
        // 已考
        execTestVos.addAll(pfExecDao.listTested(dto));
        return execTestVos;
    }

    @Override
    public boolean startTest(ExecAuthDto dto) {
        pfExecDao.execAuth(dto);
        if (dto.getParCode() != 0) {
            logger.error("调用存储过程[P_QUEUE_UPDATE]出错, param : {} ", dto.toString());
            throw new RestException(String.valueOf(dto.getParCode()), dto.getParMsg());
        }
        return true;
    }

    @Override
    public TestStuVo selectsStuTestInfo(TestDto dto) {
        TestStuVo testStuVo = pfExecDao.selectsStuTestInfo(dto);
        // 考试结束，返回结果字段
        if (testStuVo != null && testStuVo.getSdExecQueue() == 5) {
            TestResultVo testResultVo = pfExecDao.countTestResult(testStuVo.getIdPaper());
            if (testResultVo != null) {
                testStuVo.setRightNum(testResultVo.getRightNum());
                testStuVo.setErrorNum(testResultVo.getErrorNum());
                testStuVo.setNoDoneNum(testResultVo.getNoDoneNum());
                testStuVo.setTotalScore(testResultVo.getTotalScore());
            }
        }
        return testStuVo;
    }

    @Override
    public List<TestItemVo> selectsItemInfo(TestDto dto) {
        String sdExecQueue = pfExecDao.selectSdExecQueue(dto.getIdExecQueue());
        return pfExecDao.selectsItemInfo(dto, sdExecQueue);
    }

    @Override
    public Long saveItem(WeItem dto) {
        // 获取分值
        TestItemAnswerVo itemAnswerVo = pfExecDao.selectScore(dto.getIdItem());
        dto.setScore(itemAnswerVo.getScoreDefault());
        if ((dto.getCdIteStr() + ";").equals(itemAnswerVo.getCdIteStr())) {
            dto.setScoreResult(itemAnswerVo.getScoreDefault());
        } else {
            dto.setScoreResult(0);
        }
        if (dto.getIdWeItem() == null) {
            pfExecDao.saveItem(dto);
        } else {
            pfExecDao.updateWeItem(dto);
        }
        return dto.getIdWeItem();
    }

}
