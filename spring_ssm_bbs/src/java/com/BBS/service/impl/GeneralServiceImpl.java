package com.BBS.service.impl;

import com.BBS.dao.GeneralDao;
import com.BBS.service.GeneralService;
import com.BBS.utils.AppConstant;
import com.BBS.utils.PropertiesUtil;
import com.BBS.utils.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class GeneralServiceImpl implements GeneralService {
    private static final Logger logger = Logger.getLogger(GeneralServiceImpl.class);
    private final GeneralDao generalDao;
    private final JdbcTemplate jdbcTemplate;
    private final StringRedisTemplate redisTemplate;

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Autowired
    public GeneralServiceImpl(GeneralDao generalDao, JdbcTemplate jdbcTemplate, StringRedisTemplate redisTemplate) {
        this.generalDao = generalDao;
        this.jdbcTemplate = jdbcTemplate;
        this.redisTemplate = redisTemplate;
    }

    private Session getSession() {
//          return  this.sessionFactory.openSession();
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public String getUploadBasePath() throws Exception {
        PropertiesUtil p = new PropertiesUtil("sysConfig.properties");
        return p.readProperty("uploadpath");
    }

    @Override
    public Integer getCount(Map<String, String> map) {
        return this.generalDao.getCount(map);
    }

    @Override
    public List<Map<String, Object>> findForJdbc(String sql, Object... arg1) {
        return this.jdbcTemplate.queryForList(sql, arg1);
    }

    @Override
    public Map<String, Object> findOneForJdbc(String sql, Object... arg1) {
        return this.jdbcTemplate.queryForMap(sql, arg1);
    }

    @Override
    @Transactional
    public void executeSql(String sql, Object... arg1) {
        this.jdbcTemplate.update(sql, arg1);
    }

    @Override
    public <T> List<T> findHql(String sql, Object... params) {
        Query query = this.getSession().createQuery(sql);
//        query.setFirstResult(1);
//        query.setMaxResults(2);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        return query.list();
    }

    @Override
    @Transactional
    public <T> String save(T t) {

        Serializable id = this.getSession().save(t);
        this.getSession().flush();
        if (logger.isDebugEnabled()) {
            logger.debug("保存实体成功," + t.getClass().getName());
        }
        return (String) id;
    }

    @Override
    @Transactional
    public <T> String saveOrUpdate(Class<T> tClass, T t) {

        if (StringUtils.isEmpty(String.valueOf(t))) {
            this.getSession().save(tClass);
            if (logger.isDebugEnabled()) {
                logger.debug("保存实体成功," + tClass.getClass().getName());
            }
            return AppConstant.successMsg;
        } else {
            this.getSession().update(tClass);
            return AppConstant.successMsg;
        }


    }

    @Override
    public <T> T get(Class<T> tClass, String id) {

        return (T) this.getSession().get(tClass, id);

    }

    @Override
    @Transactional
    public <T> void update(T entity) {
        this.getSession().update(entity);
        this.getSession().flush();
    }

    @Override
    public void opsForStringSet(String key, String value, Long time) {
        try {
            redisTemplate.opsForValue().set(key, value, time);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public void opsForStringSet(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public String opsForStringGet(String key) {
        String value;
        try {
            value = redisTemplate.opsForValue().get(key);

        } catch (Exception e) {
            e.printStackTrace();
            value = e.getMessage();
        }
        return value;
    }

    @Override
    public void opsForMapSet(String Key, Map<? extends String, ?> map, Long time) {
        try {
            redisTemplate.opsForHash().putAll(Key, map);
            redisTemplate.expire(Key, time, TimeUnit.DAYS);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    @Override
    public Map<? extends String, ?> opsForMapGet(String Key) {
        Map<? extends String, ?> map = null;
        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
        try {
            map = hash.entries(Key);
        } catch (Exception e) {
            e.printStackTrace();
//            map.put("error", (Object) e.getMessage());
            e.getMessage();
        }
        return map;
    }

    @Override
    public void opsForListSet(String Key, List<?> list, Long time) {
        try {
            redisTemplate.opsForList().leftPush(Key, String.valueOf(list));
            redisTemplate.expire(Key, time, TimeUnit.DAYS);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    @Override
    public List<?> opsForListGet(String Key) {
        List<?> list = null;
        try {
            ListOperations<String, String> listOperations = redisTemplate.opsForList();
            list = listOperations.range(Key, 0, -1);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return list;
    }

    @Override
    public String opsForDelKey(String Key) {

        String booleanCode;
        try {
            this.redisTemplate.delete(Key);
            booleanCode = AppConstant.successMsg;
        } catch (Exception e) {
            e.printStackTrace();
            booleanCode = e.getMessage();
        }
        return booleanCode;
    }
}
