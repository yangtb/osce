package com.osce.service.biz.mobile;

import com.osce.api.biz.mobile.PfMobileService;
import com.osce.dto.biz.mobile.MobileDto;
import com.osce.orm.biz.mobile.PfMobileDao;
import com.osce.vo.biz.mobile.MobileMainVo;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @ClassName: PfMobileServiceImpl
 * @Description: 移动端接口实现
 * @Author yangtongbin
 * @Date 2019-07-07
 */
@Service
public class PfMobileServiceImpl implements PfMobileService {

    @Resource
    private PfMobileDao pfMobileDao;

    @Override
    public MobileMainVo mobileMain(MobileDto dto) {
        return pfMobileDao.mobileMain(dto);
    }

}
