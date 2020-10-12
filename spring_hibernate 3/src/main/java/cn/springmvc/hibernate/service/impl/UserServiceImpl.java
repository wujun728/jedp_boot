package cn.springmvc.hibernate.service.impl;

import java.util.Calendar;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.hibernate.common.constants.Constants;
import cn.springmvc.hibernate.common.exception.BusinessException;
import cn.springmvc.hibernate.common.utils.salt.Digests;
import cn.springmvc.hibernate.common.utils.salt.Encodes;
import cn.springmvc.hibernate.dao.DaoService;
import cn.springmvc.hibernate.dao.UserDaoService;
import cn.springmvc.hibernate.model.Role;
import cn.springmvc.hibernate.model.User;
import cn.springmvc.hibernate.model.UserRole;
import cn.springmvc.hibernate.service.UserService;
import cn.springmvc.hibernate.web.command.UserCommand;

/**
 * @author Vincent.wang
 *
 */
@Service
public class UserServiceImpl implements UserService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;

    @Autowired
    DaoService daoService;

    @Autowired
    UserDaoService userDaoService;

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }

    @Override
    public void addUser(User user, Role role) {
        if (user == null || role == null) {
            throw new BusinessException("user.registr.error", "注册信息错误");
        }

        if (StringUtils.isBlank(user.getName()) || StringUtils.isBlank(user.getPassword())) {
            throw new BusinessException("user.registr.error", "注册信息错误");
        }

        if (StringUtils.isBlank(role.getId())) {
            throw new BusinessException("user.registr.error", "用户未指定所属角色");
        }

        Role r = daoService.getByPrimaryKey(Role.class, role.getId());
        if (r == null) {
            throw new BusinessException("user.registr.error", "用户未指定所属组织或角色");
        }

        entryptPassword(user);
        user.setStatus(Constants.STATUS_VALID);
        user.setCreateTime(Calendar.getInstance().getTime());
        daoService.save(user);

        UserRole ur = new UserRole();
        ur.setRoleId(r.getId());
        ur.setUserId(user.getId());
        daoService.save(ur);
    }

    @Override
    public void updatePassword(UserCommand userCommand, User user) {
        if (log.isDebugEnabled()) {
            log.debug("## update User password.");
        }
        User u = daoService.getByPrimaryKey(User.class, user.getId());
        u.setPassword(userCommand.getPasswordAgain());
        entryptPassword(u);
        u.setModifyTime(Calendar.getInstance().getTime());
        daoService.update(u);
    }

    @Override
    public User findLocalUserByName(String username) {
        return userDaoService.findLocalUserByName(username);
    }

    @Override
    public void updateUserLastLoginTime(User user) {
        User u = daoService.getByPrimaryKey(User.class, user.getId());
        if (u != null) {
            u.setLastLoginTime(Calendar.getInstance().getTime());
            userDaoService.updateUserLastLoginTime(u);
        }
    }

    @Override
    public Collection<User> findUsers() {
        return userDaoService.findUsers();
    }

    @Override
    public Collection<User> findEmp(String shopId, String empName) {
        return userDaoService.findEmp(shopId, empName);
    }
}