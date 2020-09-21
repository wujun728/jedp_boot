package controller;

import java.util.List;

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
import po.Permission;
import po.Role;
import service.PermissionService;

@Controller
public class PermissionController {
	@Autowired
	PermissionService permissionservice;
	
	@RequestMapping(value="/permissions",method = RequestMethod.GET)
	@ResponseBody
	public DataGrid<Permission> getpermissions(@RequestParam("current") int current,@RequestParam("rowCount") int rowCount){
		DataGrid<Permission> grid=new DataGrid<Permission>();
		int total=permissionservice.getPermissions().size();
		List<Permission> list=permissionservice.getPagePermissions(current, rowCount);
		grid.setCurrent(current);
		grid.setRowCount(rowCount);
		grid.setRows(list);
		grid.setTotal(total);
		return grid;
	}
	
	@RequestMapping(value="/allpermissions",method = RequestMethod.GET)
	@ResponseBody
	public List<Permission> getallpermissions(){
		List<Permission> list=permissionservice.getPermissions();
		return list;
	}
	
	@RequestMapping(value="/permissions/{pid}",method = RequestMethod.DELETE)
	@ResponseBody
	public MSG deletepermission(@PathVariable("pid") Long pid){
		permissionservice.deletePermission(pid);
		permissionservice.deletePermissions(pid);
		return new MSG("success");
	}
	
	@RequestMapping(value="/permissions",method = RequestMethod.POST)
	@ResponseBody
	public Permission addpermission(@RequestBody Permission p){
		Permission per=permissionservice.createPermission(p);
		return per;
	}
	
	@RequestMapping(value="/permissions/{pid}",method = RequestMethod.PUT)
	@ResponseBody
	public Permission updatepermission(@RequestBody Permission p){
		Permission per=permissionservice.updatePermission(p);
		return per;
	}
	
	
}
