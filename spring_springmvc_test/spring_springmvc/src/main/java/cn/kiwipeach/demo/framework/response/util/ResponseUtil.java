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
package cn.kiwipeach.demo.framework.response.util;

import cn.kiwipeach.demo.enums.business.BusinessEnums;
import cn.kiwipeach.demo.framework.response.MulityResponse;
import cn.kiwipeach.demo.framework.response.Pageable;
import cn.kiwipeach.demo.framework.response.SimpleResponse;

import java.util.List;

/**
 * Create Date: 2018/01/25
 * Description: 数据统一返回工具类
 *
 * @author kiwipeach [1099501218@qq.com]
 */
public class ResponseUtil {


    /**
     * 静态成功方法
     *
     * @param data 数据报文
     * @return 返回信息
     */
    public static <T> SimpleResponse<T> simpleSuccess(T data) {
        return new SimpleResponse<T>(data);
    }


    /**
     * 静态失败方法1
     *
     * @param code    数据报文
     * @param message 数据报文
     * @return 返回信息
     */
    public static <T> SimpleResponse<T> simpleFail(String code, String message) {
        return new SimpleResponse<T>(code, message);
    }

    /**
     * 静态失败方法2
     *
     * @param codeEnum 错误枚举信息
     * @return 返回信息
     */
    public static <T> SimpleResponse<T> simpleFail(BusinessEnums codeEnum) {
        return new SimpleResponse<T>(codeEnum.getCode(), codeEnum.getMessage());
    }


    /**
     * 静态成功方法
     *
     * @param data 数据报文
     * @return 返回信息
     */
    public static <T> MulityResponse<T> mulitySuccess(List<T> data) {
        return new MulityResponse<T>(data);
    }


    /**
     * 静态失败方法1
     *
     * @param code    数据报文
     * @param message 数据报文
     * @return 返回信息
     */
    public static <T> MulityResponse<T> mulityFail(String code, String message) {
        return new MulityResponse<T>(code, message);
    }

    /**
     * 静态失败方法2
     *
     * @param codeEnum 错误枚举信息
     * @return 返回信息
     */
    public static <T> MulityResponse<T> mulityFail(BusinessEnums codeEnum) {
        return new MulityResponse<T>(codeEnum.getCode(), codeEnum.getMessage());
    }


    /**
     * 成功的静态方法
     *
     * @param pageNo   当前页面
     * @param pageSize 页面大小
     * @param pageData 页面数据
     * @param <T>      页面数据泛型
     * @return 返回成功状态的分页数据
     */
    public static <T> Pageable<T> pageSuccess(Integer pageNo, Integer pageSize, List<T> pageData) {
        return new Pageable<>(pageNo, pageSize, pageData);
    }

    /**
     * 失败构造1
     *
     * @param code    错误代码
     * @param message 错误消息
     * @param <T>     错误返回数据泛型
     * @return 错误的分页数据信息
     */
    public static <T> Pageable<T> pageFail(String code, String message) {
        return new Pageable<T>(code, message);
    }

    /**
     * 失败构造2
     *
     * @param businessEnums 错误枚举信息
     * @param <T>           错误返回数据泛型
     * @return 错误的分页数据信息
     */
    public static <T> Pageable<T> pageFail(BusinessEnums businessEnums) {
        return new Pageable<T>(businessEnums.getCode(), businessEnums.getMessage());
    }

}
