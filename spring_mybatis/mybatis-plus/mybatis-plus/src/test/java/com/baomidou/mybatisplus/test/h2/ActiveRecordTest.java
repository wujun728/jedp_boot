package com.baomidou.mybatisplus.test.h2;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.internal.matchers.GreaterThan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.test.h2.entity.persistent.H2Student;
import com.baomidou.mybatisplus.test.h2.service.IH2StudentService;


/**
 * ActiveRecord 测试
 *
 * @author nieqiurong 2018/7/27.
 */
@FixMethodOrder(MethodSorters.JVM)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:h2/spring-test-h2.xml"})
public class ActiveRecordTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActiveRecordTest.class);

    @Autowired
    private IH2StudentService h2StudentService;

    @Test
    @Transactional
    public void testInsert() {
        H2Student student = new H2Student(null, "测试学生", 2);
        Assert.assertTrue(student.insert());
        Assert.assertTrue(student.insert());
    }

    @Test
    public void testUpdate() {
        H2Student student = new H2Student(1L, "Tom长大了", 2);
        Assert.assertTrue(student.updateById());
        student.setName("不听话的学生");
        Assert.assertTrue(student.update(new QueryWrapper<H2Student>().gt("id", 10)));
    }

    @Test
    public void testSelect() {
        H2Student student = new H2Student();
        student.setId(1L);
        Assert.assertNotNull(student.selectById());
        Assert.assertNotNull(student.selectById(1L));
    }

    @Test
    public void testSelectList() {
        H2Student student = new H2Student();
        List<H2Student> students = student.selectList(new QueryWrapper<>(student));
        students.forEach($this -> LOGGER.info("用户信息:{}", $this));
        Assert.assertThat(students.size(), new GreaterThan<>(1));
    }

    @Test
    public void testSelectPage() {
        IPage<H2Student> page = new Page<>(1, 10);
        H2Student student = new H2Student();
        page = student.selectPage(page, new QueryWrapper<>(student));
        List<H2Student> records = page.getRecords();
        LOGGER.info("总数:{}", page.getTotal());
        records.forEach($this -> LOGGER.info("用户信息:{}", $this));
        Assert.assertTrue(page.getTotal() > 1);
    }

    @Test
    public void testSelectCount() {
        H2Student student = new H2Student();
        int count = new H2Student().selectCount(new QueryWrapper<>(student));
        LOGGER.info("count:{}", count);
        Assert.assertTrue(count > 1);
    }

    @Test
    public void testInsertOrUpdate() {
        H2Student student = new H2Student(2L, "Jerry也长大了", 2);
        Assert.assertTrue(student.insertOrUpdate());
        student.setId(null);
        Assert.assertTrue(student.insertOrUpdate());
    }

    @Test
    public void testSelectAll() {
        H2Student student = new H2Student();
        List<H2Student> students = student.selectAll();
        Assert.assertNotNull(students);
        students.forEach($this -> LOGGER.info("用户信息:{}", $this));
    }

    @Test
    public void testSelectOne() {
        H2Student student = new H2Student();
        Assert.assertNotNull(student.selectOne(new QueryWrapper<>()));
    }

    @Test
    public void testTransactional() {
        try {
            h2StudentService.testTransactional();
        } catch (MybatisPlusException e) {
            List<H2Student> students = new H2Student().selectList(new QueryWrapper<H2Student>().lambda().like(H2Student::getName, "tx"));
            Assert.assertTrue(CollectionUtils.isEmpty(students));
        }
    }

    @Test
    public void testDelete() {
        H2Student student = new H2Student();
        student.setId(2L);
        Assert.assertTrue(student.deleteById());
        Assert.assertTrue(student.deleteById(12L));
        Assert.assertTrue(student.delete(new QueryWrapper<H2Student>().gt("id", 10)));
    }
}
