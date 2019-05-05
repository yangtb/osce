package com.osce.orm.system.message;

import com.osce.dto.system.message.PfMessageDto;
import com.osce.dto.system.message.PfMessageTemplateDto;
import com.osce.entity.MessageTemplate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfMessageDao {

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
    int addMessageTemplate(PfMessageTemplateDto dto);

    /**
     * 编辑消息模板
     *
     * @param dto
     * @return
     */
    int editMessageTemplate(PfMessageTemplateDto dto);

    /**
     * 删除消息模板
     *
     * @param list      主键集合
     * @param isDeleted 是否删除
     * @return
     */
    int updateStatus(@Param("list") List<Long> list,
                     @Param("isDeleted") String isDeleted);

    /**
     * 查询模板是否已存在
     *
     * @param templateCode 模板code
     * @return
     */
    int isExistTemplate(@Param("templateCode") String templateCode);

    /**
     * 根据模板code查询消息模板
     *
     * @param templateCode
     * @return
     */
    MessageTemplate selectTempInfoByCode(@Param("templateCode") String templateCode);
}
