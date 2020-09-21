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
import cn.springmvc.hibernate.dao.PermissionDaoService;
import cn.springmvc.hibernate.model.Permission;
import cn.springmvc.hibernate.model.User;

/**
 * @author Vincent.wang
 *
 */
@Repository
public class PermissionDaoServiceImpl implements PermissionDaoService {

	private static final Logger log = LoggerFactory.getLogger(PermissionDaoServiceImpl.class);

	@Autowired
	private DaoService daoService;

	@Override
	public Collection<Permission> getPermissions(User user) {
		if (null == user) {
			log.warn("empty user used when trying to find permissions.");
			return null;
		}

		StringBuilder ql = new StringBuilder();
		ql.append(" select distinct p from cn.springmvc.hibernate.model.Permission p , ");
		ql.append(" cn.springmvc.hibernate.model.UserRole ur , cn.springmvc.hibernate.model.RolePermission rp ");
		ql.append(" where rp.permissionId = p.id ");
		ql.append("   and rp.roleId = ur.roleId ");
		ql.append("   and ur.userId = :userId ");
		ql.append(" order by p.parentKey asc , p.sort asc");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", user.getId());

		return daoService.query(ql.toString(), params);
	}

	@Override
	public void addPermission(Permission permission) {
		Permission permis = findPermissionByKey(permission.getKey());
		if (permis != null) {
			throw new DaoException("## 菜单Key已经存在，不能重复。");
		} else {
			daoService.save(permission);
		}
	}

	@Override
	public Permission findPermissionByKey(String permissionKey) {
		if (StringUtils.isBlank(permissionKey)) {
			return null;
		}
		String ql = " from cn.springmvc.hibernate.model.Permission where key=:key";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("key", permissionKey);
		return daoService.queryOne(ql, params);
	}

}
