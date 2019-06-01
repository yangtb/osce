package com.osce.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * @ClassName: TdArea
 * @Description: 模板_考场
 * @Author yangtongbin
 * @Date 2019-05-25
 */
@Setter
@Getter
@ToString
public class TdArea implements Serializable {

    private static final long serialVersionUID = 1558791613780L;

    /**
     * 主键
     * 考场ID
     */
    private Long idArea;

    /**
     * 模板ID
     */
    private Long idModel;

    /**
     * 考场名称
     */
    private String naArea;

    /**
     * 1 首次循环半天 2 首次循环一天
     */
    private String sdAreaLoopBegin;

    /**
     * 1 末次循环半天 2 末次循环一天
     */
    private String sdAreaLoopEnd;

}
