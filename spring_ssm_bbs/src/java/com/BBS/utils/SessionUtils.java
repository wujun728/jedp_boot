package com.BBS.utils;

import com.BBS.pojo.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dyl on 2018/12/6.
 */
public final class SessionUtils {
    protected static final Logger logger = Logger.getLogger(SessionUtils.class);


    /**
     * 设置session的值
     *
     * @param request
     * @param Key
     * @param value
     */

    public static void setAttr(HttpServletRequest request, String Key, Object value) {
        request.getSession(true).setAttribute(Key, value);
    }


    /**
     * 获取session的值
     *
     * @param request
     * @param Key
     */

    public static void getAttr(HttpServletRequest request, String Key) {

        request.getSession(true).getAttribute(Key);
    }

    /**
     * 删除Session值
     *
     * @param request
     * @param Key
     */
    public static void removeAttr(HttpServletRequest request, String Key) {

        request.getSession().removeAttribute(Key);

    }

    /**
     * 设置用户信息 到session
     *
     * @param request
     * @param user
     */

    public static void setUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute(AppConstant.SESSION_USER,user);
    }

    /**
     * 从session中获取用户信息
     *
     * @param request
     * @return SysUser
     */
    public static User getUser(HttpServletRequest request) {
        return (User) request.getSession(true).getAttribute(AppConstant.SESSION_USER);
    }
    /**
     * 从session中获取用户信息
     * @param request
     * @return SysUser
     */
    public static String getUserId(HttpServletRequest request) {
        User user = getUser(request);
        if (user != null) {
            return user.getLoginname();
        }
        return null;
    }
    /**
     * 从session中获取用户信息
     * @param request
     * @return SysUser
     */
    public static void removeUser(HttpServletRequest request){
        removeAttr(request, AppConstant.SESSION_USER);
    }


}
