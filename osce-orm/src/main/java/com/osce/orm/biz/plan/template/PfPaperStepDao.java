package com.osce.orm.biz.plan.template;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: PfPaperStepDao
 * @Description: 试卷设计步骤
 * @Author yangtongbin
 * @Date 2019-08-24
 */
public interface PfPaperStepDao {

    /**
     * 第4步
     *
     * @param idItemStore 题集id
     * @return
     */
    boolean isExistTdItem4(@Param("idItemStore") Long idItemStore);

    /**
     * 第3步
     *
     * @param idItemStore 题集id
     * @return
     */
    boolean isExistTdItem3(@Param("idItemStore") Long idItemStore);

    /**
     * 第2步
     *
     * @param idItemStore 题集id
     * @return
     */
    boolean isExistTdItem2(@Param("idItemStore") Long idItemStore);

}
