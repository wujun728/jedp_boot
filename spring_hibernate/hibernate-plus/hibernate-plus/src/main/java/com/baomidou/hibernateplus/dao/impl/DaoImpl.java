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
package com.baomidou.hibernateplus.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.jboss.logging.Logger;

import com.baomidou.hibernateplus.condition.SelectWrapper;
import com.baomidou.hibernateplus.condition.wrapper.Wrapper;
import com.baomidou.hibernateplus.dao.IDao;
import com.baomidou.hibernateplus.entity.Convert;
import com.baomidou.hibernateplus.entity.page.Page;
import com.baomidou.hibernateplus.utils.Assert;
import com.baomidou.hibernateplus.utils.CollectionUtils;
import com.baomidou.hibernateplus.utils.EntityInfoUtils;
import com.baomidou.hibernateplus.utils.HibernateUtils;
import com.baomidou.hibernateplus.utils.MapUtils;
import com.baomidou.hibernateplus.utils.RandomUtils;
import com.baomidou.hibernateplus.utils.ReflectionKit;
import com.baomidou.hibernateplus.utils.SqlUtils;
import com.baomidou.hibernateplus.utils.StringUtils;

/**
 * <p>
 * IDao接口实现类
 * </p>
 *
 * @author Caratacus
 * @date 2016-11-23
 */
public class DaoImpl<P extends Convert> implements IDao<P> {

    private static final Logger logger = Logger.getLogger(DaoImpl.class);

    /**
     * 获取masterSession
     *
     * @return
     */
    protected SessionFactory masterSession() {
        return EntityInfoUtils.getEntityInfo(poClass()).getMaster();
    }

    /**
     * 获取slaveSession
     *
     * @return
     */
    protected SessionFactory slaveSession() {
        Set<SessionFactory> slaves = EntityInfoUtils.getEntityInfo(poClass()).getSlaves();
        if (CollectionUtils.isEmpty(slaves)) {
            return masterSession();
        }
        return RandomUtils.getRandomElement(slaves);
    }

    /**
     * 原生hibernate根据主键获取对象
     *
     * @param id
     * @return
     */
    public P selectById(Serializable id) {
        Assert.notNull(id);
        return (P) HibernateUtils.getSession(slaveSession(), isCurrent()).get(poClass(), id);
    }

    @Override
    public P get(Serializable id) {
        Assert.notNull(id);
        String primaryKey = SqlUtils.getPrimaryKey(poClass());
        Wrapper wrapper = SelectWrapper.instance().eq(primaryKey, id);
        return selectOne(wrapper);
    }

    /**
     * 根据HQL查询单个对象
     *
     * @param hql
     * @return
     */
    protected P get(String hql) {
        return (P) get(hql, Collections.EMPTY_MAP);
    }

    /**
     * 根据HQL查询单个对象
     *
     * @param hql
     * @param params
     * @return
     */
    protected P get(String hql, Map<String, Object> params) {
        Assert.hasLength(hql);
        P t = null;
        try {
            Query query = HibernateUtils.getHqlQuery(hql, slaveSession(), isCurrent());
            setParamMap(params, query);
            t = (P) query.uniqueResult();
        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
        }
        return t;

    }

    @Override
    public P save(P p) {
        Assert.notNull(p);
        HibernateUtils.getSession(masterSession(), isCurrent()).save(p);
        return p;
    }

    @Override
    public P saveOrUpdate(P p) {
        Assert.notNull(p);
        HibernateUtils.getSession(masterSession(), isCurrent()).saveOrUpdate(p);
        return p;
    }

    @Override
    public P update(P p) {
        Assert.notNull(p);
        HibernateUtils.getSession(masterSession(), isCurrent()).merge(p);
        return p;
    }

    @Override
    public int update(Wrapper wrapper) {
        Assert.notEmpty(wrapper.getSetMap());
        String sqlUpdate = SqlUtils.sqlUpdate(poClass(), wrapper);
        return executeSqlUpdate(sqlUpdate);
    }

    @Override
    public boolean delete(P p) {
        Assert.notNull(p);
        try {
            HibernateUtils.getSession(masterSession(), isCurrent()).delete(p);
        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
            return false;
        }
        return true;
    }

    @Override
    public int delete(Wrapper wrapper) {
        String sqlDelete = SqlUtils.sqlDelete(poClass(), wrapper);
        return executeSqlUpdate(sqlDelete);
    }

    @Override
    public int delete(Serializable id) {
        Assert.notNull(id);
        String sqlDelete = SqlUtils.sqlDelete(poClass(), id);
        return executeSqlUpdate(sqlDelete);
    }

