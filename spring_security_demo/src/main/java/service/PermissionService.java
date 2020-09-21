package service;

import java.util.List;

import po.Permission;


public interface PermissionService {
    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);
    public void deletePermissions(Long permissionId);
    public List<Permission> getPermissions();
    public Permission getPermissionByid(Long permissionid);
    public Permission updatePermission(Permission permission);
    public List<Permission> getPagePermissions(int pagenum,int pagesize);
}
