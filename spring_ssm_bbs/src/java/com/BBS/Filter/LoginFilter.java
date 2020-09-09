package com.BBS.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by zlq on 2019/1/27.
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取请求
        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        获取域中的session_User信息
        Object user = request.getSession().getAttribute("session_User");
        //判断session信息
        if (user == null) {
            request.setAttribute("code", "error");
            request.setAttribute("message", "你还没登入，请先登入~");
            request.getRequestDispatcher("/bbs_jsp/user/Login").forward(request, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
