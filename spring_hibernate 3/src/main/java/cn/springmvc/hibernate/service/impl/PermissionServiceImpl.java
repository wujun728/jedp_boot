package cn.springmvc.hibernate.service.impl;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.hibernate.common.exception.BusinessException;
import cn.springmvc.hibernate.dao.PermissionDaoService;
import cn.springmvc.hibernate.model.Permission;
import cn.springmvc.hibernate.model.User;
import cn.springmvc.hibernate.service.PermissionService;

/**
 * @author Vincent.wang
 *
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDaoService permissionDaoService;

    @Override
    public Collection<Permission> getPermissions(User user) {
        return permissionDaoService.getPermissions(user);
    }

    @Override
    public void addPermission(Permission permission) {
        if (permission == null || StringUtils.isBlank(permission.getKey()) || StringUtils.isBlank(permission.getParentKey()) || StringUtils.isBlank(permission.getName())) {
            throw new BusinessException("## 创建菜单出错；菜单项数据不完整，无法进行创建。");
        }
        permissionDaoService.addPermission(permission);
    }

}
