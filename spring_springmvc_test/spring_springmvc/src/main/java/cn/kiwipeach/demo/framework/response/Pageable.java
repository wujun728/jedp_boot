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
package cn.kiwipeach.demo.framework.response;

import cn.kiwipeach.demo.enums.business.BusinessEnums;

import java.util.List;

/**
 * Create Date: 2018/01/16
 * Description: 统一分页返回实体
 *
 * @author Wujun
 */
public class Pageable<T> extends BaseResponse {
    /**
     * 总页数
     */
    private long totalNum = DEFAULT_TOTALNUM;
    /**
     * 分页数据
     */
    private List<T> data;
    /**
     * 当前页码
     */
    private Integer curNo = DEFAULT_PAGENO;
    /**
     * 页码大小
     */
    private Integer pageSize = DEFAULT_PAGESIZE;

    /**
     * 默认构造函数
     */
    public Pageable() {

    }

    /**
     * 基本分页信息初始化，一般用于控制器的方法的入参
     *
     * @param pageNo   当前页码
     * @param pageSize 页码大小
     */
    public Pageable(Integer pageNo, Integer pageSize) {
        super(false);
        this.curNo = pageNo;
        this.pageSize = pageSize;
    }

    /**
     * 封装返回数据参数，一般用于数据封装返回阶段
     *
     * @param pageNo   当前页面
     * @param pageSize 页面大小
     * @param pageData 页面数据
     */
    public Pageable(Integer pageNo, Integer pageSize, List<T> pageData) {
        this(pageNo, pageSize);
        this.data = pageData;
    }

    /**
     * 失败构造
     * @param code 错误代码
     * @param message 错误消息
     */
    public Pageable(String code,String message){
        super(code,message);
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getCurNo() {
        return curNo;
    }

    public void setCurNo(Integer curNo) {
        this.curNo = curNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
