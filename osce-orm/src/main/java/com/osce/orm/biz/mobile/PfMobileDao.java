package com.osce.orm.biz.mobile;

import com.osce.dto.biz.mobile.MobileDto;
import com.osce.vo.biz.mobile.MobileMainVo;

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

}
