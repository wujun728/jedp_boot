package po;

import java.util.List;

public class Permission {
	Long permissionid;
	String permission;
	String description;
	List<Role_permission> rp;
	
	public Long getPermissionid() {
		return permissionid;
	}
	public void setPermissionid(Long permissionid) {
		this.permissionid = permissionid;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Role_permission> getRp() {
		return rp;
	}
	public void setRp(List<Role_permission> rp) {
		this.rp = rp;
	}
	
	
}
