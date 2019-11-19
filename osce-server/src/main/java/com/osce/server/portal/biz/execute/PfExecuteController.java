package com.osce.server.portal.biz.execute;

import com.osce.server.portal.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: PfExecuteController
 * @Description: 考试执行
 * @Author yangtongbin
 * @Date 2019-07-08
 */
@Controller
public class PfExecuteController extends BaseController {

    /**
     * 理论考试首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/m/exec/main")
    public String execMain(Model model, String idRoom, String idOrg) {
        model.addAttribute("idRoom", idRoom);
        model.addAttribute("idOrg", idOrg);
        return "pages/biz/execute/execMain";
    }

    /**
     * 理论考试
     *
     * @param model
     * @return
     */
    @RequestMapping("/m/exec/test")
    public String execTest(Model model, String idExecQueue) {
        model.addAttribute("idExecQueue", idExecQueue);
        return "pages/biz/execute/execTest";
    }

}
