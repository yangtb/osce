package com.osce.vo.user.menu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class PfBaseMenuVo implements Serializable {

    private static final long serialVersionUID = 1582588956094352246L;

    private Long menuId;     // 菜单ID
    private String url;        // 菜单url
    private String name;       // 菜单名称
    private String img;        // 图片
    private String position;   // 菜单位置left=左边，top=顶部
    private String target;      // 菜单打开方式

    private String code;

    private String parentCode;

    /**
     * 菜单list
     */
    private List<PfBaseMenuVo> children;

}
