package com.BBS.Filter.ShiroSecurity;

import com.BBS.pojo.User;
import com.BBS.service.GeneralService;
import com.BBS.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhanglq
 * @version 1.0
 * @date 2019/11/15 11:34
 * @info shiro权限认证类
 */
public class AuthenticationFilter extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private GeneralService generalService;

    /**
     * shiro授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 使用Shiro提供的方法获取用户名称
        User user = (User) getAvailablePrincipal(principalCollection);

        if (user != null) {
            List list = generalService.findForJdbc("select roleId  from user_role where user_id = ?",new Object[]{user.getId()});
            String roleId = (String) list.get(0);

            // 获取用户的权限
            List roleList = generalService.findForJdbc("select res_id from role_res where role_id = ?", new Object[]{roleId});

            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

            if (roleId != null) {
                info.addRole(roleId);
            }
            if (roleList != null) {
                info.addStringPermission(roleList.toString());
            }
            return info;

        }


        return null;
    }
    /**
     * shiro认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        String loginName = token.getUsername();
        if (loginName != null &&! "".equals(loginName)) {
            //通过登录名获取用户
            User user = userService.findByLoginName(loginName);
            if (user != null) {
                // 如果身份认证验证成功，返回一个AuthenticationInfo实现
                return new SimpleAuthenticationInfo(user.getLoginname(),user.getPassword(),user.getId());
            }
        }
        return null;
    }
}
