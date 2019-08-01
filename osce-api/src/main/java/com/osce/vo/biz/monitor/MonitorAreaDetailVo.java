package com.osce.vo.biz.monitor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: MonitorAreaDetailVo
 * @Description: 站点监控详情
 * @Author yangtongbin
 * @Date 2019-08-01
 */
@Setter
@Getter
@ToString
public class MonitorAreaDetailVo implements Serializable {

    private static final long serialVersionUID = 883072579390733204L;

    /**
     * 病例ID
     */
    private String idCase;

    /**
     * 评分表ID
     */
    private Long idScoreSheet;

    /**
     * 评分表名称
     */
    private String naScoreSheet;

    /**
     * 站点url
     */
    private String stationQrCodeUrl;

}
