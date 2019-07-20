package com.osce.api.biz.execute;

import com.osce.dto.biz.execute.ExecAuthDto;
import com.osce.dto.biz.execute.ExecDto;
import com.osce.dto.biz.execute.TestDto;
import com.osce.entity.WeItem;
import com.osce.vo.biz.execute.ExecAuthVo;
import com.osce.vo.biz.execute.ExecTestVo;
import com.osce.vo.biz.execute.TestItemVo;
import com.osce.vo.biz.execute.TestStuVo;

import java.util.List;

/**
 * @ClassName: PfExecuteService
 * @Description: 执行接口
 * @Author yangtongbin
 * @Date 2019-07-17
 */
public interface PfExecuteService {

    /**
     * 理论考试首页 - 认证
     *
     * @param dto
     * @return
     */
    ExecAuthVo execAuth(ExecDto dto);

    /**
     * 执行列表
     *
     * @param dto
     * @return
     */
    List<ExecTestVo> listTested(ExecDto dto);

    /**
     * 开考
     *
     * @param dto
     * @return
     */
    boolean startTest(ExecAuthDto dto);

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
     * @return
     */
    List<TestItemVo> selectsItemInfo(TestDto dto);

    /**
     * 考试 - 单条保存
     *
     * @param dto
     * @return
     */
    Long saveItem(WeItem dto);

}
