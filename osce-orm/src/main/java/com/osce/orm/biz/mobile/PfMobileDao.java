package com.osce.orm.biz.mobile;

import com.osce.dto.biz.mobile.MobileDto;
import com.osce.vo.biz.mobile.MobileMainVo;
import com.osce.vo.biz.mobile.MobileStudentInfoVo;

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

}
