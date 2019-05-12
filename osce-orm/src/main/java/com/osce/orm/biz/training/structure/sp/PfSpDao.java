package com.osce.orm.biz.training.structure.sp;

import com.osce.dto.biz.training.structure.sp.SpDto;
import com.osce.dto.common.PfBachChangeStatusDto;
import com.osce.entity.OrgSpDefine;
import com.osce.entity.OrgUserSp;
import com.osce.vo.biz.training.structure.sp.SpVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: PfSpDao
 * @Description: sp管理
 * @Author yangtongbin
 * @Date 2019-05-06
 */
public interface PfSpDao {

    /**
     * sp 列表
     *
     * @param dto
     * @return
     */
    List<SpVo> listSp(SpDto dto);

    /**
     * 获取总数
     *
     * @param dto
     * @return
     */
    Long countSp(SpDto dto);

    /**
     * 新增
     *
     * @param list
     * @param usId
     * @return
     */
    int addSp(@Param("list") List<OrgUserSp> list,
              @Param("usId") Long usId);

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    int delSp(PfBachChangeStatusDto dto);

    /**
     * 根据用户id删除sp
     *
     * @param usId
     * @return
     */
    int delSpByusId(@Param("usId") Long usId);

    /**
     * SP标签value
     *
     * @param userId 用户id
     * @return
     */
    List<Map<Long, String>> listSpTagValue(@Param("userId") Long userId);

    /**
     * 标签列表
     *
     * @param idOrg 机构id
     * @return
     */
    List<OrgSpDefine> listSpTag(@Param("idOrg") Long idOrg);

    /**
     * add 标签
     *
     * @param dto
     * @return
     */
    int addSpTag(OrgSpDefine dto);

    /**
     * 删除标签
     *
     * @param dto
     * @return
     */
    int delSpTag(PfBachChangeStatusDto dto);

}
