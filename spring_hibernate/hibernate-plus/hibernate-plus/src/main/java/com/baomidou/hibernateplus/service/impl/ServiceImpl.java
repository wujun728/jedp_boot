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
package com.baomidou.hibernateplus.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.hibernateplus.condition.wrapper.Wrapper;
import com.baomidou.hibernateplus.converter.BeanConverter;
import com.baomidou.hibernateplus.dao.IDao;
import com.baomidou.hibernateplus.entity.Convert;
import com.baomidou.hibernateplus.entity.page.Page;
import com.baomidou.hibernateplus.service.IService;
import com.baomidou.hibernateplus.utils.ReflectionKit;

/**
 * <p>
 * IService 实现类（ 泛型：P 是数据库实体PO，V 对应数据库实体的VO ）
 * </p>
 *
 * @author Caratacus
 * @date 2016-11-23
 */
public class ServiceImpl<P extends Convert, V extends Convert> implements IService<V> {

    private static final Logger logger = Logger.getLogger(ServiceImpl.class);

    @Autowired
    protected IDao<P> baseDao;

    @Override
    public V get(Serializable id) {
        P p = baseDao.get(id);
        return p == null ? null : p.convert(voClass());
    }

    @Override
    public V save(V vo) {
        return baseDao.save(vo.convert(poClass())).convert(voClass());
    }

    @Override
    public V saveOrUpdate(V vo) {
        return baseDao.saveOrUpdate(vo.convert(poClass())).convert(voClass());
    }

    @Override
    public V update(V vo) {
        return baseDao.update(vo.convert(poClass())).convert(voClass());
    }

    @Override
    public boolean update(Wrapper wrapper) {
        return retBool(baseDao.update(wrapper));
    }

    @Override
    public boolean delete(V vo) {
        return baseDao.delete(vo.convert(poClass()));
    }

    @Override
    public boolean delete(Serializable id) {
        return retBool(baseDao.delete(id));
    }

    @Override
    public boolean delete(Wrapper wrapper) {
        return retBool(baseDao.delete(wrapper));
    }

    @Override
    public boolean insertBatch(List<V> list) {
        return baseDao.insertBatch(BeanConverter.convert(poClass(), list), 30);
    }

    @Override
    public boolean insertBatch(List<V> list, int size) {
        return baseDao.insertBatch(BeanConverter.convert(poClass(), list), size);
    }

    @Override
    public boolean updateBatch(List<V> list) {
        return baseDao.updateBatch(BeanConverter.convert(poClass(), list), 30);
    }

    @Override
    public boolean updateBatch(List<V> list, int size) {
        return baseDao.updateBatch(BeanConverter.convert(poClass(), list), size);
    }

    @Override
    public boolean saveOrUpdateBatch(List<V> list) {
        return baseDao.saveOrUpdateBatch(BeanConverter.convert(poClass(), list), 30);
    }

    @Override
    public boolean saveOrUpdateBatch(List<V> list, int size) {
        return baseDao.saveOrUpdateBatch(BeanConverter.convert(poClass(), list), size);
    }

    @Override
    public V selectOne(Wrapper wrapper) {
        P p = baseDao.selectOne(wrapper);
        return p == null ? null : p.convert(voClass());
    }

    @Override
    public List<V> selectList(Wrapper wrapper) {
        return BeanConverter.convert(voClass(), baseDao.selectList(wrapper));
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper wrapper) {
        return baseDao.selectMaps(wrapper);
    }

    @Override
    public int selectCount(Wrapper wrapper) {
        return baseDao.selectCount(wrapper);
    }

    @Override
    public Page<V> selectPage(Wrapper wrapper, Page<V> page) {
        page = baseDao.selectPage(wrapper, page);
        page.setRecords(BeanConverter.convert(voClass(), page.getRecords()));
        return page;
    }

    @Override
    public Page<Map<String, Object>> selectMapsPage(Wrapper wrapper, Page<Map<String, Object>> page) {
        return baseDao.selectMapsPage(wrapper, page);
    }

    /**
     * 判断数据库操作是否成功
     *
     * @param result
     *            数据库操作返回影响条数
     * @return boolean
     */
    protected boolean retBool(int result) {
        return result >= 1;
    }

    /**
     * 获取toClass
     *
     * @return
     */
    protected Class<P> poClass() {
        return ReflectionKit.getSuperClassGenricType(getClass(), 0);
    }

    /**
     * 获取voClass
     *
     * @return
     */
    protected Class<V> voClass() {
        return ReflectionKit.getSuperClassGenricType(getClass(), 1);
    }

}
