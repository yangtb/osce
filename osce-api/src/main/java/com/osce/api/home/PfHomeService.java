package com.osce.api.home;

import com.osce.dto.home.PfHomeDto;
import com.osce.vo.home.PfHomeVo;

/**
 * @author yangtongbin
 * @className: PfHomeService
 * @description: 首页信息
 * @date 2018/9/26
 */
public interface PfHomeService {

    /**
     * 获取首页信息
     *
     * @param dto
     * @return
     */
    PfHomeVo selectHomeInfo(PfHomeDto dto);
}
