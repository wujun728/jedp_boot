package service.impl;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mapper.RoleMapper;
import mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import po.Role;
import po.Role_permission;
import po.User;
import po.User_role;
import service.UserService;

import com.github.pagehelper.PageHelper;
@Service("userservice")
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=5)
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper usermapper;
	
	@Autowired
	RoleMapper rolemapper;
	
	public User createUser(User user) {
		usermapper.adduser(user);
		return user;
	}

	public void changePassword(Long userId, String newPassword) {
		User u=usermapper.getUserByid(userId);
		u.setPassword(newPassword);
		usermapper.updateuser(u);
	}

	public void correlationRoles(Long userId, Long... roleIds) {
		for(Long roleid:roleIds){
			usermapper.adduserrole(userId, roleid);
		}
	}

	public void uncorrelationRoles(Long userId, Long... roleIds) {
		for(Long roleid:roleIds){
			usermapper.deleteuserrole(userId, roleid);
		}
	}

	public User findByUsername(String username) {
		User u=usermapper.getUserByusername(username);
		return u;
	}

	public Set<String> findRoles(String username) {
		Set<String> roles=new HashSet<String>();
		User u=usermapper.getUserByusername(username);
		List<User_role> user_roles=u.getUser_roles();
		for(User_role ur:user_roles){
			Role r=ur.getRole();
			roles.add(r.getRole());
		}
		return roles;
	}

	public Set<String> findPermissions(String username) {
		Set<String> permissions=new HashSet<String>();
		User u=usermapper.getUserByusername(username);
		List<User_role> user_roles=u.getUser_roles();
		for(User_role ur:user_roles){
			Role role=rolemapper.getRolebyid(ur.getRole().getRoleid());
			List <Role_permission> rps=role.getRole_permissions();
			for(Role_permission rp:rps){
				String permission=rp.getPermission().getPermission();
				permissions.add(permission);
			}
		}
		return permissions;
	}

	public List<User> getallusers() {
		return usermapper.getusers();
	}

	public List<User> getpageusers(int pagenum, int pagesize) {
		PageHelper.startPage(pagenum,pagesize);  
		List<User> users=usermapper.getusers();
		return users;
	}

	public User updateuser(User u) {
		usermapper.updateuser(u);
		return u;
	}

	public void deleteuser(Long userid) {
		usermapper.deleteuser(userid);
	}

	public User getUser(Long uid) {
		User u=usermapper.getUserByid(uid);
		return u;
	}

	public void deleteuserroles(Long uid) {
		usermapper.deleteuseroles(uid);
	}


}
