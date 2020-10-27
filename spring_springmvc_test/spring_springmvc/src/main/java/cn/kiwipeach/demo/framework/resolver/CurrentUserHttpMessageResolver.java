/*
 * Copyright 2017 KiWiPeach.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.kiwipeach.demo.framework.resolver;

import cn.kiwipeach.demo.domain.Employ;
import cn.kiwipeach.demo.framework.annotations.CurrentUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Create Date: 2018/01/18
 * Description: 用户信息解析器，在控制器中添加此注解自动把当前系统登录用户信息注入进来
 *
 * @author kiwipeach [1099501218@qq.com]
 */
public class CurrentUserHttpMessageResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        CurrentUser currentUserAnnotation = parameter.getParameterAnnotation(CurrentUser.class);
        //从session的scope里取CurrentUser注解里的value属性值的key的value
        Employ employ = new Employ();
        employ.setEmpno(new BigDecimal(5678));
        employ.setEname(currentUserAnnotation.value());
        employ.setJob("无业游民");
        employ.setHiredate(new Date());
        return employ;
    }
}
