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

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.baomidou.hibernateplus.entity.EntityInfo;
import com.baomidou.hibernateplus.entity.page.Page;
import com.baomidou.hibernateplus.model.AppTestModel;
import com.baomidou.hibernateplus.utils.EntityInfoUtils;
import com.baomidou.hibernateplus.utils.SqlUtils;

/**
 * <p>
 * SqlUtils工具类
 * </p>
 *
 * @author Caratacus
 * @Date 2016-11-13
 */
public class SqlUtilsTest {

    private final static EntityInfo entityInfo;

    static {
        entityInfo = EntityInfoUtils.initEntityInfo(AppTestModel.class);
    }

    /**
     * 测试select的count语句 复杂 orderby
     */
    @Test
    public void sqlCountOptimize1() {
        String countsql = SqlUtils
                .sqlCountOptimize("select * from user a left join (select uuid from user2) b on b.id = a.aid where a=1 order by (select 1 from dual)");
        System.out.println(countsql);
        Assert.assertEquals("SELECT COUNT(0) FROM user a LEFT JOIN (SELECT uuid FROM user2) b ON b.id = a.aid WHERE a = 1",
                countsql);
    }

    /**
     * 测试select的count语句 distinct
     */
    @Test
    public void sqlCountOptimize2() {
        String countsql = SqlUtils
                .sqlCountOptimize("select distinct * from user a left join (select uuid from user2) b on b.id = a.aid where a=1 order by (select 1 from dual)");
        System.out.println(countsql);
        Assert.assertEquals(
                "SELECT COUNT(0) FROM ( SELECT DISTINCT * FROM user a LEFT JOIN (SELECT uuid FROM user2) b ON b.id = a.aid WHERE a = 1 ) TOTAL",
                countsql);
    }

    /**
     * 测试select的count语句 group by
     */
    @Test
    public void sqlCountOptimize3() {

        String countsql = SqlUtils
                .sqlCountOptimize("select * from user a left join (select uuid from user2) b on b.id = a.aid where a=1 group by a.id order by (select 1 from dual)");
        System.out.println(countsql);
        Assert.assertEquals(
                "SELECT COUNT(0) FROM ( SELECT * FROM user a LEFT JOIN (SELECT uuid FROM user2) b ON b.id = a.aid WHERE a = 1 GROUP BY a.id ORDER BY (SELECT 1 FROM dual) ) TOTAL",
                countsql);
    }

    /**
     * 测试SQL拼接Order By
     */
    @Test
    public void concatOrderBy() {
        Page page = new Page();
        page.setOrderByField("orderfield");
        String sql = SqlUtils.concatOrderBy("select * from user", page);
        System.out.println(sql);
        Assert.assertEquals("select * from user ORDER BY orderfield ASC ", sql);
    }

    /**
     * 测试SQL实体映射List sql
     */
    @Test
    public void sqlEntityList() {
        Page page = new Page(1, 10);
        page.setOrderByField("fileld");
        String sql = SqlUtils.sqlEntityList(AppTestModel.class, SelectWrapper.instance().eq("s_id", 1), page);
        System.out.println(sql);
        Assert.assertEquals("SELECT * FROM app_table WHERE (s_id = 1)\n" + "ORDER BY fileld ASC", sql);

    }

    /**
     * 获取普通List sql
     */
    @Test
    public void sqlList() {
        Page page = new Page(1, 10);
        page.setOrderByField("fileld");
        String sql = SqlUtils.sqlList(AppTestModel.class, false, SelectWrapper.instance().eq("s_id", 1), page);
        System.out.println(sql);
        Assert.assertEquals("SELECT s_id AS id,s_str AS str FROM app_table WHERE (s_id = 1)\n" + "ORDER BY fileld ASC", sql);
    }

    /**
     * 获取普通List sql
     *
     */
    @Test
    public void sqlList2() {
        Page page = new Page(1, 10);
        page.setOrderByField("fileld");
        String sql = SqlUtils.sqlList(AppTestModel.class, true, SelectWrapper.instance().eq("s_id", 1), page);
        System.out.println(sql);
        Assert.assertEquals("SELECT * FROM app_table WHERE (s_id = 1)\n" + "ORDER BY fileld ASC", sql);
    }

    @Test
    public void sqlCount() {
        String sql = SqlUtils.sqlCount(AppTestModel.class, SelectWrapper.instance().eq("s_id", 1));
        System.out.println(sql);
        Assert.assertEquals("SELECT COUNT(0) FROM app_table WHERE (s_id = 1)", sql);
    }

    /**
     * 根据Class获取表名
     */
    @Test
    public void getTableName() {
        String tableName = SqlUtils.getTableName(AppTestModel.class);
        Assert.assertEquals("app_table", tableName);

    }

    /**
     * 获取删除sql
     */
    @Test
    public void sqlDelete() {
        String sql = SqlUtils.sqlDelete(AppTestModel.class, SelectWrapper.instance().eq("s_id", 1));
        System.out.println(sql);
        Assert.assertEquals("DELETE FROM app_table WHERE (s_id = 1)", sql);
    }

    /**
     * 获取Update SQL
     */
    @Test
    public void sqlUpdate() {
        Map<String, String> map = new HashMap();
        map.put("s_str", "222");
        String sql = SqlUtils.sqlUpdate(AppTestModel.class, UpdateWrapper.instance().sets(map).eq("s_id", 1));
        System.out.println(sql);
        Assert.assertEquals("UPDATE app_table SET s_str = 222 WHERE (s_id = 1)", sql);
    }

    /**
     * 获取select
     */
    @Test
    public void select() {
        String select = SqlUtils.select(AppTestModel.class);
        Assert.assertEquals("s_id AS id,s_str AS str", select);
    }

}
