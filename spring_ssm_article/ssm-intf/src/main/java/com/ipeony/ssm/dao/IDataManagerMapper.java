/**
 * CCC 2009-2013 All Rights Reserved.
 */
package com.ipeony.ssm.dao;

import com.ipeony.ssm.entity.User;

/**
 * 
 * 功能描述： 数据管理统一接口
 * @author Wujun
 * @version 1.0.0
 * @date 2013年11月6日 下午6:31:20
 */

public interface IDataManagerMapper {
    public User getUser(String id);
}
