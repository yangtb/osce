package com.osce.api.system.notice;

import com.osce.dto.system.notice.PfNoticeDto;
import com.osce.entity.SysNotice;
import com.osce.vo.system.notice.SysNoticeVo;

import java.util.List;


public interface PfNoticeService {

    /**
     * 公告总数
     */
    Long countNotices(PfNoticeDto dto);

    /**
     * 获取公告列表
     *
     * @return
     */
    List<SysNoticeVo> listNotices(PfNoticeDto dto);

    /**
     * 新增公告
     *
     * @param dto
     * @return
     */
    boolean addNotice(SysNotice dto);

    /**
     * 编辑公告
     *
     * @param dto
     * @return
     */
    boolean editNotice(SysNotice dto);

    /**
     * 删除公告
     *
     * @param notices 公告ID
     * @return
     */
    boolean delNotice(List<Long> notices);
}
