package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pagemodel.DataGrid;
import pagemodel.MSG;
import pagemodel.UserInfo;
import po.User;
import po.User_role;
import service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userservice;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public DataGrid<UserInfo> getusers(@RequestParam("current") int current,
			@RequestParam("rowCount") int rowCount) {
		DataGrid<UserInfo> grid = new DataGrid<UserInfo>();
		List<User> list = userservice.getallusers();
		int total = list.size();
		List<User> pageuser = userservice.getpageusers(current, rowCount);
		List<UserInfo> userlist = new ArrayList<UserInfo>();
		for (User u : pageuser) {
			UserInfo info = new UserInfo();
			info.setId(u.getUserid());
			info.setUsername(u.getUsername());
			Set<String> set = userservice.findRoles(u.getUsername());
			info.setRoles(set.toString());
			userlist.add(info);
		}
		grid.setCurrent(current);
		grid.setTotal(total);
		grid.setRowCount(rowCount);
		grid.setRows(userlist);
		return grid;
	}

	//保护后台接口，角色要以"ROLE_"开头，否则无效
	//必须有ROLE_HEHE的角色才能拉取到
	@Secured({ "ROLE_HEHE" })
	@RequestMapping(value = "/users/permissions", method = RequestMethod.GET)
	@ResponseBody
	public Set<String> getpermissions() {
		Set<String> list = userservice.findPermissions("wsz");
		return list;
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseBody
	public User adduser(@RequestBody User u) {
		String pwd = u.getPassword();
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		String mypwd = encoder.encode(pwd);
		u.setPassword(mypwd);
		User user = userservice.createUser(u);
		Long uid = user.getUserid();
		List<User_role> urlist = u.getUser_roles();
		for (User_role ur : urlist) {
			Long roleid = ur.getRole().getRoleid();
			userservice.correlationRoles(uid, roleid);
		}
		return user;
	}

	@RequestMapping(value = "/users/{uid}", method = RequestMethod.PUT)
	@ResponseBody
	public User updateuser(@PathVariable Long uid, @RequestBody User u) {
		userservice.deleteuserroles(uid);
		List<User_role> urlist = u.getUser_roles();
		for (User_role ur : urlist) {
			Long roleid = ur.getRole().getRoleid();
			userservice.correlationRoles(uid, roleid);
		}
		return u;
	}

	@RequestMapping(value = "/users/{uid}", method = RequestMethod.GET)
	@ResponseBody
	public User getuser(@PathVariable Long uid) {
		User user = userservice.getUser(uid);
		return user;
	}

	@RequestMapping(value = "/users/{uid}", method = RequestMethod.DELETE)
	@ResponseBody
	public MSG deleteuser(@PathVariable Long uid) {
		userservice.deleteuser(uid);
		userservice.deleteuserroles(uid);
		return new MSG("delete success");
	}

	
	@RequestMapping(value = "/currentuser", method = RequestMethod.GET)
	@ResponseBody
	public MSG getcurrentuser() {
		UserDetails userDetails =  (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new MSG(userDetails.getUsername());
	}

}
