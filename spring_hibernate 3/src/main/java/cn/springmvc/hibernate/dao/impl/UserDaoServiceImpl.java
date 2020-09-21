package cn.springmvc.hibernate.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.springmvc.hibernate.common.constants.Constants;
import cn.springmvc.hibernate.dao.DaoService;
import cn.springmvc.hibernate.dao.UserDaoService;
import cn.springmvc.hibernate.model.User;

/**
 * @author Vincent.wang
 *
 */
@Repository
public class UserDaoServiceImpl implements UserDaoService {

    private static final Logger log = LoggerFactory.getLogger(UserDaoServiceImpl.class);

    @Autowired
    private DaoService daoService;

    @Override
    public User findLocalUserByName(String username) {
        if (null == username || username.trim() == "") {
            log.warn("empty username used when trying to find local user.");
            return null;
        }
        String ql = "from cn.springmvc.hibernate.model.User u where u.name=:username";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        return daoService.queryOne(ql, params);
    }

    @Override
    public Collection<User> findUserByShop(String id) {
        String hql = "from cn.springmvc.hibernate.model.User where shopId=:shopId";
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("shopId", id);
        return daoService.query(hql, parameter);
    }

    @Override
    public void updateUserLastLoginTime(User user) {
        String ql = "update cn.springmvc.hibernate.model.User set lastLoginTime=:lastLoginTime where id=:id";
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("lastLoginTime", user.getLastLoginTime());
        parameter.put("id", user.getId());
        daoService.executeHqlUpdate(ql, parameter);
    }

    @Override
    public Collection<User> findUsers() {
        StringBuilder hql = new StringBuilder();
        hql.append(" select u from cn.springmvc.hibernate.model.User u , cn.springmvc.hibernate.model.UserRole ur , cn.springmvc.hibernate.model.Role r ");
        hql.append(" where u.id=ur.userId and ur.roleId=r.id ");
        hql.append(" and r.code=:roleCode ");
        hql.append(" and u.status=:status");
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("roleCode", Constants.COMMON_ROLE_CODE);
        parameter.put("status", Constants.STATUS_VALID);
        return daoService.query(hql.toString(), parameter);
    }

    @Override
    public Collection<User> findEmp(String shopId, String empName) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        StringBuilder hql = new StringBuilder();
        hql.append(" select u from cn.springmvc.hibernate.model.User u , cn.springmvc.hibernate.model.UserRole ur , cn.springmvc.hibernate.model.Role r ");
        hql.append(" where u.id=ur.userId and ur.roleId=r.id ");
        hql.append(" and r.code=:roleCode ");
        hql.append(" and u.status=:status");
        parameter.put("roleCode", Constants.COMMON_ROLE_CODE);
        parameter.put("status", Constants.STATUS_VALID);
        if (StringUtils.isNotBlank(shopId)) {
            hql.append(" and u.organizeId=:organizeId");
            parameter.put("organizeId", shopId);
        }

        if (StringUtils.isNotBlank(empName)) {
            hql.append(" and u.trueName like :trueName");
            parameter.put("trueName", "%" + empName + "%");
        }
        return daoService.query(hql.toString(), parameter);
    }
}