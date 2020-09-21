package cn.springmvc.hibernate.dao;

import java.util.Collection;

import cn.springmvc.hibernate.model.User;

/**
 * 用户Dao
 * @author Vincent.wang
 *
 */
public interface UserDaoService {

	/**
	 * 根据用户名查询用户
	 *
	 * @param username
	 *            用户名
	 * @return user 用户
	 */
	public User findLocalUserByName(String username);

	/**
	 * 查询店铺所有用户
	 *
	 * @param id
	 *            店铺ID
	 * @return
	 * @author wangxin
	 */
	public Collection<User> findUserByShop(String id);

	/**
	 * 查询组织下所有客服员工
	 *
	 * @return
	 */
	public Collection<User> findUsers();

	/**
	 * 更新用户最后登录时间
	 *
	 * @param user
	 *            用户
	 */
	public void updateUserLastLoginTime(User user);

	/**
	 * 根据条件（店铺、名称）查询客服人员
	 *
	 * @param shopId
	 *            店铺ID
	 * @param empName
	 *            客服人员名称
	 * @return
	 */
	public Collection<User> findEmp(String shopId, String empName);

}
