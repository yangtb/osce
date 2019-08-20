package com.osce.api.biz.training.res.pick;

import com.osce.dto.biz.training.res.pick.PfPickDeviceDto;
import com.osce.dto.biz.training.res.pick.PfPickDto;
import com.osce.dto.biz.training.res.pick.PfTpPickCaseDto;
import com.osce.entity.TpPicking;
import com.osce.result.PageResult;
import com.osce.vo.biz.training.res.pick.TpPickingCaseVo;

import java.util.List;

/**
 * @ClassName: PfPickService
 * @Description: 领料计划
 * @Author yangtongbin
 * @Date 2019-08-19
 */
public interface PfPickService {

    /**
     * 领料清单
     *
     * @param dto
     * @return
     */
    PageResult pagePick(PfPickDto dto);

    /**
     * 设备列表
     *
     * @param dto
     * @return
     */
    List<TpPickingCaseVo> listDeviceCase(PfPickDeviceDto dto);

    /**
     * save领料实例
     *
     * @param dto
     * @return
     */
    boolean saveTpPickCase(PfTpPickCaseDto dto);

    /**
     * 更新实领数量
     *
     * @param dto
     * @return
     */
    boolean updateTpPick(TpPicking dto);
}
