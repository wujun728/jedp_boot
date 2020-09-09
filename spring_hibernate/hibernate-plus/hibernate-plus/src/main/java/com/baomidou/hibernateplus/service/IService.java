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
package com.baomidou.hibernateplus.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.baomidou.hibernateplus.condition.wrapper.Wrapper;
import com.baomidou.hibernateplus.entity.Convert;
import com.baomidou.hibernateplus.entity.page.Page;

/**
 * <p>
 * IService接口类
 * </p>
 *
 * @author Caratacus
 * @date 2016-11-23
 */
public interface IService<V extends Convert> {

    /**
     * 根据主键获取对象
     *
     * @param id
     * @return
     */
    V get(Serializable id);

    /**
     * 保存
     *
     * @param vo
     * @return
     */
    V save(V vo);

    /**
     * 保存或修改
     *
     * @param vo
     */
    V saveOrUpdate(V vo);

    /**
     * 修改
     *
     * @param vo
     */
    V update(V vo);

    /**
     * 根据Wrapper修改
     *
     * @param wrapper
     * @return
     */
    boolean update(Wrapper wrapper);

    /**
     * 删除
     *
     * @param vo
     */
    boolean delete(V vo);

    /**
     * 根据主键删除
     *
     * @param id
     */
    boolean delete(Serializable id);

    /**
     * 根据Wrapper删除
     *
     * @param wrapper
     * @return
     */
    boolean delete(Wrapper wrapper);

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    boolean insertBatch(List<V> list);

    /**
     * 批量插入
     *
     * @param list
     * @param size
     * @return
     */
    boolean insertBatch(List<V> list, int size);

    /**
     * 批量修改
     *
     * @param list
     * @return
     */
    boolean updateBatch(List<V> list);

    /**
     * 批量修改
     *
     * @param list
     * @param size
     * @return
     */
    boolean updateBatch(List<V> list, int size);

    /**
     * 批量新增或修改
     *
     * @param list
     * @return
     */
    boolean saveOrUpdateBatch(List<V> list);

    /**
     * 批量新增或修改
     *
     * @param list
     * @param size
     * @return
     */
    boolean saveOrUpdateBatch(List<V> list, int size);

    /**
     * 获取单个对象
     *
     * @param wrapper
     * @return
     */
    V selectOne(Wrapper wrapper);

    /**
     * 查询列表
     *
     * @param wrapper
     * @return
     */
    List<V> selectList(Wrapper wrapper);

    /**
     * 查询列表
     *
     * @param wrapper
     * @return
     */
    List<Map<String, Object>> selectMaps(Wrapper wrapper);

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
    Page<V> selectPage(Wrapper wrapper, Page<V> page);

    /**
     * 查询分页
     *
     * @param wrapper
     * @param page
     * @return
     */
    Page<Map<String, Object>> selectMapsPage(Wrapper wrapper, Page<Map<String, Object>> page);

}
