package cn.tycoding.controller;

import cn.tycoding.pojo.Admin;
import cn.tycoding.service.AdminService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 管理人员的Controller层
 *
 * @author TyCoding
 * @date 18-4-27上午7:05
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    /**
     * 注入service
     */
    @Autowired
    private AdminService adminService;


    /**
     * 跳转到系统登录首页
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    /**
     * 登录功能
     */
    @RequestMapping(value = "/login")
    public String login(@RequestParam String a_name, @RequestParam String a_password, Model model, HttpSession session) {
        Admin admin = adminService.login(a_name);
        if (admin != null) {
            if (admin.getA_password().equals(a_password)) {
                session.setAttribute("name", admin.getA_name());
                return "view/page";
            } else {
                model.addAttribute("message", "用户名或密码错误");
                return "view/login/info";
            }
        } else {
            model.addAttribute("message", "登录失败");
            return "view/login/info";
        }
    }

    /**
     * 注册功能
     */
    @RequestMapping(value = "/register")
    public String register(Admin admin, HttpSession session) {
        adminService.insert(admin);
        session.setAttribute("name", admin.getA_name());
        return "view/page";
    }

    /**
     * 退出登录的功能
     */
    @RequestMapping(value = "/outLogin")
    public String outLogin(HttpSession session) {
        session.invalidate();
        return "index";
    }

    /**
     * 跳转到page首页面
     */
    @RequestMapping(value = "/page")
    public String page() {
        return "view/page";
    }

    /**
     * 根据用户名查询
     */
    @ResponseBody
    @RequestMapping(value = "/findByName")
    public String findByName(@RequestBody Admin admin) {
        Admin info = adminService.findByName(admin.getA_name());
        System.out.println(JSONObject.toJSONString(info));
        return JSONObject.toJSONString(info);
    }
}
