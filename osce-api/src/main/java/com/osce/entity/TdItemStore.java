package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: TdItemStore
 * @Description: 模板_题集管理
 * @Author yangtongbin
 * @Date 2019-06-08
 */
@Setter
@Getter
@ToString
public class TdItemStore implements Serializable {

    private static final long serialVersionUID = 1559975708033L;

    /**
     * 主键
     * 题集ID
     */
    private Long idItemStore;

    /**
     * 模板ID
     */
    private Long idModel;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 来源题集ID
     */
    private Long idItemStoreFrom;

    /**
     * 来源题集名称
     */
    private String naItemStoreFrom;

    /**
     * 题集名称
     */
    private String naItemStore;

    /**
     * 及格分数
     */
    private Integer scorePass;

    /**
     * 题库试题标志
     */
    private String fgItemFromPublic;

    /**
     * 私有试题标志
     */
    private String fgItemFromPrivate;

    /**
     * 导入试题标志
     */
    private String fgItemFromImport;

    /**
     * 题集描述
     */
    private String desItemStore;

    /**
     * 排除历史已选
     */
    private String fgChooseHist;

    /**
     * 0 未激活 1 已激活
     */
    private String fgActive;

    /**
     * 0 正常 1 删除
     */
    private String fgValid;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建人员
     */
    private String creator;

    /**
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 修改人员
     */
    private String operator;

    /**
     * 修改时间
     */
    private String gmtModify;


}
