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
package cn.kiwipeach.demo.framework.aspect;

import cn.kiwipeach.demo.framework.response.Pageable;
import cn.kiwipeach.demo.framework.response.BaseResponse;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Create Date: 2018/01/17
 * Description: Mybatis自动分页切面
 *
 * @author Wujun
 */
@Component
public class MybatisPageableAspect {
    private final static Logger logger = LoggerFactory.getLogger(MybatisPageableAspect.class);
    /**
     * 当前切点对象
     */
    private JoinPoint joinPoint;
    /**
     * 分页方法中的入参分页对象
     */
    private Pageable<Object> pageable;
    /**
     * 分页插件中的临时分页对象
     */
    private Page<Object> pageHelper;

    /**
     * 是否支持分页
     */
    private boolean isPageable = false;

    /**
     * @param joinPoint 切点信息参数
     */
    public void beforedMethod(JoinPoint joinPoint) {
        this.joinPoint = joinPoint;
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        Class targetClass = signature.getDeclaringType();
        Method targetMethod = ((MethodSignature) signature).getMethod();
        for (Object arg : args) {
            //检测是否有分页对象
            if (arg instanceof Pageable) {
                pageable = (Pageable<Object>) arg;
                int pageNo = pageable.getCurNo();
                int pageSize = pageable.getPageSize();
                pageHelper = PageHelper.startPage(pageNo, pageSize);
                isPageable = true;
                logger.debug("{}.{}:开始设置分页大小==>{pageNo:{},pageSize:{}}", targetClass.getName(), targetMethod.getName(), pageNo, pageSize);
                break;
            } else {
                logger.debug("{}.{}:开始设置分页参数==>{}", targetClass.getName(), targetMethod.getName(), JSON.toJSONString(arg));
            }
        }
    }

    public void afterMethod() {
        //TODO 可自定义处理
        logger.debug("MybatisPageableAspect afterMethod....");
    }

    public void afterReturnMethod(Object result) {
        //获取目标方法对象
        Signature signature = joinPoint.getSignature();
        Class targetClass = signature.getDeclaringType();
        Method targetMethod = ((MethodSignature) signature).getMethod();
        //注入分页查询出来的总记录、页面大小、当前页、分页数据信息。
        if (isPageable) {
            List realPageResponse = (List) result;
            pageable.setTotalNum(pageHelper.getTotal());
            pageable.setCurNo(pageHelper.getPageNum());
            pageable.setPageSize(pageHelper.getPageSize());
            pageable.setData(realPageResponse);
            pageable.setSuccess(true);
            pageable.setCode(BaseResponse.DEFAULT_SUCCESS_VALUE);
            pageable.setMessage(BaseResponse.DEFAULT_SUCCESS_MESSAGE);
        }
        logger.debug("{}.{}:分页结束数据返回==>{}", targetClass.getName(), targetMethod.getName(), JSON.toJSONString(result));
    }

    public void afterThrowMethod(Exception ex) {
        //TODO 可自定义处理
        logger.debug("MybatisPageableAspect afterThrowMethod....");
    }
}
