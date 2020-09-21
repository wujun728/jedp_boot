package mapper;

import java.util.List;
import po.Role;


public interface RoleMapper {
	List<Role> getRoles();
	void addRole(Role role);
	void deleterole(long roleid);//删除一个角色
	void addRolePermission(long roleid,long permissionid);
	void deleterole_permission(long roleid,long permissionid);//删除一个角色和一个权限的关联
	void deleteroles(long roleid);//删除一个角色的所有权限关联
	Role getRolebyid(long roleid);
	void updateRole(Role role);
}
