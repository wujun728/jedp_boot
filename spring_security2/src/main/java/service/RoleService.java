package service;

import java.util.List;

import po.Role;


public interface RoleService {


    public Role createRole(Role role);
    public void deleteRole(Long roleId);

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
    
    public List<Role> getroles();
    
    public List<Role> getpagerole(int pagenum, int pagesize);
    
    public void deleteroles(Long roleid);//删除该角色的所有权限关联
    
    public Role getrolebyid(Long roleid);
    
    public Role updateRole(Role r);
}
