package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
import po.Permission;
import po.Role;
import po.Role_permission;
import po.User;
import service.RoleService;

@Controller
public class RoleController {
	@Autowired
	RoleService roleservice;
	
	@RequestMapping(value="/roles",method = RequestMethod.GET)
	@ResponseBody
	public DataGrid<Role> getPageRoles(@RequestParam("current") int current,@RequestParam("rowCount") int rowCount){
		DataGrid<Role> grid=new DataGrid<Role>();
		int total=roleservice.getroles().size();
		List<Role> list=roleservice.getpagerole(current, rowCount);
		grid.setCurrent(current);
		grid.setRowCount(rowCount);
		grid.setRows(list);
		grid.setTotal(total);
		return grid;
	}
	
	@RequestMapping(value="/allroles",method = RequestMethod.GET)
	@ResponseBody
	public List<Role> getallRoles(){
		List<Role> list=roleservice.getroles();
		return list;
	}
	
	@RequestMapping(value="/roles",method = RequestMethod.POST)
	@ResponseBody
	public Role addRole(@RequestBody Role r){
		Role role=roleservice.createRole(r);
		Long roleid=role.getRoleid();
		List<Role_permission> list=r.getRole_permissions();
		for(Role_permission rp:list){
			Long pid=rp.getPermission().getPermissionid();
			roleservice.correlationPermissions(roleid, pid);
		}
		return role;
	}
	
	@RequestMapping(value="/roles/{roleid}",method = RequestMethod.DELETE)
	@ResponseBody
	public MSG deleteRole(@PathVariable Long roleid){
		roleservice.deleteRole(roleid);
		roleservice.deleteroles(roleid);
		return new MSG("success");
	}
	
	@RequestMapping(value="/roles/{roleid}",method = RequestMethod.GET)
	@ResponseBody
	public Role getRole(@PathVariable Long roleid){
		Role r=roleservice.getrolebyid(roleid);
		return r;
	}
	
	@RequestMapping(value="/roles/{roleid}",method = RequestMethod.PUT)
	@ResponseBody
	public Role updateRole(@PathVariable Long roleid,@RequestBody Role r){
		roleservice.updateRole(r);
		roleservice.deleteroles(roleid);
		List<Role_permission> list=r.getRole_permissions();
		for(Role_permission rp:list){
			Long pid=rp.getPermission().getPermissionid();
			roleservice.correlationPermissions(roleid, pid);
		}
		return r;
	}
	
	
	
}
