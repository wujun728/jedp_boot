package com.BBS.controller;

import com.BBS.pojo.User;
import com.BBS.service.GeneralService;
import com.BBS.service.UserService;
import com.BBS.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dyl on 2018/12/27.
 */
@Controller
public class LoginController {

    private static final String SESSION_USER = "session_User";
    private static final String Message = "message";

    //角色启用状态，1表示启用
    private static final String Status = "1";
    //角色的权限
    private static final String roleid = "1";

    @Autowired
    private UserService userService;

    @Autowired
    private GeneralService generalService;

    //设置日期格式
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

    /*
           登入请求控制器
     */
    @RequestMapping("/Login")
    public ModelAndView Login(ModelAndView modelAndView, HttpServletRequest request,User user,HttpSession session) {

        User session_User = SessionUtils.getUser(request);

        String verifyCode = session.getAttribute("verifyCode").toString().toLowerCase();//获取session中的验证码  不区分大小写

        String vercode = request.getParameter("vercode").toLowerCase();//获取用户提交的验证码

        if (session_User == null || session_User.getId() == null) {
            session_User = userService.findByLoginName(user.getLoginname());
            if (session_User != null) {
                int errcount = 0;
                String pw = user.getPassword();

                //获取用户输入密码错误的时间与信息
                String GetUserError = (String) request.getSession().getAttribute(session_User.getLoginname() + "errrcount" + df.format(new Date()));
                //避免由于第一次输入密码的错误信息拿到的时空指针异常，这里赋值为0
                if (GetUserError == null) {
                    errcount = 0;
                } else {
                    errcount = Integer.valueOf(GetUserError);
                }

                //如果密码错误次数大于N次就不准用户登陆
                if (errcount > 4) {
                    modelAndView.addObject("message", "密码错误次数超过限制，当天无法登陆！");
                    modelAndView.setViewName("bbs_jsp/user/Login");
                    return modelAndView;
                }

                //判断验证码与密码是否正确
                if (session_User.getPassword().equals(pw)) {
                    //校验验证码，暂时不用
                    if (vercode.equals(verifyCode)) {   //匹配验证码是否相等
                        modelAndView.addObject("session_User", session_User);
                        session.setAttribute("session_User", session_User);
                        modelAndView.setViewName("bbs_jsp/index");
                        return modelAndView;
                    } else {
                        modelAndView.addObject("message", "验证码错误请重新输入!");
                        modelAndView.setViewName("bbs_jsp/user/Login");
                        return modelAndView;
                    }

                } else {
                    //初始化错误次数
                    Integer errorcount = 0;
                    modelAndView.addObject("message", "密码输入错误,当天错误5次将无法登陆!");

                    //拿到用户第一次错误的密码信息
                    String FirstError = (String) request.getSession().getAttribute(session_User.getLoginname() + "errorcount" + df.format(new Date()));
                    if (FirstError == null) {
                        errcount = 0;
                    } else {
                        errcount = Integer.valueOf(GetUserError);
                        errcount += 1;
                    }
                    //将错误的信息重新set到Session中
                    request.getSession().setAttribute(session_User.getLoginname() + "_errorcout_" + df.format(new Date()), errorcount.toString());

                    modelAndView.setViewName("bbs_jsp/user/Login");//跳转到登陆页
                    return modelAndView;
                }

            } else {
                modelAndView.addObject("message", "该用户不存在");
                modelAndView.setViewName("bbs_jsp/user/Login");
                return modelAndView;
            }

        } else {
            modelAndView.addObject(SESSION_USER, session_User);
            session.setAttribute("session_User", session_User);
            modelAndView.setViewName("bbs_jsp/index");
            return modelAndView;
        }

    }




    @RequestMapping("/Register")
    public ModelAndView Register(ModelAndView mv, User user,HttpServletRequest request) {

        User setuser = new User();
        String repassword = request.getParameter("repassword");

        //        判断登入名是否包含汉字
//       Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
//       Matcher m = p.matcher(loginname);
//        if (m.find()) {
//
//        } else {
//            mv.addObject(Message, "登入名不允许为汉字");
//            mv.setViewName("bbs_jsp/user/Register");
//            return mv;
//        }

        User session_User = userService.findByLoginName(user.getLoginname());

        //判断两次是否输入一致
        if (repassword.equals(user.getPassword())) {
            //判断该用户名是否已经存在
            if (userService.findByusername(user.getUsername()) != null) {
                mv.addObject(Message, "该用户名已存在，请重新输入！");
                mv.setViewName("bbs_jsp/user/Register");
                return mv;
            }
            if (session_User != null) {
                mv.addObject(Message, "该账号已被注册，请重新输入！");
                mv.setViewName("bbs_jsp/user/Register");
                return mv;
            } else {
                setuser.setUsername(user.getUsername());
                setuser.setPassword(user.getPassword());
                setuser.setLoginname(user.getLoginname());
                setuser.setRoleid(roleid);
                setuser.setStatus(Status);
//                setuser.setId(UUID.randomUUID().);
//                userService.insertUser(setuser);
                try {
                    this.generalService.save(setuser);
                    mv.addObject(Message, "注册成功！请立即登录");
                } catch (Exception e) {
                    e.printStackTrace();
                    mv.addObject(Message, "注册失败！稍后再试");
                    e.getMessage();
                }
                mv.setViewName("bbs_jsp/user/Register");
                return mv;
            }
        } else {
            mv.addObject(Message, "两次输入的密码不一致，请重新输入！");
            mv.setViewName("bbs_jsp/user/Register");
            return mv;
        }



    }

    //退出登录
    @RequestMapping("/seeout")
    public ModelAndView seeout(HttpSession session, ModelAndView modelAndView,HttpServletRequest request)
            throws ServletException, IOException {
//        SessionUtils.setUser(request, null);
        //清空session信息
        session.setAttribute("session_User",null);
        modelAndView.setViewName("bbs_jsp/index");
        return modelAndView;
    }

    //给shiro拦截器，访问使用，当拦截成功后，返回的登录页面
    @RequestMapping("/shiroLogin")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs_jsp/user/Login");
        return modelAndView;
    }

}
