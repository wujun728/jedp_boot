package cn.springmvc.hibernate.common.redis;

import cn.springmvc.hibernate.model.User;

/**
 * @author Vincent.wang
 *
 */
public interface UserRedisService {

    public void add(User user);

    public User getUser(String key);

}
