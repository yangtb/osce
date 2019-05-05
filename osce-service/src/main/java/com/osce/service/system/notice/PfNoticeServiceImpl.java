package com.osce.service.system.notice;

import com.osce.api.system.notice.PfNoticeService;
import com.osce.dto.system.notice.PfNoticeDto;
import com.osce.entity.SysNotice;
import com.osce.orm.system.notice.PfNoticeDao;
import com.osce.vo.system.notice.SysNoticeVo;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfNoticeServiceImpl implements PfNoticeService {

    @Resource
    private PfNoticeDao pfNoticeDao;

    @Override
    public Long countNotices(PfNoticeDto dto) {
        return pfNoticeDao.countNotices(dto);
    }

    @Override
    public List<SysNoticeVo> listNotices(PfNoticeDto dto) {
        return pfNoticeDao.listNotices(dto);
    }

    @Override
    public boolean addNotice(SysNotice dto) {
        return pfNoticeDao.addNotice(dto) == 1 ? true : false;
    }

    @Override
    public boolean editNotice(SysNotice dto) {
        return pfNoticeDao.editNotice(dto) == 1 ? true : false;
    }

    @Override
    public boolean delNotice(List<Long> notices) {
        return pfNoticeDao.delNotice(notices) >= 1 ? true : false;
    }
}
