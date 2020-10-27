package zw.cc.cn.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zwz
 * 2016/8/18.
 * zw.cc.cn.controller
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {

        return "buss/home";
    }


    @RequestMapping("/rolePage")
    @ResponseBody
    public boolean rolePage() {
        Subject currentUser = SecurityUtils.getSubject();
        return currentUser.hasRole("ROOT");
    }

    @RequestMapping("/premissPage")
    @ResponseBody
    public boolean premissPage() {
        Subject currentUser = SecurityUtils.getSubject();
        return currentUser.isPermitted("U_DELETE");
    }

    @RequestMapping("/premissDeletePage")
    @ResponseBody
    public boolean premissDeletePage() {
        Subject currentUser = SecurityUtils.getSubject();
        return currentUser.isPermitted("P_DELETE");
    }
}
