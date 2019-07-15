package com.osce.vo.biz.show;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ShowStationRightVo
 * @Description: 占
 * @Author yangtongbin
 * @Date 2019-07-15
 */
@Setter
@Getter
@ToString
public class ShowStationRightVo implements Serializable {

    private static final long serialVersionUID = 8884676760692508186L;

    /**
     * 等待学员数
     */
    private Integer studentNum;

    /**
     * 房间等待学员
     */
    private List<ShowRoomStudentVo> roomStudents;

    /**
     * 当前时间
     */
    private String nowTime;

}
