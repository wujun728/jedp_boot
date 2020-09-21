package cn.springmvc.hibernate.dao.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.springmvc.hibernate.common.exception.DaoException;
import cn.springmvc.hibernate.dao.DaoService;
import cn.springmvc.hibernate.model.BaseEntity;

/**
 * @author Vincent.wang
 *
 */
@Repository
@Transactional
public class DaoServiceImpl implements DaoService {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier("jdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public <E extends BaseEntity<String>> void delete(E entity) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(entity);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public <E extends BaseEntity<String>, P extends Serializable> void deleteByPrimaryKey(Class<E> clazz, P id) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            E entity = getByPrimaryKey(clazz, id);
            if (entity != null) {
                session.delete(entity);
            } else {
                throw new PersistenceException("The entity you want to delete is not existed.");
            }
        } catch (HibernateException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public int execute(String sql) throws DaoException {
        Map<String, Object> paramMap = null;
        return jdbcTemplate.update(sql, paramMap);
    }

    @Override
    public int execute(String sql, Map<String, Object> parameter) throws DaoException {
        return jdbcTemplate.update(sql, parameter);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void executeBatch(String sql, List<Map<String, Object>> parameter) throws DaoException {
        if (parameter == null) {
            throw new DaoException("parameter can not be null.");
        }
        Map<String, Object>[] paramArray = new Map[parameter.size()];
        int index = 0;
        for (Map<String, Object> paramMap : parameter) {
            paramArray[index++] = paramMap;
        }
        jdbcTemplate.batchUpdate(sql, paramArray);
    }

    @Override
    public List<Map<String, Object>> executeNativeQuery(String sql) throws DaoException {
        Map<String, Object> paramMap = null;
        return jdbcTemplate.queryForList(sql, paramMap);
    }

    @Override
    public List<Map<String, Object>> executeNativeQuery(String sql, Map<String, Object> parameter) throws DaoException {
        return jdbcTemplate.queryForList(sql, parameter);
    }

    @Override
    public <T> T executeNativeQuery(String sql, Class<T> clazz, Map<String, Object> paramMap) {
        try {
            return jdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(clazz));
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public <T> List<T> executeNativeQueryForList(String sql, Class<T> clazz, Map<String, Object> paramMap) {
        try {
            return jdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(clazz));
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E extends BaseEntity<String>> List<E> query(String ql, Map<String, Object> parameter) throws DaoException {
        List<E> list = Collections.emptyList();
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(ql);
            for (String key : parameter.keySet()) {
                if (parameter.get(key) instanceof List) {
                    query.setParameterList(key, (List<Object>) parameter.get(key));
                } else {
                    query.setParameter(key, parameter.get(key));
                }
            }
            list = query.list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E extends BaseEntity<String>> List<E> query(String ql) throws DaoException {
        List<E> collection = Collections.emptyList();
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(ql);
            collection = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return collection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E extends BaseEntity<String>, P extends Serializable> E getByPrimaryKey(Class<E> clazz, P id) throws DaoException {
        E entity = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            entity = (E) session.get(clazz, id);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E extends BaseEntity<String>> E queryOne(String ql, Map<String, Object> parameter) throws DaoException {
        E entity = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(ql);
            for (String key : parameter.keySet()) {
                query.setParameter(key, parameter.get(key));
            }
            List<E> collection = query.list();
            if (!collection.isEmpty()) {
                entity = collection.iterator().next();
            }
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E extends BaseEntity<String>> E queryOne(String ql) throws DaoException {
        E entity = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(ql);
            List<E> collection = query.list();
            if (!collection.isEmpty()) {
                entity = collection.iterator().next();
            }
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return entity;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Integer queryOneForInteger(String ql, Map<String, Object> parameter) throws DaoException {
        Integer num = 0;
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(ql);
            for (String key : parameter.keySet()) {
                if (parameter.get(key) instanceof List) {
                    query.setParameterList(key, (List<Object>) parameter.get(key));
                } else {
                    query.setParameter(key, parameter.get(key));
                }
            }
            List collection = query.list();
            if (!collection.isEmpty()) {
                num = Integer.parseInt(query.iterate().next().toString());
            }
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return num;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Integer queryOneForInteger(String ql) throws DaoException {
        Integer num = 0;
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(ql);
            List collection = query.list();
            if (!collection.isEmpty()) {
                num = Integer.parseInt(query.iterate().next().toString());
            }
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return num;
    }

    @Override
    public <E extends BaseEntity<String>> void save(E entity) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(entity);
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
    }

    @Override
    public <E extends BaseEntity<String>> void update(E entity) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(entity);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public <E extends BaseEntity<String>> E saveOrUpdate(E entity) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(entity);
            return entity;
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public <E extends BaseEntity<String>> void executeBatchByEntity(String sql, List<E> parameter) throws DaoException {
        if (parameter == null) {
            throw new DaoException("parameter can not be null.");
        }
        SqlParameterSource[] parameterSourceArray = SqlParameterSourceUtils.createBatch(parameter.toArray());
        jdbcTemplate.batchUpdate(sql, parameterSourceArray);
    }

    @Override
    public <E extends BaseEntity<String>> int executeByEntity(String sql, E parameter) throws DaoException {
        int result = 0;
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(parameter);
        result = jdbcTemplate.update(sql, parameterSource);
        return result;
    }

    @Override
    public <E extends BaseEntity<String>> List<Map<String, Object>> executeNativeQueryByEntity(String sql, E parameter) throws DaoException {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(parameter);
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, parameterSource);
        return results;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E extends BaseEntity<String>> List<E> query(String ql, int firstResult, int maxResults) throws DaoException {
        List<E> collection = Collections.emptyList();
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(ql);
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResults);
            collection = query.list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return collection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E extends BaseEntity<String>> List<E> query(String ql, Map<String, Object> parameter, int firstResult, int maxResults) {
        List<E> collection = Collections.emptyList();
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(ql);
        for (String key : parameter.keySet()) {
            if (parameter.get(key) instanceof List) {
                query.setParameterList(key, (List<Object>) parameter.get(key));
            } else {
                query.setParameter(key, parameter.get(key));
            }
        }
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        collection = query.list();
        return collection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> queryForListArray(String ql, Map<String, Object> parameter, int firstResult, int maxResults) {
        List<Object[]> collection = Collections.emptyList();
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(ql);
        for (String key : parameter.keySet()) {
            if (parameter.get(key) instanceof List) {
                query.setParameterList(key, (List<Object>) parameter.get(key));
            } else {
                query.setParameter(key, parameter.get(key));
            }
        }
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        collection = query.list();
        return collection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> queryForListArray(String ql, Map<String, Object> parameter) {
        List<Object[]> collection = Collections.emptyList();
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(ql);
        for (String key : parameter.keySet()) {
            if (parameter.get(key) instanceof List) {
                query.setParameterList(key, (List<Object>) parameter.get(key));
            } else {
                query.setParameter(key, parameter.get(key));
            }
        }
        collection = query.list();
        return collection;
    }

    @SuppressWarnings("unchecked")
    public int executeHqlUpdate(String ql, Map<String, Object> parameter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(ql);
        for (String key : parameter.keySet()) {
            if (parameter.get(key) instanceof List) {
                query.setParameterList(key, (List<Object>) parameter.get(key));
            } else {
                query.setParameter(key, parameter.get(key));
            }
        }
        return query.executeUpdate();
    }

    @Override
    public void executeStoredProcedure(String procedureName) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery(procedureName);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> queryByFields(String ql, Map<String, Object> parameter, int firstResult, int maxResults) {
        List<Map<String, Object>> list = Collections.emptyList();
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(ql);
        for (String key : parameter.keySet()) {
            query.setParameter(key, parameter.get(key));
        }
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        list = query.list();
        return list;
    }

}
