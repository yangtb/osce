package com.osce.service.biz.training.item;

import com.osce.api.biz.training.item.PfTdItemService;
import com.osce.dto.biz.training.item.ItemBach;
import com.osce.dto.biz.training.item.ItemBachDto;
import com.osce.dto.biz.training.item.ItemDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.TdItem;
import com.osce.entity.TdItemOption;
import com.osce.entity.TdItemSection;
import com.osce.entity.TdItemStore;
import com.osce.orm.biz.training.item.PfTdItemDao;
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
public class PfTdItemServiceImpl implements PfTdItemService {

    @Resource
    private PfTdItemDao pfTdItemDao;

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
        sdItemLevelMap.put("易", "1");
        sdItemLevelMap.put("较易", "2");
        sdItemLevelMap.put("中", "3");
        sdItemLevelMap.put("难", "4");
        sdItemLevelMap.put("较难", "5");
    }

    @Override
    public PageResult pageItem(ItemDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfTdItemDao.countItem(dto),
                pfTdItemDao.listItem(dto));
    }

    @Override
    public Long addItem(TdItemStore dto) {
        if (dto.getIdItemStore() == null) {
            pfTdItemDao.addItem(dto);
        } else {
            pfTdItemDao.editItem(dto);
        }
        return dto.getIdItemStore();
    }

    @Override
    public boolean delItem(PfBachChangeStatusDto dto) {
        int num = pfTdItemDao.delItem(dto);
        return num >= 1 ? true : false;
    }

    @Override
    public List<TdItemSection> listSection(Long idItemStore) {
        return pfTdItemDao.listSection(idItemStore);
    }

    @Override
    public Long saveSection(TdItemSection dto) {
        if (dto.getIdItemSection() == null) {
            pfTdItemDao.addSection(dto);
        } else {
            pfTdItemDao.editSection(dto);
        }
        return dto.getIdItemSection();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delSection(PfBachChangeStatusDto dto) {
        pfTdItemDao.delSection(dto);
        pfTdItemDao.delTdItemBySection(dto.getList());
        return true;
    }

    @Override
    public PageResult pageItemManage(ItemDto dto) {
        PageParam.initPageDto(dto);
        return ResultFactory.initPageResultWithSuccess(pfTdItemDao.countItemManage(dto),
                pfTdItemDao.listItemManage(dto));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveExam(TdItem dto) {
        List<TdItemOption> itemOptions = dto.getItemOptions();
        if (dto.getIdItem() == null) {
            pfTdItemDao.addExam(dto);
            if (!CollectionUtils.isEmpty(itemOptions)) {
                for (TdItemOption tdItemOption : itemOptions) {
                    tdItemOption.setIdItem(dto.getIdItem());
                    pfTdItemDao.addItemOption(tdItemOption);
                }
            }
        } else {
            pfTdItemDao.editExam(dto);
            if (!CollectionUtils.isEmpty(itemOptions)) {
                for (TdItemOption tdItemOption : itemOptions) {
                    tdItemOption.setIdItem(dto.getIdItem());
                    if (tdItemOption.getIdItemOption() == null) {
                        pfTdItemDao.addItemOption(tdItemOption);
                    } else {
                        pfTdItemDao.editItemOption(tdItemOption);
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
            pfTdItemDao.delExamByIdItem(idItem);
            pfTdItemDao.delOptionByIdItem(idItem);
        }
        return true;
    }

    @Override
    public List<TdItemOption> listItemOption(ItemDto dto) {
        return pfTdItemDao.listItemOption(dto);
    }

    @Override
    public boolean delOption(PfBachChangeStatusDto dto) {
        return pfTdItemDao.delOption(dto) >= 1 ? true : false;
    }

    @Override
    public List<ItemBach> addBachItem(ItemBachDto dto) {
        List<ItemBach> items = dto.getItems();
        List<ItemBach> errorItems = new ArrayList<>();
        for (ItemBach itemBach : items) {
            try {
                /* 1、题集 */
                Long idItemStore = itemBach.getIdItemStore();
                /* 2、题集 */
                Long idItemSection = pfTdItemDao.selectTdItemSectionId(itemBach.getNaItemSection(), idItemStore);
                if (idItemSection == null) {
                    TdItemSection tdItemSection = new TdItemSection();
                    tdItemSection.setIdItemStore(idItemStore);
                    tdItemSection.setNaItemSection(itemBach.getNaItemSection());
                    pfTdItemDao.addSection(tdItemSection);
                    idItemSection = tdItemSection.getIdItemSection();
                }
                /* 3、题目 */
                Long idItem = pfTdItemDao.selectTdItem(idItemStore, idItemSection, itemBach.getMainItem());
                if (idItem == null) {
                    TdItem tdItem = new TdItem();
                    tdItem.setIdItemStore(idItemStore);
                    tdItem.setIdItemSection(idItemSection);
                    tdItem.setSdItemCa(sdItemCaMap.get(itemBach.getSdItemCa()));
                    tdItem.setMainItem(itemBach.getMainItem());
                    tdItem.setSdItemLevel(sdItemLevelMap.get(itemBach.getSdItemLevel()));
                    tdItem.setItemAnalysis(itemBach.getItemAnalysis());
                    tdItem.setScoreDefault(itemBach.getScoreDefault());
                    // 默认题库试题
                    tdItem.setFgMust("1");
                    tdItem.setFgActive("1");
                    tdItem.setSdItemFrom("3");
                    pfTdItemDao.addExam(tdItem);
                    idItem = tdItem.getIdItem();
                }
                /* 4、选项 */
                List<TdItemOption> options = new ArrayList<>(4);
                TdItemOption tdItemOptionA = new TdItemOption();
                tdItemOptionA.setIdItem(idItem);
                tdItemOptionA.setCdIte(itemBach.getCdIteStr_A());
                tdItemOptionA.setNaOption(itemBach.getNaOption_A());
                tdItemOptionA.setFgRight("是".equals(itemBach.getFgRight_A()) ? "1" : "0");
                options.add(tdItemOptionA);

                TdItemOption tdItemOptionB = new TdItemOption();
                tdItemOptionB.setIdItem(idItem);
                tdItemOptionB.setCdIte(itemBach.getCdIteStr_B());
                tdItemOptionB.setNaOption(itemBach.getNaOption_B());
                tdItemOptionB.setFgRight("是".equals(itemBach.getFgRight_B()) ? "1" : "0");
                options.add(tdItemOptionB);

                TdItemOption tdItemOptionC = new TdItemOption();
                tdItemOptionC.setIdItem(idItem);
                tdItemOptionC.setCdIte(itemBach.getCdIteStr_C());
                tdItemOptionC.setNaOption(itemBach.getNaOption_C());
                tdItemOptionC.setFgRight("是".equals(itemBach.getFgRight_C()) ? "1" : "0");
                options.add(tdItemOptionC);

                TdItemOption tdItemOptionD = new TdItemOption();
                tdItemOptionD.setIdItem(idItem);
                tdItemOptionD.setCdIte(itemBach.getCdIteStr_D());
                tdItemOptionD.setNaOption(itemBach.getNaOption_D());
                tdItemOptionD.setFgRight("是".equals(itemBach.getFgRight_D()) ? "1" : "0");
                options.add(tdItemOptionD);

                for (TdItemOption tdItemOption : options) {
                    Long idItemOption = pfTdItemDao.selectTdItemOptionId(tdItemOption.getCdIte(), idItem);
                    if (idItemOption == null) {
                        pfTdItemDao.addItemOption(tdItemOption);
                    } else {
                        tdItemOption.setIdItemOption(idItemOption);
                        pfTdItemDao.editItemOption(tdItemOption);
                    }
                }
            } catch (Exception e) {
                errorItems.add(itemBach);
            }
        }
        return errorItems;
    }

    @Override
    public List<String> listCdGroup(ItemDto dto) {
        return pfTdItemDao.listCdGroup(dto);
    }

}
