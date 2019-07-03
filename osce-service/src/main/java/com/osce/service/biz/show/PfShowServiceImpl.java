package com.osce.service.biz.show;

import com.osce.api.biz.show.PfShowService;
import com.osce.dto.biz.show.ShowDto;
import com.osce.orm.biz.show.PfShowDao;
import com.osce.vo.biz.show.ShowBigScreenDetailVo;
import com.osce.vo.biz.show.ShowBigScreenMainVo;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PfShowServiceImpl
 * @Description: 显示屏
 * @Author yangtongbin
 * @Date 2019-06-30
 */
@Service
public class PfShowServiceImpl implements PfShowService {

    @Resource
    private PfShowDao pfShowDao;

    @Override
    public ShowBigScreenMainVo selectBigScreenMain(ShowDto dto) {
        ShowBigScreenMainVo showBigScreenMainVo = pfShowDao.selectBigScreenMain(dto);
        dto.setIdPlan(showBigScreenMainVo.getIdPlan());
        dto.setIdArea(showBigScreenMainVo.getIdArea());
        dto.setTimeSection(showBigScreenMainVo.getTimeSection());
        if (showBigScreenMainVo.getIdPlan() != null) {
            showBigScreenMainVo.setItemNum(pfShowDao.countBigScreenDetail(dto));
        } else {
            showBigScreenMainVo.setItemNum(0L);
        }
        return showBigScreenMainVo;
    }

    @Override
    public List<ShowBigScreenDetailVo> selectBigScreenDetail(ShowDto dto) {
        return pfShowDao.selectBigScreenDetail(dto);
    }
}
