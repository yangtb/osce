package com.osce.api.biz.mobile;

import com.osce.dto.biz.execute.ExecAuthDto;
import com.osce.dto.biz.mobile.MobileDto;
import com.osce.vo.biz.mobile.MobileMainVo;
import com.osce.vo.biz.mobile.MobileStudentInfoVo;

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
    List<MobileStudentInfoVo> listWaitingStudentInfo(MobileDto dto);

    /**
     * 叫号、开考、缺考登记
     *
     * @param dto
     * @return
     */
    boolean handleExamStatus(ExecAuthDto dto);

}
