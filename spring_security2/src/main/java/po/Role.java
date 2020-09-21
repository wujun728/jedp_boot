package po;

import java.util.List;

public class Role {
	Long roleid;
	String role;
	String description;
	List<User_role> user_roles;  
	List<Role_permission> role_permissions;
	
	public Long getRoleid() {
		return roleid;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<User_role> getUser_roles() {
		return user_roles;
	}
	public void setUser_roles(List<User_role> user_roles) {
		this.user_roles = user_roles;
	}
	public List<Role_permission> getRole_permissions() {
		return role_permissions;
	}
	public void setRole_permissions(List<Role_permission> role_permissions) {
		this.role_permissions = role_permissions;
	}
	
	
}
