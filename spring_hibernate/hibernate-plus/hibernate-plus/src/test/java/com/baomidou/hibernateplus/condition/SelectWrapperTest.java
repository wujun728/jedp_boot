/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2016 Caratacus
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.baomidou.hibernateplus.condition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

import com.baomidou.hibernateplus.enums.SqlLike;

/**
 * <p>
 * 条件查询测试
 * </p>
 *
 * @author Caratacus
 * @date 2016-11-29
 */
public class SelectWrapperTest {

    /*
     * User 查询包装器
     */
    private final SelectWrapper ew = new SelectWrapper();

    @Test
    public void test() {
        /*
		 * 无条件测试
		 */
        Assert.assertEquals("", ew.toString());
    }

    @Test
    public void test11() {
		/*
		 * 实体带where ifneed
		 */
        ew.where("name={0}", "'123'").addFilterIfNeed(false, "id=12");
        String sqlSegment = ew.toString();
        System.err.println("test11 = " + sqlSegment);
        Assert.assertEquals("WHERE (name='123')", sqlSegment);
    }

    @Test
    public void test12() {
		/*
		 * 实体带where orderby
		 */
        ew.where("name={0}", "'123'").orderBy("id", false);
        String sqlSegment = ew.toString();
        System.err.println("test12 = " + sqlSegment);
        Assert.assertEquals("WHERE (name='123')\nORDER BY id DESC", sqlSegment);
    }

    @Test
    public void test13() {
		/*
		 * 实体排序
		 */
        ew.orderBy("id", false);
        String sqlSegment = ew.toString();
        System.err.println("test13 = " + sqlSegment);
        Assert.assertEquals("ORDER BY id DESC", sqlSegment);
    }

    @Test
    public void test21() {
		/*
		 * 无实体 where ifneed orderby
		 */
        ew.where("name={0}", "'123'").addFilterIfNeed(false, "id=1").orderBy("id");
        String sqlSegment = ew.toString();
        System.err.println("test21 = " + sqlSegment);
        Assert.assertEquals("WHERE (name='123')\nORDER BY id", sqlSegment);
    }

    @Test
    public void test22() {
        ew.where("name={0}", "'123'").orderBy("id", false);
        String sqlSegment = ew.toString();
        System.err.println("test22 = " + sqlSegment);
        Assert.assertEquals("WHERE (name='123')\nORDER BY id DESC", sqlSegment);
    }

    @Test
    public void test23() {
		/*
		 * 无实体查询，只排序
		 */
        ew.orderBy("id", false);
        String sqlSegment = ew.toString();
        System.err.println("test23 = " + sqlSegment);
        Assert.assertEquals("ORDER BY id DESC", sqlSegment);
    }

    @Test
    public void testNoTSQL() {
		/*
		 * 实体 filter orderby
		 */
        ew.addFilter("name={0}", "'123'").orderBy("id,name");
        String sqlSegment = ew.toString();
        System.err.println("testNoTSQL = " + sqlSegment);
        Assert.assertEquals("WHERE (name='123')\nORDER BY id,name", sqlSegment);
    }

    @Test
    public void testNoTSQL1() {
		/*
		 * 非 T-SQL 无实体查询
		 */
        ew.addFilter("name={0}", "'123'").addFilterIfNeed(false, "status=?", "1");
        String sqlSegment = ew.toString();
        System.err.println("testNoTSQL1 = " + sqlSegment);
        Assert.assertEquals("WHERE (name='123')", sqlSegment);
    }

    @Test
    public void testTSQL11() {
		/*
		 * 实体带查询使用方法 输出看结果
		 */
        ew.where("name=?", "'zhangsan'").and("id=1").orNew("status=?", "0").or("status=1").notLike("nlike", "notvalue")
                .andNew("new=xx").like("hhh", "ddd").andNew("pwd=11").isNotNull("n1,n2").isNull("n3").groupBy("x1")
                .groupBy("x2,x3").having("x1=11").having("x3=433").orderBy("dd").orderBy("d1,d2");
        System.out.println(ew.toString());
        Assert.assertEquals("WHERE (name=? AND id=1) \n" + "OR (status=? OR status=1 AND nlike NOT LIKE '%notvalue%') \n"
                + "AND (new=xx AND hhh LIKE '%ddd%') \n" + "AND (pwd=11 AND n1 IS NOT NULL AND n2 IS NOT NULL AND n3 IS NULL)\n"
                + "GROUP BY x1, x2,x3\n" + "HAVING (x1=11 AND x3=433)\n" + "ORDER BY dd, d1,d2", ew.toString());
    }

    @Test
    public void testNull() {
        ew.orderBy(null);
        String sqlPart = ew.toString();
        Assert.assertEquals("", sqlPart);
    }

