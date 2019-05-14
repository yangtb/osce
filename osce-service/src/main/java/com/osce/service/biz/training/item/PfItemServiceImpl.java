package com.osce.service.biz.training.item;

import com.osce.api.biz.training.item.PfItemService;
import com.osce.dto.biz.training.item.ItemDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.IbmItem;
import com.osce.entity.IbmItemOption;
import com.osce.entity.IbmItemSection;
import com.osce.entity.IbmItemStore;
import com.osce.orm.biz.training.item.PfItemDao;
import com.osce.param.PageParam;
import com.osce.result.PageResult;
import com.osce.result.ResultFactory;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<IbmItemSection> listSection(Long idItemStore) {
        return pfItemDao.listSection(idItemStore);
    }

    @Override
    public Long saveSection(IbmItemSection dto) {
        if (dto.getIdItemSection() == null) {
            pfItemDao.addSection(dto);
        } else {
            pfItemDao.editSection(dto);
        }
        return dto.getIdItemSection();
    }

    @Override
    public boolean delSection(PfBachChangeStatusDto dto) {
        int num = pfItemDao.delSection(dto);
        return num >= 1 ? true : false;
    }

    @Override
    public PageResult pageItemManage(ItemDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfItemDao.countItemManage(dto),
                pfItemDao.listItemManage(dto));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveExam(IbmItem dto) {
        List<IbmItemOption> itemOptions = dto.getItemOptions();
        if (dto.getIdItem() == null) {
            pfItemDao.addExam(dto);
            if (!CollectionUtils.isEmpty(itemOptions)) {
                for (IbmItemOption ibmItemOption : itemOptions) {
                    ibmItemOption.setIdItem(dto.getIdItem());
                    pfItemDao.addItemOption(ibmItemOption);
                }
            }
        } else {
            pfItemDao.editExam(dto);
            if (!CollectionUtils.isEmpty(itemOptions)) {
                for (IbmItemOption ibmItemOption : itemOptions) {
                    ibmItemOption.setIdItem(dto.getIdItem());
                    if (ibmItemOption.getIdItemOption() == null) {
                        pfItemDao.addItemOption(ibmItemOption);
                    } else {
                        pfItemDao.editItemOption(ibmItemOption);
                    }
                }
            }
        }
        return dto.getIdItem();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delExam(PfBachChangeStatusDto dto) {
        Long idItem = dto.getList().get(0);
        if (idItem != null) {
            pfItemDao.delExamByIdItem(idItem);
            pfItemDao.delOptionByIdItem(idItem);
        }
        return true;
    }

    @Override
    public List<IbmItemOption> listItemOption(ItemDto dto) {
        return pfItemDao.listItemOption(dto);
    }

    @Override
    public boolean delOption(PfBachChangeStatusDto dto) {
        return pfItemDao.delOption(dto) >= 1 ? true : false;
    }

}
