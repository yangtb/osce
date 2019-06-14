package com.osce.api.biz.plan.template;

import com.osce.dto.biz.plan.template.*;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdItemStore;
import com.osce.entity.TdSpCase;
import com.osce.result.PageResult;
import com.osce.vo.biz.plan.template.PaperLeftVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @ClassName: PfPaperService
 * @Description: 试卷设计
 * @Author yangtongbin
 * @Date 2019-06-08
 */
public interface PfPaperService {

    /**
     * 获取左侧列表
     *
     * @param idModel 模板id
     * @return
     */
    List<PaperLeftVo> listLeft(Long idModel);

    /**
     * 保存题集
     *
     * @param dto
     * @return
     */
    Long addTdItemStore(TdItemStore dto);

    /**
     * 拷贝题集
     *
     * @param dto
     */
    boolean copyTdItemStore(PfParamItemStoreDto dto);

    /**
     * 获取试题题集
     *
     * @param dto
     * @return
     */
    TdItemStore selectTdItemStore(PfPaperDto dto);

    /**
     * add试卷参数
     *
     * @param dto
     * @return
     */
    PfPaperParam addPaperParam(PfPaperParam dto);

    /**
     * 获取试卷参数
     *
     * @param idItemStore 题集ID
     * @return
     */
    PfPaperParam selectPaperParam(Long idItemStore);

    /**
     * 考题list
     *
     * @param dto
     * @return
     */
    PageResult listItem(PfPaperDto dto);

    /**
     * 设置必考题
     *
     * @param dto
     * @return
     */
    boolean setPaperMustParam(PfPaperMustDto dto);

    /**
     * 生成试卷
     *
     * @param dto
     * @return
     */
    boolean generatePaper(PfParamItemStoreDto dto);

    /**
     * 试卷列表
     *
     * @param dto
     * @return
     */
    PageResult listPaper(PfPaperDto dto);

    /**
     * 删除试卷
     *
     * @param dto
     * @return
     */
    boolean delPaper(PfBachChangeStatusDto dto);

    /**
     * 新增sp病例
     *
     * @param dto
     * @return
     */
    TdSpCase addSpCase(TdSpCase dto);

    /**
     * 另存sp病例
     *
     * @param dto
     * @return
     */
    boolean copyTdSpCase(PfSpCaseDto dto);

    /**
     * sp 列表
     *
     * @param dto
     * @return
     */
    PageResult listSp(PfPaperDto dto);

    /**
     * 删除sp
     *
     * @param dto
     * @return
     */
    boolean delSp(PfBachChangeStatusDto dto);

}