    @Override
    public boolean insertBatch(List<P> list, int size) {
        Assert.notEmpty(list);
        try {
            Session session = HibernateUtils.getSession(masterSession(), isCurrent());
            for (int i = 0; i < list.size(); i++) {
                session.save(list.get(i));
                if (i % size == 0) {
                    session.flush();
                    session.clear();
                }
            }
        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateBatch(List<P> list, int size) {
        Assert.notEmpty(list);
        try {
            Session session = HibernateUtils.getSession(masterSession(), isCurrent());
            for (int i = 0; i < list.size(); i++) {
                session.merge(list.get(i));
                if (i % size == 0) {
                    session.flush();
                    session.clear();
                }
            }
        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
            return false;
        }
        return true;

    }

    @Override
    public boolean saveOrUpdateBatch(List<P> list, int size) {
        Assert.notEmpty(list);
        try {
            Session session = HibernateUtils.getSession(masterSession(), isCurrent());
            for (int i = 0; i < list.size(); i++) {
                session.saveOrUpdate(list.get(i));
                if (i % size == 0) {
                    session.flush();
                    session.clear();
                }
            }
        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
            return false;
        }
        return true;

    }

    @Override
    public P selectOne(Wrapper wrapper) {
        List<P> list = selectList(wrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            int size = list.size();
            if (size > 1) {
                logger.warn(String.format("Warn: selectOne Method There are  %s results.", size));
            }
            return list.get(0);
        }
        return null;
    }

    @Override
    public <P> List<P> selectList(Wrapper wrapper) {
        List<P> list = Collections.emptyList();
        try {
            String sql = SqlUtils.sqlEntityList(poClass(), wrapper, null);
            Query query = HibernateUtils.getEntitySqlQuery(poClass(), sql, slaveSession(), isCurrent());
            list = query.list();
        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper wrapper) {
        List<Map<String, Object>> list = Collections.emptyList();
        try {
            String sql = SqlUtils.sqlList(poClass(), wrapper, null);
            Query query = HibernateUtils.getSqlQuery(sql, slaveSession(), isCurrent()).setResultTransformer(
                    Transformers.ALIAS_TO_ENTITY_MAP);
            list = query.list();
        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
        }
        return list;
    }

    @Override
    public int selectCount(Wrapper wrapper) {
        int count = 0;
        try {
            String sql = SqlUtils.sqlCount(poClass(), wrapper);
            Query query = HibernateUtils.getSqlQuery(sql, slaveSession(), isCurrent());
            BigInteger bigInteger = (BigInteger) query.uniqueResult();
            count = bigInteger.intValue();
        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
        }
        return count;
    }

    @Override
    public Page<Map<String, Object>> selectMapsPage(Wrapper wrapper, Page<Map<String, Object>> page) {
        try {
            String sql = SqlUtils.sqlList(poClass(), wrapper, page);
            Query query = HibernateUtils.getSqlQuery(sql, slaveSession(), isCurrent()).setResultTransformer(
                    Transformers.ALIAS_TO_ENTITY_MAP);
            HibernateUtils.setPage(page.getCurrent(), page.getSize(), query);
            page.setRecords(query.list());
            setPageTotal(sql, page);
        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
        }
        return page;
    }

    @Override
    public Page selectPage(Wrapper wrapper, Page page) {
        try {
            String sql = SqlUtils.sqlEntityList(poClass(), wrapper, page);
            Query query = HibernateUtils.getEntitySqlQuery(poClass(), sql, slaveSession(), isCurrent());
            HibernateUtils.setPage(page.getCurrent(), page.getSize(), query);
            page.setRecords(query.list());
            setPageTotal(sql, page);
        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
        }
        return page;
    }

    /**
     * 执行HQL
     *
     * @param hql
     * @return
     */
    protected int executeHql(String hql) {
        return executeHql(hql, Collections.EMPTY_MAP);
    }

    /**
     * 执行HQL
     *
     * @param hql
     * @param params
     * @return
     */
    protected int executeHql(String hql, Map<String, Object> params) {
        Assert.hasLength(hql);
        Query query = HibernateUtils.getHqlQuery(hql, masterSession(), isCurrent());
        setParamMap(params, query);
        return query.executeUpdate();
    }

    /**
     * 根据HQL查询列表
     *
     * @param hql
     * @return
     */
    protected List<P> queryListWithHql(String hql) {
        return queryListWithHql(hql, Collections.EMPTY_MAP);
    }

    /**
     * 根据HQL查询列表
     *
     * @param hql
     * @param params
     * @return
     */
    protected List<P> queryListWithHql(String hql, Map<String, Object> params) {
        return queryListWithHql(hql, params, 0, 0);
    }

    /**
     * 根据HQL查询列表
     *
     * @param hql
     * @param params
     * @param page
     * @param rows
     * @return
     */
    protected List<P> queryListWithHql(String hql, Map<String, Object> params, int page, int rows) {
        Assert.hasLength(hql);
        List<P> list = Collections.emptyList();
        try {
            Query query = HibernateUtils.getHqlQuery(hql, slaveSession(), isCurrent());
            setParamMap(params, query);
            HibernateUtils.setPage(page, rows, query);
            list = query.list();
        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
        }
        return list;

    }

    /**
     * 根据HQL查询列表
     *
     * @param hql
     * @param page
     * @param rows
     * @return
     */
    protected List<P> queryListWithHql(String hql, int page, int rows) {
        return queryListWithHql(hql, Collections.EMPTY_MAP, page, rows);
    }

    /**
     * 根据HQL查询列表
     *
     * @param hql
     * @return
     */
    protected List<Map<String, Object>> queryMapsWithHql(String hql) {
        return queryMapsWithHql(hql, 0, 0);
    }

    /**
     * 根据HQL查询列表
     *
     * @param hql
     * @param page
     * @param rows
     * @return
     */
    protected List<Map<String, Object>> queryMapsWithHql(String hql, int page, int rows) {
        Assert.hasLength(hql);
        List<Map<String, Object>> list = Collections.emptyList();
        try {
            Query query = HibernateUtils.getHqlQuery(hql, slaveSession(), isCurrent()).setResultTransformer(
                    Transformers.ALIAS_TO_ENTITY_MAP);
            HibernateUtils.setPage(page, rows, query);
            list = query.list();

        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
        }
        return list;
    }

    /**
     * 根据HQL查询数量
     *
     * @param hql
     * @return
     */
    protected int queryCountWithHql(String hql) {
        return queryCountWithHql(hql, Collections.EMPTY_MAP);
    }

    /**
     * 根据HQL查询数量
     *
     * @param hql
     * @param params
     * @return
     */
    protected int queryCountWithHql(String hql, Map<String, Object> params) {
        Assert.hasLength(hql);
        Query query = HibernateUtils.getHqlQuery(hql, slaveSession(), isCurrent());
        setParamMap(params, query);
        BigInteger bigInteger = (BigInteger) query.uniqueResult();
        return bigInteger.intValue();
    }

    /**
     * 执行SQL
     *
     * @param sql
     * @return
     */
    protected int executeSqlUpdate(String sql) {
        return executeSqlUpdate(sql, Collections.EMPTY_MAP);
    }

    /**
     * 执行SQL
     * <p>
     * delete from user where username = ?0 and sex = ?1
     * </p>
     *
     * @param sql
     * @param args
     * @return
     */
    protected int executeSqlUpdate(String sql, Object[] args) {
        Assert.hasLength(sql);
        int resultCount = 0;
        try {
            Query query = HibernateUtils.getSqlQuery(sql, masterSession(), isCurrent());
            if (null != args) {
                for (int i = 0; i < args.length; i++) {
                    HibernateUtils.setParams(query, StringUtils.toString(i), args[i]);
                }
            }
            resultCount = query.executeUpdate();
        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
        }
        return resultCount;
    }

    /**
     * 执行SQL
     * <p>
     * delete from user where username = :username and sex = :sex
     * </p>
     *
     * @param sql
     * @param params
     * @return
     */
    protected int executeSqlUpdate(String sql, Map<String, Object> params) {
        Assert.hasLength(sql);
        int resultCount = 0;
        if (StringUtils.isNotBlank(sql)) {
            try {
                Query query = HibernateUtils.getSqlQuery(sql, masterSession(), isCurrent());
                setParamMap(params, query);
                resultCount = query.executeUpdate();
            } catch (Exception e) {
                logger.warn("Warn: Unexpected exception.  Cause:" + e);
            }
        }
        return resultCount;
    }

    /**
     * 执行SQL查询数量
     *
     * @param sql
     * @return
     */
    protected int queryCount(String sql) {
        return queryCount(sql, SelectWrapper.DEFAULT);
    }

    /**
     * 执行SQL查询数量
     *
     * @param sql
     * @return
     */
    protected int queryCount(String sql, Wrapper wrapper) {
        Assert.hasLength(sql);
        sql += wrapper.getSqlSegment();
        int count = 0;
        try {
            Query query = HibernateUtils.getSqlQuery(sql, slaveSession(), isCurrent());
            BigInteger bigInteger = (BigInteger) query.uniqueResult();
            bigInteger.intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 执行SQL返回单个对象
     *
     * @param sql
     * @param wrapper
     * @return
     */
    protected Map<String, Object> queryMap(String sql, Wrapper wrapper) {
        Assert.hasLength(sql);
        Map<String, Object> map = Collections.emptyMap();
        try {
            sql += wrapper.getSqlSegment();
            Query query = HibernateUtils.getSqlQuery(sql, slaveSession(), isCurrent()).setResultTransformer(
                    Transformers.ALIAS_TO_ENTITY_MAP);
            map = (Map<String, Object>) query.uniqueResult();
        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
        }
        return map;
    }

    /**
     * 执行SQL返回单个对象
     *
     * @param sql
     * @return
     */
    protected Map<String, Object> queryMap(String sql) {
        return queryMap(sql, SelectWrapper.DEFAULT);
    }

    /**
     * 执行SQL查询列表
     *
     * @param sql
     * @param page
     * @return
     */
    protected Page<Map<String, Object>> queryMapsPage(String sql, Page page) {
        return queryMapsPage(sql, SelectWrapper.DEFAULT, page);
    }

    /**
     * 执行SQL查询列表
     *
     * @param sql
     * @param wrapper
     * @param page
     * @return
     */
    protected Page<Map<String, Object>> queryMapsPage(String sql, Wrapper wrapper, Page page) {
        Assert.hasLength(sql);
        try {
            sql = SqlUtils.sqlList(sql, wrapper, page);
            Query query = HibernateUtils.getSqlQuery(sql, slaveSession(), isCurrent()).setResultTransformer(
                    Transformers.ALIAS_TO_ENTITY_MAP);
            HibernateUtils.setPage(page.getCurrent(), page.getSize(), query);
            page.setRecords(query.list());
            setPageTotal(sql, page);
        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
        }
        return page;
    }

    /**
     * 执行SQL查询列表
     *
     * @param sql
     * @return
     */
    protected List<Map<String, Object>> queryMaps(String sql) {
        return this.queryMaps(sql, Collections.EMPTY_MAP);
    }

    /**
     * 执行SQL查询列表
     *
     * @param sql
     * @param params
     * @return
     */
    protected List<Map<String, Object>> queryMaps(String sql, Map<String, Object> params) {
        Assert.hasLength(sql);
        List list = Collections.emptyList();
        try {
            Query query = HibernateUtils.getSqlQuery(sql, slaveSession(), isCurrent()).setResultTransformer(
                    Transformers.ALIAS_TO_ENTITY_MAP);
            setParamMap(params, query);
            list = query.list();
        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
        }
        return list;
    }

    /**
     * 执行SQL查询列表
     *
     * @param sql
     * @param wrapper
     * @return
     */
    protected List<Map<String, Object>> queryMaps(String sql, Wrapper wrapper) {
        Assert.hasLength(sql);
        List list = Collections.emptyList();
        try {
            sql += wrapper.toString();
            Query query = HibernateUtils.getSqlQuery(sql, slaveSession(), isCurrent()).setResultTransformer(
                    Transformers.ALIAS_TO_ENTITY_MAP);
            list = query.list();
        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
        }
        return list;
    }

    /**
     * 查询列表
     *
     * @param params
     * @return
     */
    public List<Map<String, Object>> queryMaps(Map<String, Object> params) {
        List<Map<String, Object>> list = Collections.emptyList();
        try {
            String hql = HibernateUtils.getListHql(poClass(), params);
            Query query = HibernateUtils.getHqlQuery(hql, slaveSession(), isCurrent()).setResultTransformer(
                    Transformers.ALIAS_TO_ENTITY_MAP);
            setParamMap(params, query);
            list = query.list();
        } catch (Exception e) {
            logger.warn("Warn: Unexpected exception.  Cause:" + e);
        }
        return list;

    }

    /**
     * 设置Page total值
     *
     * @param sql
     * @param page
     */
    private void setPageTotal(String sql, Page<?> page) {
        if (page.isSearchCount()) {
            String sqlCount = SqlUtils.sqlCountOptimize(sql);
            Query countQuery = HibernateUtils.getSqlQuery(sqlCount, slaveSession(), isCurrent());
            BigInteger bigInteger = (BigInteger) countQuery.uniqueResult();
            page.setTotal(bigInteger.intValue());
        }
    }

    /**
     * Query设置Map参数
     *
     * @param params
     * @param query
     * @return
     * @throws
     * @author Caratacus
     * @date 2016/9/2 0002
     * @version 1.0
     */
    private void setParamMap(Map<String, Object> params, Query query) {
        if (MapUtils.isNotEmpty(params)) {
            for (String key : params.keySet()) {
                Object obj = params.get(key);
                HibernateUtils.setParams(query, key, obj);
            }
        }
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
     * 是否获取当前事务的Session
     *
     * @return
     */
    protected Boolean isCurrent() {
        return true;
    }

}
