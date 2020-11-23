package com.youmeek.ssm.service;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Wujun
 *         date 2016/5/4.
 */
public class MyMultipartResolver extends CommonsMultipartResolver {

    @Override
    public boolean isMultipart(HttpServletRequest request) {
        String dir = request.getParameter("dir");
        if(dir!=null){  // kindeditor 上传图片的时候 不进行request 的转换
            return false;
        }
        return super.isMultipart(request);
    }
}
