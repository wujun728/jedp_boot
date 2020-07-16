package service.impl;

import java.util.List;

import mapper.PermissionMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import po.Permission;
import service.PermissionService;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=5)
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	PermissionMapper permissionmapper;
	
	public Permission createPermission(Permission permission) {
		permissionmapper.addpermission(permission);
		return permission;
	}

	public void deletePermission(Long permissionId) {
		permissionmapper.deletepermission(permissionId);
	}

	public List<Permission> getPermissions() {
		return permissionmapper.getPermissions();
	}

	public Permission getPermissionByid(Long permissionid) {
		return permissionmapper.getPermissionByid(permissionid);
	}

	public Permission updatePermission(Permission permission) {
		permissionmapper.updatePermission(permission);
		return permission;
	}

	public List<Permission> getPagePermissions(int pagenum, int pagesize) {
		PageHelper.startPage(pagenum,pagesize);
		List<Permission> list=permissionmapper.getPermissions();
		return list;
	}

	public void deletePermissions(Long permissionId) {
		permissionmapper.deletePermissionsByid(permissionId);
	}

}
