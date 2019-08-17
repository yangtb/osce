package com.osce.orm.biz.mobile;

import com.osce.dto.biz.mobile.MobileDto;
import com.osce.dto.biz.mobile.MobileScoreDto;
import com.osce.entity.WeEvaluate;
import com.osce.entity.WeEvaluateDetail;
import com.osce.entity.WeScore;
import com.osce.vo.biz.mobile.MobileMainVo;
import com.osce.vo.biz.mobile.MobileStudentInfoVo;
import com.osce.vo.biz.mobile.score.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: PfMobileDao
 * @Description: 移动端
 * @Author yangtongbin
 * @Date 2019-07-08
 */
public interface PfMobileDao {

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
    List<MobileStudentInfoVo> listWaitingStudentInfo(MobileDto dto);

    /**
     * 待考学员总数
     *
     * @param dto
     * @return
     */
    Integer countWaitingStudent(MobileDto dto);

    /**
     * 获取评分页面头部信息
     *
     * @param idExecQueue 执行id
     * @return
     */
    MobileScoreHeaderVo selectScoreHeader(@Param("idExecQueue") Long idExecQueue);

    /**
     * 主考官评分表（第一个sheet页）
     *
     * @param dto
     * @return
     */
    List<MobileScoreSheetVo> listScoreSheet(MobileScoreDto dto);

    /**
     * add 执行_评分明细
     *
     * @param dto
     * @return
     */
    int saveSheetScore(WeScore dto);

    /**
     * edit执行_评分明细
     *
     * @param dto
     * @return
     */
    int editSheetScore(WeScore dto);

    /**
     * 执行信息
     *
     * @param idExec        执行id
     * @param cdAssistantCa 考官类型
     * @return
     */
    MobileExecVo selectExecInfo(@Param("idExec") Long idExec,
                                @Param("cdAssistantCa") String cdAssistantCa);

    /**
     * 类型
     *
     * @param idExec 执行id
     * @return
     */
    String selectSdSkillCa(@Param("idExec") Long idExec);

    /**
     * 评量表list
     *
     * @param idExec        执行id
     * @param cdCobEvaluate
     * @return
     */
    List<MobileEvaluateVo> listCobEvaluate(@Param("idExec") Long idExec,
                                           @Param("cdCobEvaluate") String cdCobEvaluate);

    /**
     * 查询评量表明细
     *
     * @param idExec        执行id
     * @param idCobEvaluate 评量表id
     * @return
     */
    List<MobileEvaluateDetail> listEvaluateDetail(@Param("idExec") Long idExec,
                                                  @Param("idCobEvaluate") Long idCobEvaluate);

    /**
     * add评量表
     *
     * @param dto
     * @return
     */
    int addEvaluate(WeEvaluate dto);

    /**
     * edit评量表
     *
     * @param dto
     * @return
     */
    int editEvaluate(WeEvaluate dto);

    /**
     * add评量表明细
     *
     * @param dto
     * @return
     */
    int addEvaluateDetail(WeEvaluateDetail dto);

    /**
     * edit评量表明细
     *
     * @param dto
     * @return
     */
    int editEvaluateDetail(WeEvaluateDetail dto);

}
