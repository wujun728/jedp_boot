package zw.cc.cn.controller;

import common.util.Password;
import common.variables.exception.ExceVariables;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zw.cc.cn.service.UserService;
import zw.cc.cn.user.module.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by wiggins on 2016/7/11.
 */
@Controller
@RequestMapping("/acc")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult loginpage(String username, String password, boolean remember,  HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        BaseResult baseResult = new BaseResult();
        if (subject.isAuthenticated()) {
            return baseResult;
        }
        try {
            if(username == null || password == null){
                baseResult.addResult(ExceVariables.NOT_EXIST_ACCOUNT);
            }else {
                UsernamePasswordToken token = new UsernamePasswordToken(username, Password.md5(password));
                //是否勾选记住我
                token.setRememberMe(remember);
                subject.login(token);
                User activeUser = (User) subject.getPrincipal();
                subject.getSession().setAttribute("user", activeUser);
            }
        } catch (UnknownAccountException e) {
            baseResult.addResult(ExceVariables.NOT_EXIST_ACCOUNT);
        } catch (IncorrectCredentialsException e) {
            baseResult.addResult(ExceVariables.NAME_PWD_ERROR);
        } catch (LockedAccountException e) {
            baseResult.addResult(ExceVariables.LOCKED_ACCOUNT);
        } catch (ExcessiveAttemptsException e) {
            baseResult.addResult(ExceVariables.TRY_TOO_MUCH);
        } catch (ExpiredCredentialsException e) {
            baseResult.addResult(ExceVariables.CERTIFICATE_EXPIRED);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.addResult(ExceVariables.UNKNOWN_ERROR);
        }
        String url = (String) request.getAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE);
        System.err.println(url);
        return baseResult;
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return "redirect:/";
    }
}