    @Test
    public void testNull2() {
        ew.like(null, null).where("aa={0}", "'bb'").orderBy(null);
        String sqlPart = ew.toString();
        Assert.assertEquals("WHERE (aa='bb')", sqlPart);
    }

    /**
     * 测试带单引号的值是否不会再次添加单引号
     */
    @Test
    public void testNul14() {
        ew.where("id={0}", "'11'").and("name={0}", 22);
        String sqlPart = ew.toString();
        System.out.println("sql ==> " + sqlPart);
        Assert.assertEquals("WHERE (id='11' AND name=22)", sqlPart);
    }

    /**
     * 测试带不带单引号的值是否会自动添加单引号
     */
    @Test
    public void testNul15() {
        ew.where("id={0} and ids = {1}", "11", 22).and("name={0}", 222222222);
        String sqlPart = ew.toString();
        System.out.println("sql ==> " + sqlPart);
        Assert.assertEquals("WHERE (id='11' and ids = 22 AND name=222222222)", sqlPart);
    }

    /**
     * 测试EXISTS
     */
    @Test
    public void testNul16() {
        ew.notExists("(select * from user)");
        String sqlPart = ew.toString();
        System.out.println("sql ==> " + sqlPart);
        Assert.assertEquals("WHERE ( NOT EXISTS ((select * from user)))", sqlPart);
    }

    /**
     * 测试NOT IN
     */
    @Test
    public void test17() {
        List<String> list = new ArrayList<String>();
        list.add("'1'");
        list.add("'2'");
        list.add("'3'");
        ew.notIn("test_type", list);
        String sqlPart = ew.toString();
        System.out.println("sql ==> " + sqlPart);
        Assert.assertEquals("WHERE (test_type NOT IN ('1','2','3'))", sqlPart);
    }

    /**
     * 测试IN
     */
    @Test
    public void testNul18() {
        List<Long> list = new ArrayList<Long>();
        list.add(111111111L);
        list.add(222222222L);
        list.add(333333333L);
        ew.in("test_type", list);
        String sqlPart = ew.toString();
        System.out.println("sql ==> " + sqlPart);
        Assert.assertEquals("WHERE (test_type IN (111111111,222222222,333333333))", sqlPart);
    }

    /**
     * 测试IN
     */
    @Test
    public void test18() {
        Set<Long> list = new TreeSet<Long>();
        list.add(111111111L);
        list.add(222222222L);
        list.add(333333333L);
        ew.in("test_type", list);
        String sqlPart = ew.toString();
        System.out.println("sql ==> " + sqlPart);
        Assert.assertEquals("WHERE (test_type IN (111111111,222222222,333333333))", sqlPart);
    }

    /**
     * 测试BETWEEN AND
     */
    @Test
    public void testNul19() {
        String val1 = "11";
        String val2 = "33";
        ew.between("test_type", val1, val2);
        String sqlPart = ew.toString();
        System.out.println("sql ==> " + sqlPart);
        Assert.assertEquals("WHERE (test_type BETWEEN '11' AND '33')", sqlPart);
    }

    /**
     * 测试Escape
     */
    @Test
    public void testEscape() {
        String val1 = "'''";
        String val2 = "\\";
        ew.between("test_type", val1, val2);
        String sqlPart = ew.toString();
        System.out.println("sql ==> " + sqlPart);
        Assert.assertEquals("WHERE (test_type BETWEEN '\\'' AND '\\\\')", sqlPart);
    }

    /**
     * 测试Escape
     */
    @Test
    public void testInstance() {
        String val1 = "'''";
        String val2 = "\\";
        String sqlPart = SelectWrapper.instance().between("test_type", val1, val2).toString();
        System.out.println("sql ==> " + sqlPart);
        Assert.assertEquals("WHERE (test_type BETWEEN '\\'' AND '\\\\')", sqlPart);
    }

    /**
     * 测试Qbc
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testQbc() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("allEq1", "22");
        map.put("allEq2", 3333);
        map.put("allEq3", 66.99);
        String sqlPart = SelectWrapper.instance().gt("gt", 1).le("le", 2).lt("le", 3).ge("ge", 4).eq("eq", 5).allEq(map)
                .toString();
        System.out.println("sql ==> " + sqlPart);
        Assert.assertEquals(
                "WHERE (gt > 1 AND le <= 2 AND le < 3 AND ge >= 4 AND eq = 5 AND allEq3 = 66.99 AND allEq1 = '22' AND allEq2 = 3333)",
                sqlPart);
    }

    /**
     * 测试LIKE
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testlike() {
        String sqlPart = SelectWrapper.instance().like("default", "default", SqlLike.DEFAULT)
                .like("left", "left", SqlLike.LEFT).like("right", "right", SqlLike.RIGHT).toString();
        System.out.println("sql ==> " + sqlPart);
        Assert.assertEquals("WHERE (default LIKE '%default%' AND left LIKE '%left' AND right LIKE 'right%')", sqlPart);
    }
}
