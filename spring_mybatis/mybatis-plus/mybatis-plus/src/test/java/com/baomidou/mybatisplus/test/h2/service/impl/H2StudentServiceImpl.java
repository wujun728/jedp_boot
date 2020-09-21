package com.baomidou.mybatisplus.test.h2.service.impl;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;
import com.baomidou.mybatisplus.test.h2.entity.mapper.H2StudentMapper;
import com.baomidou.mybatisplus.test.h2.entity.persistent.H2Student;
import com.baomidou.mybatisplus.test.h2.service.IH2StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2018/9/6.
 */
@Service
public class H2StudentServiceImpl extends ServiceImpl<H2StudentMapper,H2Student> implements IH2StudentService {

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void testTransactional() {
        new H2Student(null, "tx1", 2).insert();
        new H2Student(null, "tx2", 2).insert();
        new H2Student(null, "tx3", 2).insert();
        throw new MybatisPlusException("测试AR事务回滚");
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void testSqlRunnerTransactional() {
        SqlRunner.db().insert("INSERT INTO h2student ( name, age ) VALUES ( {0}, {1} )","sqlRunnerTx1",2);
        SqlRunner.db().insert("INSERT INTO h2student ( name, age ) VALUES ( {0}, {1} )","sqlRunnerTx2",2);
        throw new MybatisPlusException("测试普通插入事务回滚");
    }
}
