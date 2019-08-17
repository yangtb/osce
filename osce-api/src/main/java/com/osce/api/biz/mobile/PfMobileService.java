package com.osce.api.biz.mobile;

import com.osce.dto.biz.execute.ExecAuthDto;
import com.osce.dto.biz.mobile.MobileDto;
import com.osce.dto.biz.mobile.MobileScoreAddDto;
import com.osce.dto.biz.mobile.MobileScoreDto;
import com.osce.entity.WeEvaluate;
import com.osce.entity.WeEvaluateDetail;
import com.osce.vo.biz.mobile.MobileMainVo;
import com.osce.vo.biz.mobile.MobileQueueVo;
import com.osce.vo.biz.mobile.MobileStudentInfoVo;
import com.osce.vo.biz.mobile.score.MobileEvaluateDetail;
import com.osce.vo.biz.mobile.score.MobileEvaluateVo;
import com.osce.vo.biz.mobile.score.MobileScoreHeaderVo;
import com.osce.vo.biz.mobile.score.MobileScoreSheetVo;

import java.util.List;

/**
 * @ClassName: PfMobileService
 * @Description: 移动端接口
 * @Author yangtongbin
 * @Date 2019-07-07
 */
public interface PfMobileService {

    /**
     * 移动端 - 首页
     *
     * @param dto
     * @return
     */
    MobileMainVo mobileMain(MobileDto dto);

    /**
     * 当前学员信息
     *
     * @param dto
     * @return
     */
    MobileStudentInfoVo selectCurrentStudentInfo(MobileDto dto);

    /**
     * 待考学员信息
     *
     * @param dto
     * @return
     */
    MobileQueueVo listWaitingStudentInfo(MobileDto dto);

    /**
     * 叫号、开考、缺考登记
     *
     * @param dto
     * @return
     */
    boolean handleExamStatus(ExecAuthDto dto);

    /**
     * 获取评分页面头部信息
     *
     * @param dto
     * @return
     */
    MobileScoreHeaderVo selectScoreHeader(MobileScoreDto dto);

    /**
     * 主考官评分表（第一个sheet页）
     *
     * @param dto
     * @return
     */
    List<MobileScoreSheetVo> listScoreSheet(MobileScoreDto dto);

    /**
     * 评分（第一个sheet页）
     *
     * @param dto
     * @return
     */
    Long saveSheetScore(MobileScoreAddDto dto);

    /**
     * 查询评量表
     *
     * @param idExec
     * @return
     */
    List<MobileEvaluateVo> listCobEvaluate(Long idExec);

    /**
     * 查询评量表明细
     *
     * @param idExec        执行id
     * @param idCobEvaluate 评量表id
     * @return
     */
    List<MobileEvaluateDetail> listEvaluateDetail(Long idExec, Long idCobEvaluate);

    /**
     * 保存评量表
     *
     * @param dto
     * @return
     */
    Long saveEvaluate(WeEvaluate dto);

    /**
     * 保存评量表明细
     *
     * @param dto
     * @return
     */
    Long saveEvaluateDetail(WeEvaluateDetail dto);

}
