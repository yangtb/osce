package com.osce.api.biz.training.res.model;

import com.osce.dto.biz.training.res.model.FaultDto;
import com.osce.dto.biz.training.res.model.ModelDto;
import com.osce.dto.biz.training.res.model.RepairDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.ErpDevice;
import com.osce.entity.ErpDeviceCase;
import com.osce.entity.ErpDeviceFault;
import com.osce.entity.ErpDeviceRepair;
import com.osce.result.PageResult;

import java.util.List;

/**
 * @ClassName: PfModelService
 * @Description: 教学模块
 * @Author yangtongbin
 * @Date 2019-05-10
 */
public interface PfModelService {

    /**
     * 列表
     *
     * @param dto
     * @return
     */
    PageResult pageModels(ModelDto dto);

    /**
     * 增加
     *
     * @param dto
     * @return
     */
    Long addModel(ErpDevice dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    boolean delModel(PfBachChangeStatusDto dto);

    /**
     * 设备列表
     *
     * @param dto
     * @return
     */
    PageResult pageModelDevice(ModelDto dto);

    /**
     * 增加设备
     *
     * @param dto
     * @return
     */
    Long addModelDevice(ErpDeviceCase dto);

    /**
     * 删除设备
     *
     * @param dto
     * @return
     */
    boolean delModelDevice(PfBachChangeStatusDto dto);

    /**
     * 故障记录
     *
     * @param dto
     * @return
     */
    List<ErpDeviceFault> listDeviceFault(ModelDto dto);

    /**
     * 维修记录
     *
     * @param dto
     * @return
     */
    List<ErpDeviceRepair> listDeviceRepair(ModelDto dto);

    /**
     * 故障登记
     *
     * @param dto
     * @return
     */
    boolean saveDeviceFault(FaultDto dto);

    /**
     * 维修登记
     *
     * @param dto
     * @return
     */
    boolean saveDeviceRepair(RepairDto dto);

    /**
     * 故障删除
     *
     * @param dto
     * @return
     */
    boolean delDeviceFault(PfBachChangeStatusDto dto);

    /**
     * 维修删除
     *
     * @param dto
     * @return
     */
    boolean delDeviceRepair(PfBachChangeStatusDto dto);

}
