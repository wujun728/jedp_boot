package com.wuzy.sys.controller;

import com.wuzy.sys.utils.Result;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台系统登陆Controller
 * @author wuzy
 * 2016年12月8日 下午10:25:21
 */

@Controller
@RequestMapping("/sys")
public class SysLoginController {
    private Logger logger=Logger.getLogger(this.getClass());
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(){
        logger.info("登陆页面：sys/login.jsp");
		return "sys/login";
	}

	 /** 
     * 用户登录 
     */  
    @RequestMapping(value="/login", method=RequestMethod.POST)
    @ResponseBody
    public Result login(HttpServletRequest request ,
                        @RequestParam(value="account",required=true) String account,
                        @RequestParam(value="password",required=true)String password,
                        @RequestParam(value="rememberMe") Boolean rememberMe){
        Result result=new Result();
        String resultPageURL = InternalResourceViewResolver.FORWARD_URL_PREFIX + "/";  
        /*String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberMe");  */
        if(rememberMe==null)rememberMe=false;
        System.out.println("rememberme:::>>"+rememberMe);
        System.out.println("用户[" + account + "]登录时输入的验证码为[----],HttpSession中的验证码为[---]");

        UsernamePasswordToken token = new UsernamePasswordToken(account, password,rememberMe);
        token.setRememberMe(true);  
        System.out.println("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));  

        //获取当前的Subject  
        Subject currentUser = SecurityUtils.getSubject();  

        try {  

            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
            currentUser.login(token);
            System.out.println("对用户[" + account + "]进行登录验证..验证通过");
            resultPageURL = "/sys/index";

            result.setCode(Boolean.FALSE);
        }catch(UnknownAccountException uae){
            System.out.println("对用户[" + account + "]进行登录验证..验证未通过,未知账户");
            request.setAttribute("message_login", "未知账户");
            result.setMessage("未知账户");
        }catch(IncorrectCredentialsException ice){  
            System.out.println("对用户[" + account + "]进行登录验证..验证未通过,错误的凭证");
            request.setAttribute("message_login", "密码不正确");
            result.setMessage("密码不正确");
        }catch(LockedAccountException lae){  
            System.out.println("对用户[" + account + "]进行登录验证..验证未通过,账户已锁定");
            request.setAttribute("message_login", "账户已锁定");
            result.setMessage("账户已锁定");
        }catch(ExcessiveAttemptsException eae){  
            System.out.println("对用户[" + account + "]进行登录验证..验证未通过,错误次数过多");
            request.setAttribute("message_login", "用户名或密码错误次数过多");
            result.setMessage("用户名或密码错误次数过多");
        }catch(AuthenticationException ae){  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            System.out.println("对用户[" + account + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();  
            request.setAttribute("message_login", "用户名或密码不正确");
            result.setMessage("用户名或密码不正确");
        }  

        //验证是否登录成功  
        if(currentUser.isAuthenticated()){  
            System.out.println("用户[" + account + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            result.setCode(Boolean.TRUE);
            result.setMessage("恭喜您，登陆成功");
        }else{
            token.clear();  
        }
        return result;
    }

       

    /** 
     * 用户登出
     * @return 登陆页面
     */  

    @RequestMapping("/logout")  
    public String logout(HttpServletRequest request){  
         SecurityUtils.getSubject().logout();  
         return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";  
    }
    /**
     * 普通登陆用户可访问权限
     * @param request
     * @return
     */
    @RequestMapping(value="/info")  
    public String getUserInfo(HttpServletRequest request){  
        String currentUser = (String)request.getSession().getAttribute("currentUser");  
        System.out.println("当前登录的用户为[" + currentUser + "]");  
        request.setAttribute("currUser", currentUser);  
        return "/sys/info";  
    }  
    /**
     * 需管理员用户才能查看
     * @param request
     * @return
     */
    @RequestMapping(value="/list")  
    public String list(HttpServletRequest request){  
    	String currentUser = (String)request.getSession().getAttribute("currentUser");  
    	System.out.println("当前登录的用户为[" + currentUser + "]");  
    	request.setAttribute("currUser", currentUser);  
    	return "/sys/list";  
    }  
    
    
}
