package zw.cc.cn.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import zw.cc.cn.service.UserService;
import zw.cc.cn.user.module.Role;
import zw.cc.cn.user.module.User;
import zw.cc.cn.user.module.UserRole;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zwz
 * 2016/8/15.
 * zw.cc.cn.shiro
 */
public class MonitorRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    /**
     * 为当前登录的Subject授予角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前登录的用户
        User token = (User)SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        //角色code集合
        List<String> roles = userService.queryUserRole(token.getId());

        if(!roles.isEmpty()){
            simpleAuthorInfo.addRoles(roles);
            //角色权限集合
            List<String> permiss = userService.queryUserPermiss(token.getId());
            if(!permiss.isEmpty()){
                simpleAuthorInfo.addStringPermissions(permiss);
            }
        }
        return simpleAuthorInfo;
    }


    /**
     * 验证当前登录的Subject
     * 调用Subject.login()时执行此方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = new User();
        user.setPassword(String.valueOf(token.getPassword()));
        user.setUserName(String.valueOf(token.getUsername()));
        user = userService.validUser(user);
        if(null == user) {
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(), getName());
    }


    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     */
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }
}
