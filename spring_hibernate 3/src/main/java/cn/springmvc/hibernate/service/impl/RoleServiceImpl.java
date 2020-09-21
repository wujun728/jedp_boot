package cn.springmvc.hibernate.service.impl;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.hibernate.common.exception.BusinessException;
import cn.springmvc.hibernate.dao.PermissionDaoService;
import cn.springmvc.hibernate.dao.RoleDaoService;
import cn.springmvc.hibernate.model.Permission;
import cn.springmvc.hibernate.model.Role;
import cn.springmvc.hibernate.model.User;
import cn.springmvc.hibernate.service.RoleService;

/**
 * @author Vincent.wang
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	private final static Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleDaoService roleDaoService;

	@Autowired
	private PermissionDaoService permissionDaoService;

	public void addRole(Role role) {
		if (role == null || StringUtils.isBlank(role.getName())) {
			return;
		}
		if (log.isDebugEnabled()) {
			log.debug("## 添加角色 : {}", role.getName());
		}
		roleDaoService.addRole(role);

	}

	@Override
	public Role findRoleByCode(String code) {
		if (log.isDebugEnabled()) {
			log.debug("## 根据编码查询角色 :　{}", code);
		}
		return roleDaoService.findRoleByCode(code);
	}

	@Override
	public Collection<Role> findRoles(User user) {
		return roleDaoService.findRoles(user.getId());
	}

	@Override
	public void addRolePermission(String roleCode, String permissionKey) {
		Role role = findRoleByCode(roleCode);
		if (role == null) {
			throw new BusinessException("## 给角色授权失败， 角色编码错误");
		}
		Permission permis = permissionDaoService.findPermissionByKey(permissionKey);
		if (permis == null) {
			throw new BusinessException("## 给角色授权失败， 授权KEY错误");
		}

		roleDaoService.addRolePermission(role, permis);

	}
}
