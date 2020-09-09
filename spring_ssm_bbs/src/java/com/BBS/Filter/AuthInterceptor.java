package com.BBS.Filter;


import com.BBS.pojo.User;
import com.BBS.utils.AppConstant;
import com.BBS.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhanglq
 * @version 1.0
 * @date 2019/11/21 21:32
 * @info 拦截没有登录的所有请求
 */
public class AuthInterceptor implements HandlerInterceptor {

    //存放允许放行的Url
    private List<String> excludeUrls;
    //存放不允许通过的Url
    private List<String> noPassUrls;


    public AuthInterceptor() {

    }

    private static String getRequestPath(HttpServletRequest request) {
        String requestPath = request.getRequestURI() + "?" + request.getQueryString();
        if (requestPath.contains("&")) {
            requestPath = requestPath.substring(0, requestPath.indexOf("&"));
        }

        requestPath = requestPath.substring(request.getContextPath().length() + 1);
        return requestPath;
    }
    /*
     请求方法前进行拦截URL
     Controller方法处理之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestPath = getRequestPath(request);
        User user;

        //判断是否是配置放行URL
        if (this.isInClude(requestPath)) { //如果是放行的Url
            return true;
        } else {
            HttpSession session = request.getSession();
            if (session.getAttribute(AppConstant.SESSION_USER) != null) {  //如果能找到用户信息
                user = (User) session.getAttribute(AppConstant.SESSION_USER);
                if (user.getId() != null) {  //如果用户ID不为空，则可以返回真，可以访问controller的方法
                    return true;
                } else { //否则重定向到登录页
                    ModelAndView mv = new ModelAndView();
                    this.forword(request,mv);
                    return false;
                }
            } else {    //否则重定向到登录页
                ModelAndView mv = new ModelAndView();
                this.forword(request,mv);
                return false;
            }
        }


    }
    /*
    调用前提：preHandle返回true
    调用时间：Controller方法处理完之后，DispatcherServlet进行视图的渲染之前，也就是说在这个方法中你可以对ModelAndView进行操作
    执行顺序：链式Intercepter情况下，Intercepter按照声明的顺序倒着执行。
    备注：postHandle虽然post打头，但post、get方法都能处理
    这个方法可以在认证成功后为程序设置COOKIE信息。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    /*
    调用前提：preHandle返回true
    调用时间：DispatcherServlet进行视图的渲染之后
    多用于清理资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }




    private boolean isInClude(String requestPath) {
        boolean flag = false;
        List<String> list = this.excludeUrls;

        for (String url : list) {  //判断URL是否与配置的一致
            if (StringUtils.equals(requestPath, url)) {
                flag = true;
            } else {
                int index = url.indexOf("*"); //判断是否星号通配符，表示这个控制器下面的所有URL都要进行拦截
                if (index != -1) { // 返回指定字符在字符串中第一次出现处的索引，如果此字符串中没有这样的字符，则返回 -1。
                    String regex = "^" + url.replace(".", "\\.").replace("/", "\\/").replace("*", ".*") + "$";
                    if (requestPath.matches(regex)) {
                        flag = true;
                    }
                }
            }

            if (flag) {
                break;
            }
        }

        return flag;
    }



    //对于没有通过的跳转到登录页面
    @RequestMapping("/forword")
    public ModelAndView forword(HttpServletRequest request,ModelAndView mv) {
        mv.setViewName("bbs_jsp/user/Login");
        return mv;
    }

    public List<String> getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    public List<String> getNoPassUrls() {
        return noPassUrls;
    }

    public void setNoPassUrls(List<String> noPassUrls) {
        this.noPassUrls = noPassUrls;
    }
}
