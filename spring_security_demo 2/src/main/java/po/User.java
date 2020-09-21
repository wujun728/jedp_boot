package po;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User {

	private static final long serialVersionUID = 1L;

	Long userid;
	String username;
	String password;
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
	public List<User_role> getUser_roles() {
		return user_roles;
	}
	public void setUser_roles(List<User_role> user_roles) {
		this.user_roles = user_roles;
	}

	


}
