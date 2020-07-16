package mapper;

import java.util.List;
import po.Permission;

public interface PermissionMapper {
	List<Permission> getPermissions();
	void addpermission(Permission permission);
	void deletepermission(long pid);
	Permission getPermissionByid(Long pid);
	Permission updatePermission(Permission permission);
	void deletePermissionsByid(Long permissionid);//删除所有角色中的permission
}
