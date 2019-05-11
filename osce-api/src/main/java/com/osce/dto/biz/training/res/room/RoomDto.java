package com.osce.dto.biz.training.res.room;

import com.osce.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: OrgGrade
 * @Description: 房间管理
 * @Author yangtongbin
 * @Date 2019-05-04
 */
@Setter
@Getter
@ToString
public class RoomDto extends PageParam implements Serializable {

    private static final long serialVersionUID = 1556984315086L;

    /**
     * 机构id
     */
    private Long idOrg;

}
