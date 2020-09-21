package cn.springmvc.hibernate.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.springmvc.hibernate.common.exception.DaoException;
import cn.springmvc.hibernate.dao.DaoService;
import cn.springmvc.hibernate.dao.RoleDaoService;
import cn.springmvc.hibernate.model.Permission;
import cn.springmvc.hibernate.model.Role;
import cn.springmvc.hibernate.model.RolePermission;

/**
 * @author Vincent.wang
 *
 */
@Repository
public class RoleDaoServiceImpl implements RoleDaoService {

    private static final Logger log = LoggerFactory.getLogger(RoleDaoServiceImpl.class);

    @Autowired
    private DaoService daoService;

    @Override
    public void addRole(Role role) {
        if (role == null || StringUtils.isBlank(role.getName())) {
            throw new DaoException(" 添加角色失败，角色对象数据不完整。");
        }

        String ql = "from cn.springmvc.hibernate.model.Role where code=:code";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", role.getCode());
        Role r = daoService.queryOne(ql, params);
        if (null == r) {
            daoService.save(role);
        }
    }

    @Override
    public Role findRoleByCode(String code) {
        String ql = "from cn.springmvc.hibernate.model.Role where code=:code";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        return daoService.queryOne(ql, params);
    }

    @Override
    public Collection<Role> findRoles(String userId) {
        if (StringUtils.isBlank(userId)) {
            log.warn("empty user used when trying to find roles.");
            return null;
        }
        String ql = "select r from cn.springmvc.hibernate.model.Role r ,  cn.springmvc.hibernate.model.UserRole ur where r.id = ur.roleId and ur.userId=:userId";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        return daoService.query(ql, params);
    }

    @Override
    public void addRolePermission(Role role, Permission permis) {
        if (null != role && null != permis) {
            String ql = "from cn.springmvc.hibernate.model.RolePermission where roleId=:roleId and permissionId=:permissionId";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("roleId", role.getId());
            params.put("permissionId", permis.getId());
            RolePermission rolePermis = daoService.queryOne(ql, params);
            if (rolePermis == null) {
                rolePermis = new RolePermission();
                rolePermis.setRoleId(role.getId());
                rolePermis.setPermissionId(permis.getId());
                daoService.save(rolePermis);
            }
        }

    }

}
