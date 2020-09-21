package cn.springmvc.hibernate.dao;

import java.util.Collection;

import cn.springmvc.hibernate.model.Permission;
import cn.springmvc.hibernate.model.Role;

/**
 * @author Vincent.wang
 *
 */
public interface RoleDaoService {

	/**
	 * 添加角色，若已经存在同名角色，则不创建
	 *
	 * @param role
	 *            角色
	 */
	public void addRole(Role role);

	/**
	 * 根据用户查询对应所有角色
	 *
	 * @param userId
	 *            用户
	 * @return roles 所有角色
	 */
	public Collection<Role> findRoles(String userId);

	/**
	 * 根据编码查询角色
	 *
	 * @param code
	 *            角色编码
	 * @return
	 */
	public Role findRoleByCode(String code);

	/**
	 * 给角色授权操作
	 *
	 * @param role
	 *            角色
	 * @param permis
	 *            授权
	 */
	public void addRolePermission(Role role, Permission permis);

	// /**
	// * 查询用户与管理员角色的关系
	// *
	// * @param id
	// * 用户ID
	// * @return
	// * @author wangxin
	// */
	// public Collection<UserRole> findUserRole(String id);
}
