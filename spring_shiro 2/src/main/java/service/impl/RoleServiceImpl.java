package service.impl;

import java.util.List;

import mapper.RoleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import po.Role;
import service.RoleService;
@Service
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=5)
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleMapper rolemapper;
	
	public Role createRole(Role role) {
		rolemapper.addRole(role);
		return role;
	}

	public void deleteRole(Long roleId) {
		rolemapper.deleterole(roleId);
	}

	public void correlationPermissions(Long roleId, Long... permissionIds) {
		for(Long pid:permissionIds){
			rolemapper.addRolePermission(roleId, pid);
		}
		
	}

	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		for(Long pid:permissionIds){
			rolemapper.deleterole_permission(roleId, pid);
		}
	}

	public List<Role> getroles() {
		return rolemapper.getRoles();
	}

	public List<Role> getpagerole(int pagenum, int pagesize) {
		PageHelper.startPage(pagenum,pagesize);
		List<Role> list=rolemapper.getRoles();
		return list;
	}

	public void deleteroles(Long roleid) {
		rolemapper.deleteroles(roleid);
	}

	public Role getrolebyid(Long roleid) {
		return rolemapper.getRolebyid(roleid);
	}

	public Role updateRole(Role r) {
		rolemapper.updateRole(r);
		return r;
	}


}
