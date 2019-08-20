package com.osce.orm.biz.training.res.pick;

import com.osce.dto.biz.training.res.pick.PfPickDeviceDto;
import com.osce.dto.biz.training.res.pick.PfPickDto;
import com.osce.dto.biz.training.res.pick.PfTpPickCaseDto;
import com.osce.vo.biz.plan.manage.TpPickingVo;
import com.osce.vo.biz.training.res.pick.TpPickingCaseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: PfPickDao
 * @Description: 领料
 * @Author yangtongbin
 * @Date 2019-08-19
 */
public interface PfPickDao {


    /**
     * 领料清单总数
     *
     * @param dto
     * @return
     */
    Long countPick(PfPickDto dto);

    /**
     * 领料清单
     *
     * @param dto
     * @return
     */
    List<TpPickingVo> listPick(PfPickDto dto);

    /**
     * 设备列表
     *
     * @param dto
     * @return
     */
    List<TpPickingCaseVo> listDeviceCase(PfPickDeviceDto dto);

    /**
     * 删除领料实例
     *
     * @param idTpPicking 领料计划ID
     * @return
     */
    int delTpPickingCase(@Param("idTpPicking") Long idTpPicking);

    /**
     * save 领料实例
     *
     * @param dto
     * @return
     */
    int saveTpPickCase(PfTpPickCaseDto dto);

    /**
     * 更新实领数量
     *
     * @param fgPicked    已领数量
     * @param idTpPicking 领料计划ID
     * @return
     */
    int updatePickNum(@Param("fgPicked") Integer fgPicked,
                      @Param("idTpPicking") Long idTpPicking);

}
