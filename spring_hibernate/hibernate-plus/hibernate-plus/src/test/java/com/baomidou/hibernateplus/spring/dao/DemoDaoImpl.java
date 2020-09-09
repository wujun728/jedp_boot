package com.baomidou.hibernateplus.spring.dao;

import com.baomidou.hibernateplus.dao.impl.DaoImpl;
import com.baomidou.hibernateplus.spring.po.Tdemo;

/**
 * <p>
 * DemoDaoImpl
 * </p>
 *
 * @author Caratacus
 * @date 2016-12-2
 */
public class DemoDaoImpl extends DaoImpl<Tdemo> implements DemoDao {

    /**
     * 是否获取当前事务的Session
     *
     * @return
     */
    protected Boolean isCurrent() {
        return false;
    }

}
