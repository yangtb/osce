package com.osce.service.system.message;

import com.osce.api.system.message.PfMessageService;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.dto.system.message.PfMessageDto;
import com.osce.dto.system.message.PfMessageTemplateDto;
import com.osce.entity.MessageTemplate;
import com.osce.orm.system.message.PfMessageDao;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfMessageServiceImpl implements PfMessageService {

    @Resource
    private PfMessageDao pfMessageDao;

    @Override
    public Long countMessages(PfMessageDto dto) {
        return pfMessageDao.countMessages(dto);
    }

    @Override
    public List<MessageTemplate> listMessages(PfMessageDto dto) {
        return pfMessageDao.listMessages(dto);
    }

    @Override
    public boolean addMessageTemplate(PfMessageTemplateDto dto) {
        return pfMessageDao.addMessageTemplate(dto) == 1 ? true : false;
    }

    @Override
    public boolean editMessageTemplate(PfMessageTemplateDto dto) {
        return pfMessageDao.editMessageTemplate(dto) == 1 ? true : false;
    }

    @Override
    public boolean updateStatus(PfBachChangeStatusDto dto) {
        return pfMessageDao.updateStatus(dto.getList(), dto.getStatus()) >= 1 ? true : false;
    }

    @Override
    public boolean isExistTemplate(String templateCode) {
        return pfMessageDao.isExistTemplate(templateCode) >= 1 ? true : false;
    }
}
