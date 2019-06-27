package com.osce.service.system.dic;

import com.osce.api.system.dic.PfDicService;
import com.osce.dto.system.dic.PfDicDto;
import com.osce.entity.SysDictionary;
import com.osce.enums.SysEnum;
import com.osce.orm.system.dic.PfDicDao;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfDicServiceImpl implements PfDicService {

    @Resource
    private PfDicDao pfDicDao;

    @Override
    public Long countDicGroup(PfDicDto dto) {
        return pfDicDao.countDicGroup(dto);
    }

    @Override
    public List<SysDictionary> listDicGroups(PfDicDto dto) {
        return pfDicDao.listDicGroups(dto);
    }

    @Override
    public List<SysDictionary> listEnums(PfDicDto dto) {
        return pfDicDao.listEnums(dto);
    }

    @Override
    public Long countEnum(PfDicDto dto) {
        return pfDicDao.countEnum(dto);
    }

    @Override
    public List<SysDictionary> listAllEnums() {
        return pfDicDao.listAllEnums();
    }

    @Override
    public boolean isExistDic(String dictCode) {
        return pfDicDao.isExistDic(dictCode) >= 1 ? true : false;
    }

    @Override
    public boolean addDic(SysDictionary dto) {
        dto.setGroupName(SysEnum.DICTIONARY_GROUP.getDesc());
        dto.setGroupCode(SysEnum.DICTIONARY_GROUP.getCode());
        return pfDicDao.addDic(dto) == 1 ? true : false;
    }

    @Override
    public boolean editDic(SysDictionary dto) {
        Integer num;
        SysDictionary oldDic = pfDicDao.selectDicInfoById(dto.getId());
        num = pfDicDao.editDic(dto);
        if (!oldDic.getDictCode().equals(dto.getDictCode())) {
            // 更新字典所属group
            pfDicDao.updateDicGroup(oldDic.getDictCode(), dto.getDictCode());
        }
        return num == 1 ? true : false;
    }

    @Override
    public boolean delDic(List<Long> list) {
        return pfDicDao.delDic(list) >= 1 ? true : false;
    }

    @Override
    public boolean addEnum(SysDictionary dto) {
        if (dto.getId() == null) {
            return pfDicDao.addDic(dto) == 1 ? true : false;
        } else {
            return pfDicDao.editDic(dto) == 1 ? true : false;
        }
    }

    @Override
    public boolean isExistEnum(String dictCode, String groupCode) {
        return pfDicDao.isExistEnum(dictCode, groupCode) >= 1 ? true : false;
    }

}
