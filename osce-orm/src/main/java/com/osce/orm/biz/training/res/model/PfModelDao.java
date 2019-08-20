package com.osce.orm.biz.training.res.model;

import com.osce.dto.biz.training.res.model.ModelDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.ErpDevice;
import com.osce.entity.ErpDeviceCase;
import com.osce.entity.ErpDeviceFault;
import com.osce.entity.ErpDeviceRepair;
import com.osce.vo.biz.training.res.model.ErpDeviceCaseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: PfModelDao
 * @Description: 管理
 * @Author yangtongbin
 * @Date 2019-05-10
 */
public interface PfModelDao {

    /**
     * 列表
     *
     * @param dto
     * @return
     */
    List<ErpDevice> listModels(ModelDto dto);

    /**
     * 总数
     *
     * @param dto
     * @return
     */
    Long countModel(ModelDto dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long addModel(ErpDevice dto);

    /**
     * 编辑
     *
     * @param dto
     * @return
     */
    int editModel(ErpDevice dto);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    int delModel(PfBachChangeStatusDto dto);

    /**
     * 设备列表
     *
     * @param dto
     * @return
     */
    List<ErpDeviceCase> listModelDevice(ModelDto dto);

    /**
     * 新增设备
     *
     * @param dto
     * @return
     */
    Long addModelDevice(ErpDeviceCase dto);

    /**
     * 编辑设备
     *
     * @param dto
     * @return
     */
    int editModelDevice(ErpDeviceCase dto);

    /**
     * 删除设备
     *
     * @param dto
     * @return
     */
    int delModelDevice(PfBachChangeStatusDto dto);

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
    int addDeviceFault(ErpDeviceFault dto);

    /**
     * 故障编辑
     *
     * @param dto
     * @return
     */
    int editDeviceFault(ErpDeviceFault dto);

    /**
     * 故障删除
     *
     * @param dto
     * @return
     */
    int delDeviceFault(PfBachChangeStatusDto dto);

    /**
     * 维修登记
     *
     * @param dto
     * @return
     */
    int addDeviceRepair(ErpDeviceRepair dto);

    /**
     * 维修编辑
     *
     * @param dto
     * @return
     */
    int editDeviceRepair(ErpDeviceRepair dto);

    /**
     * 维修删除
     *
     * @param dto
     * @return
     */
    int delDeviceRepair(PfBachChangeStatusDto dto);

    /**
     * 设备列表
     *
     * @param idDevice 设备id
     * @return
     */
    List<ErpDeviceCaseVo> listDeviceCase(@Param("idDevice") Long idDevice);

}
