package com.osce.server.portal.biz.training.res.room;

import com.osce.api.biz.training.res.room.PfRoomService;
import com.osce.dto.biz.training.res.room.RoomDto;
import com.osce.enums.SysDicGroupEnum;
import com.osce.result.PageResult;
import com.osce.server.portal.BaseController;
import com.osce.server.security.CurrentUserUtils;
import com.osce.server.utils.EnumUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @ClassName: PfRoomController
 * @Description: PfSpController
 * @Author yangtongbin
 * @Date 2019-05-08
 */
@Controller
public class PfRoomController extends BaseController {

    @Reference
    private PfRoomService pfRoomService;

    @Resource
    private EnumUtil enumUtil;

    @PreAuthorize("hasAnyRole('ROLE_01_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/room/page")
    public String page() {
        return "pages/biz/training/res/room/roomPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/room/form")
    public String form(String formType, Model model) {
        model.addAttribute("formType", formType);
        return "pages/biz/training/res/room/roomTagForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/room/device/page")
    public String pageDevice() {
        return "pages/biz/training/res/room/devicePage";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_02_001','ROLE_SUPER')")
    @RequestMapping("/pf/p/room/device/form")
    public String formDevice(String formType, Long idRoom, Model model) {
        model.addAttribute("formType", formType);
        model.addAttribute("idRoom", idRoom);
        model.addAttribute("roomDeviceList", enumUtil.getEnumList(SysDicGroupEnum.SD_ROOM_DEVICE_CA.getCode()));
        return "pages/biz/training/res/room/deviceForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_01_02_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/room/list")
    @ResponseBody
    public PageResult pageRooms(RoomDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfRoomService.pageRooms(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_01_02_001','ROLE_SUPER')")
    @RequestMapping(value = "/pf/p/device/list")
    @ResponseBody
    public PageResult pageDevices(RoomDto dto) {
        dto.setIdOrg(CurrentUserUtils.getCurrentUserIdOrg());
        return pfRoomService.pageDevices(dto);
    }

}
