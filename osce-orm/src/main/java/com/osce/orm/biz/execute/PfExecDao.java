package com.osce.orm.biz.execute;

import com.osce.dto.biz.execute.ExecAuthDto;
import com.osce.dto.biz.execute.ExecDto;
import com.osce.dto.biz.execute.TestDto;
import com.osce.entity.WeItem;
import com.osce.vo.biz.execute.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: PfExecDao
 * @Description: 考试执行
 * @Author yangtongbin
 * @Date 2019-07-17
 */
public interface PfExecDao {

    /**
     * 查询待认证学员信息
     *
     * @param dto
     * @return
     */
    ExecAuthVo selectStuInfo(ExecDto dto);

    /**
     * 认证
     *
     * @param dto
     */
    void execAuth(ExecAuthDto dto);

    /**
     * 待考
     *
     * @param dto
     * @return
     */
    ExecTestVo selectTest(ExecDto dto);

    /**
     * 已考
     *
     * @param dto
     * @return
     */
    List<ExecTestVo> listTested(ExecDto dto);

    /**
     * 考试 - 学员信息
     *
     * @param dto
     * @return
     */
    TestStuVo selectsStuTestInfo(TestDto dto);

    /**
     * 考试 - 题目信息
     *
     * @param dto
     * @param sdExecQueue
     * @return
     */
    List<TestItemVo> selectsItemInfo(@Param("dto") TestDto dto,
                                     @Param("sdExecQueue") String sdExecQueue);

    /**
     * 考试 - 单条保存
     *
     * @param dto
     * @return
     */
    int saveItem(WeItem dto);

    /**
     * 考试 - 单条保存
     *
     * @param dto
     * @return
     */
    int updateWeItem(WeItem dto);

    /**
     * 查询分值
     *
     * @param idItem 题目id
     * @return
     */
    TestItemAnswerVo selectScore(@Param("idItem") Long idItem);

    /**
     * 查询状态
     *
     * @param idExecQueue 执行id
     * @return
     */
    String selectSdExecQueue(@Param("idExecQueue") Long idExecQueue);

    /**
     * 结果统计
     *
     * @param idPaper 试卷id
     * @return
     */
    TestResultVo countTestResult(@Param("idPaper") Long idPaper);

}
