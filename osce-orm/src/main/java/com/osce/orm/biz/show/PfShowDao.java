package com.osce.orm.biz.show;

import com.osce.dto.biz.show.ShowDto;
import com.osce.vo.biz.show.ShowBigScreenDetailVo;
import com.osce.vo.biz.show.ShowBigScreenMainVo;

import java.util.List;

/**
 * @ClassName: PfShowDao
 * @Description: 显示屏
 * @Author yangtongbin
 * @Date 2019-06-30
 */
public interface PfShowDao {

    /**
     * 显示大屏main
     *
     * @param dto
     * @return
     */
    ShowBigScreenMainVo selectBigScreenMain(ShowDto dto);

    /**
     * 显示大屏detail
     *
     * @param dto
     * @return
     */
    List<ShowBigScreenDetailVo> selectBigScreenDetail(ShowDto dto);

    /**
     * 显示大屏总数
     *
     * @param dto
     * @return
     */
     Long countBigScreenDetail(ShowDto dto);

}
