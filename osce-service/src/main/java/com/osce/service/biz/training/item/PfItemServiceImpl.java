package com.osce.service.biz.training.item;

import com.osce.api.biz.training.item.PfItemService;
import com.osce.dto.biz.training.item.ItemBach;
import com.osce.dto.biz.training.item.ItemBachDto;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 题目类型
     */
    public static final Map<String, String> sdItemCaMap;

    /**
     * 题目难度
     */
    public static final Map<String, String> sdItemLevelMap;

    static {
        sdItemCaMap = new HashMap<>(3);
        sdItemCaMap.put("A1", "1");
        sdItemCaMap.put("A2", "2");
        sdItemCaMap.put("B1", "3");

        sdItemLevelMap = new HashMap<>(5);
        sdItemLevelMap.put("简单", "1");
        sdItemLevelMap.put("普通", "2");
        sdItemLevelMap.put("中", "3");
        sdItemLevelMap.put("困难", "4");
        sdItemLevelMap.put("极难", "5");
    }

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

    @Override
    public List<ItemBach> addBachItem(ItemBachDto dto) {
        List<ItemBach> items = dto.getItems();
        List<ItemBach> errorItems = new ArrayList<>();
        for (ItemBach itemBach : items) {
            try {
                /* 1、题集 */
                Long idItemStore = pfItemDao.selectIbmItemStoreId(itemBach.getNaItemStore(), dto.getIdOrg());
                if (idItemStore == null) {
                    IbmItemStore ibmItemStore = new IbmItemStore();
                    ibmItemStore.setNaItemStore(itemBach.getNaItemStore());
                    ibmItemStore.setCreator(dto.getCreator());
                    ibmItemStore.setOperator(dto.getCreator());
                    ibmItemStore.setIdOrg(dto.getIdOrg());
                    pfItemDao.addItem(ibmItemStore);
                    idItemStore = ibmItemStore.getIdItemStore();
                }
                /* 2、题集 */
                Long idItemSection = pfItemDao.selectIbmItemSectionId(itemBach.getNaItemSection(), idItemStore);
                if (idItemSection == null) {
                    IbmItemSection ibmItemSection = new IbmItemSection();
                    ibmItemSection.setIdItemStore(idItemStore);
                    ibmItemSection.setNaItemSection(itemBach.getNaItemSection());
                    pfItemDao.addSection(ibmItemSection);
                    idItemSection = ibmItemSection.getIdItemSection();
                }
                /* 3、题目 */
                Long idItem = pfItemDao.selectIbmItem(idItemStore, idItemSection, itemBach.getMainItem());
                if (idItem == null) {
                    IbmItem ibmItem = new IbmItem();
                    ibmItem.setIdItemStore(idItemStore);
                    ibmItem.setIdItemSection(idItemSection);
                    ibmItem.setSdItemCa(sdItemCaMap.get(itemBach.getSdItemCa()));
                    ibmItem.setMainItem(itemBach.getMainItem());
                    ibmItem.setSdItemLevel(sdItemLevelMap.get(itemBach.getSdItemLevel()));
                    ibmItem.setItemAnalysis(itemBach.getItemAnalysis());
                    ibmItem.setScoreDefault(itemBach.getScoreDefault());
                    ibmItem.setCreator(dto.getCreator());
                    ibmItem.setOperator(dto.getCreator());
                    // 默认题库试题
                    ibmItem.setSdItemFrom("1");
                    pfItemDao.addExam(ibmItem);
                    idItem = ibmItem.getIdItem();
                }
                /* 4、选项 */
                List<IbmItemOption> options = new ArrayList<>(4);
                IbmItemOption ibmItemOptionA = new IbmItemOption();
                ibmItemOptionA.setIdItem(idItem);
                ibmItemOptionA.setCdIte(itemBach.getCdIteStr_A());
                ibmItemOptionA.setNaOption(itemBach.getNaOption_A());
                ibmItemOptionA.setFgRight("是".equals(itemBach.getFgRight_A()) ? "1" : "0");
                options.add(ibmItemOptionA);

                IbmItemOption ibmItemOptionB = new IbmItemOption();
                ibmItemOptionB.setIdItem(idItem);
                ibmItemOptionB.setCdIte(itemBach.getCdIteStr_B());
                ibmItemOptionB.setNaOption(itemBach.getNaOption_B());
                ibmItemOptionB.setFgRight("是".equals(itemBach.getFgRight_B()) ? "1" : "0");
                options.add(ibmItemOptionB);

                IbmItemOption ibmItemOptionC = new IbmItemOption();
                ibmItemOptionC.setIdItem(idItem);
                ibmItemOptionC.setCdIte(itemBach.getCdIteStr_C());
                ibmItemOptionC.setNaOption(itemBach.getNaOption_C());
                ibmItemOptionC.setFgRight("是".equals(itemBach.getFgRight_C()) ? "1" : "0");
                options.add(ibmItemOptionC);

                IbmItemOption ibmItemOptionD = new IbmItemOption();
                ibmItemOptionD.setIdItem(idItem);
                ibmItemOptionD.setCdIte(itemBach.getCdIteStr_D());
                ibmItemOptionD.setNaOption(itemBach.getNaOption_D());
                ibmItemOptionD.setFgRight("是".equals(itemBach.getFgRight_D()) ? "1" : "0");
                options.add(ibmItemOptionD);

                for (IbmItemOption ibmItemOption : options) {
                    Long idItemOption = pfItemDao.selectIbmItemOptionId(ibmItemOption.getCdIte(), idItem);
                    if (idItemOption == null) {
                        pfItemDao.addItemOption(ibmItemOption);
                    } else {
                        ibmItemOption.setIdItemOption(idItemOption);
                        pfItemDao.editItemOption(ibmItemOption);
                    }
                }
            } catch (Exception e) {
                errorItems.add(itemBach);
            }
        }
        return errorItems;
    }

}
