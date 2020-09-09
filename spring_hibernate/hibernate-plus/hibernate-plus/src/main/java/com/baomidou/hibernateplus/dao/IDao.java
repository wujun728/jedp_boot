/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2016 Caratacus
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.baomidou.hibernateplus.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.baomidou.hibernateplus.condition.wrapper.Wrapper;
import com.baomidou.hibernateplus.entity.page.Page;

/**
 * <p>
 * IDao接口
 * </p>
 *
 * @author Caratacus
 * @date 2016-11-23
 */
public interface IDao<P> {

    /**
     * 根据id获取对象
     *
     * @param id
     * @return
     */
    P get(Serializable id);

    /**
     * 保存方法
     *
     * @param p
     * @return
     */
    P save(P p);

    /**
     * 保存/修改方法
     *
     * @param p
     */
    P saveOrUpdate(P p);

    /**
     * 修改方法
     *
     * @param p
     */
    P update(P p);

    /**
     * 修改方法
     *
     * @param wrapper
     * @return
     */
    int update(Wrapper wrapper);

    /**
     * 删除方法
     *
     * @param p
     */
    boolean delete(P p);

    /**
     * 删除方法
     *
     * @param wrapper
     * @return
     */
    int delete(Wrapper wrapper);

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int delete(Serializable id);

    /**
     * 批量添加
     *
     * @param list
     * @param size
     * @return
     */
    boolean insertBatch(List<P> list, int size);

    /**
     * 批量修改
     *
     * @param list
     * @param size
     * @return
     */
    boolean updateBatch(List<P> list, int size);

    /**
     * 批量修改
     *
     * @param list
     * @param size
     * @return
     */
    boolean saveOrUpdateBatch(List<P> list, int size);

    /**
     * 查询List<Map<String,Object>>结果集
     *
     * @param wrapper
     * @return
     */
    List<Map<String, Object>> selectMaps(Wrapper wrapper);

    /**
     * 根据Wrapper查询单个对象
     *
     * @param wrapper
     * @return
     */
    P selectOne(Wrapper wrapper);

    /**
     * 查询列表
     *
     * @param wrapper
     * @param <P>
     * @return
     */
    <P> List<P> selectList(Wrapper wrapper);

    /**
     * 查询数量
     *
     * @param wrapper
     * @return
     */
    int selectCount(Wrapper wrapper);

    /**
     * 查询分页
     *
     * @param wrapper
     * @param page
     * @return
     */
    Page<Map<String, Object>> selectMapsPage(Wrapper wrapper, Page<Map<String, Object>> page);

    /**
     * 查询分页
     *
     * @param wrapper
     * @param page
     * @return
     */
    Page selectPage(Wrapper wrapper, Page page);

}