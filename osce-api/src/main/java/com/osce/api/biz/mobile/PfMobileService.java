package com.osce.api.biz.mobile;

import com.osce.dto.biz.mobile.MobileDto;
import com.osce.vo.biz.mobile.MobileMainVo;

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
}