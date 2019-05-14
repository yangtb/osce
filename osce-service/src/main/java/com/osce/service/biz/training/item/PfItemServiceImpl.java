package com.osce.service.biz.training.item;

import com.osce.api.biz.training.item.PfItemService;
import com.osce.dto.biz.training.item.ItemDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.IbmItemStore;
import com.osce.orm.biz.training.item.PfItemDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @ClassName: PfItemServiceImpl
 * @Description: 题库实现
 * @Author yangtongbin
 * @Date 2019-05-14
 */
@Service
public class PfItemServiceImpl implements PfItemService {

    @Resource
    private PfItemDao pfItemDao;

    @Override
    public PageResult pageItem(ItemDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfItemDao.countItem(dto),
                pfItemDao.listItem(dto));
    }

    @Override
    public Long addItem(IbmItemStore dto) {
        if (dto.getIdItemStore() == null) {
            pfItemDao.addItem(dto);
        } else {
            pfItemDao.editItem(dto);
        }
        return dto.getIdItemStore();
    }

    @Override
    public boolean delItem(PfBachChangeStatusDto dto) {
        int num = pfItemDao.delItem(dto);
        return num >= 1 ? true : false;
    }

}
