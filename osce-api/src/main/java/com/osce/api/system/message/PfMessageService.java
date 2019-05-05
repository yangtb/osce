package com.osce.api.system.message;


import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.dto.system.message.PfMessageDto;
import com.osce.dto.system.message.PfMessageTemplateDto;
import com.osce.entity.MessageTemplate;

import java.util.List;

/**
 * @ClassName: PfMessageService
 * @Description: 消息接口
 * @Author yangtongbin
 * @Date 2018/9/16 20:19
 */
public interface PfMessageService {

    /**
     * 消息模板总数
     *
     * @param dto
     * @return
     */
    Long countMessages(PfMessageDto dto);

    /**
     * 消息模板列表
     *
     * @param dto
     * @return
     */
    List<MessageTemplate> listMessages(PfMessageDto dto);

    /**
     * 新增消息模板
     *
     * @param dto
     * @return
     */
    boolean addMessageTemplate(PfMessageTemplateDto dto);

    /**
     * 编辑消息模板
     *
     * @param dto
     * @return
     */
    boolean editMessageTemplate(PfMessageTemplateDto dto);

    /**
     * 删除消息模板
     *
     * @param dto
     * @return
     */
    boolean updateStatus(PfBachChangeStatusDto dto);

    /**
     * 查询模板是否已存在
     *
     * @param templateCode 模板code
     * @return
     */
    boolean isExistTemplate(String templateCode);
}
