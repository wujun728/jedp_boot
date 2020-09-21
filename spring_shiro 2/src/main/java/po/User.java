package po;

import java.util.List;

public class User {
	Long userid;
	String username;
	String password;
	Boolean locked;
	List<User_role> user_roles;
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	public List<User_role> getUser_roles() {
		return user_roles;
	}
	public void setUser_roles(List<User_role> user_roles) {
		this.user_roles = user_roles;
	}
	
}
