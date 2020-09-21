/*
 * Copyright 2017 KiWiPeach.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.kiwipeach.demo.mapper;

import cn.kiwipeach.demo.SpringJunitBase;
import cn.kiwipeach.demo.domain.Employ;
import cn.kiwipeach.demo.framework.response.Pageable;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Create Date: 2018/01/17
 * Description: Mybatis分页插件测试,详情请见官网https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md
 *
 * @author kiwipeach [1099501218@qq.com]
 */
public class PageHelperTest extends SpringJunitBase {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;
    private static final String CONDITION_JOB = "SALESMAN";

    /**
     * 初始化
     */
    @Before
    public void init() {
        sqlSession = sqlSessionFactory.openSession();
    }

    /**
     * 销毁
     */
    @After
    public void destory() {
        sqlSession.close();
    }

    /**
     * 第一种，RowBounds方式的调用
     *
     * @throws Exception
     */
    @Test
    public void testPageSelectByRowBounds() throws Exception {
        Object params = CONDITION_JOB;
        List<Employ> employList = sqlSession.selectList("cn.kiwipeach.demo.mapper.EmployMapper.selectByJob", params, new RowBounds(0, 5));
        showEmployList(employList);
    }

    /**
     * 第二种，Mapper接口方式的调用，推荐这种使用方式。
     */

    @Test
    public void testPageSelectByMapper1() {
        EmployMapper employMapper = sqlSession.getMapper(EmployMapper.class);
        Page<Object> page = PageHelper.startPage(1, 2);
        Pageable pageable = new Pageable(3,2);
        List<Employ> employList = employMapper.selectByJob1(CONDITION_JOB,pageable);
        showEmployList(employList);
        System.out.println("第" + page.getPageNum() + "页/共" + page.getPages() + "页");
        System.out.println("页码大小：" + page.getPageSize());
        System.out.println("总记录数：" + page.getTotal());
    }

    @Autowired
    private EmployMapper employMapper;

    /**
     * 第三种，Mapper接口方式的调用，推荐这种使用方式。
     */
    @Test
    public void testPageSelectByMapper2() {
//        EmployMapper employMapper = sqlSession.getMapper(EmployMapper.class);
//        Page<Object> offsetPage = PageHelper.offsetPage(1, 2);
        Pageable pageable = new Pageable(1,2);
        List<Employ> employPageable = employMapper.selectByJob1(CONDITION_JOB,pageable);
        showEmployList(employPageable);
//        int curNo = employPageable.getCurNo();
//        int pageSize = employPageable.getPageSize();
//        int total = (int) employPageable.getTotalNum();
//        int totalPage = ((total+pageSize)/pageSize)-1;
//        System.out.println("第" + curNo + "页/共" + totalPage + "页");
//        System.out.println("分页大小：" + pageSize);
    }

    /**
     * 第四种，在Mapper接口中使用注解 @Param("pageNum") int pageNum, @Param("pageSize") int pageSize
     */
    @Test
    public void testPageSelectByAnnotation() {
        EmployMapper employMapper = sqlSession.getMapper(EmployMapper.class);
        Employ employ = new Employ();
        employ.setJob(CONDITION_JOB);
        List<Employ> employList = employMapper.selectByJobWithAnnotation(employ, 2, 2);
        showEmployList(employList);
    }

    /**
     * 第六种，ISelect 接口方式,jdk1.6/1.7
     */
    @Test
    public void testPageSelectByISelect() {
        EmployMapper employMapper = sqlSession.getMapper(EmployMapper.class);
//        Page<Employ> page = PageHelper.startPage(1, 2).doSelectPage(new ISelect() {
//            @Override
//            public void doSelect() {
//                employMapper.selectByJob(CONDITION_JOB);
//            }
//        });
//        System.out.println(page);
    }

    /**
     * jdk1.8的写法
     */
    @Test
    public void testPageSelectByLambda() {
        EmployMapper employMapper = sqlSession.getMapper(EmployMapper.class);
//        Page<Employ> page = PageHelper.startPage(1, 2).doSelectPage(() -> employMapper.selectByJob(CONDITION_JOB));
//        System.out.println("当前页码：" + page.getPageNum());
//        System.out.println("页码大小：" + page.getPageSize());
//        System.out.println("总页码数：" + page.getPages());
//        System.out.println("总记录数：" + page.getTotal());

    }


    @Test
    public void testPageSelectByPageInfo() {
//        EmployMapper employMapper = sqlSession.getMapper(EmployMapper.class);
//        Page<Object> page = PageHelper.startPage(4, 1);
//        List<Employ> employList = employMapper.selectByJob(CONDITION_JOB);
//        showEmployList(employList);
//        //Page基础性功能
//        System.out.println("第" + page.getPageNum() + "页/共" + page.getPages() + "页");
//        System.out.println("页码大小：" + page.getPageSize());
//        System.out.println("总记录数：" + page.getTotal());
//        //PageInfo增强性功能,设置连续分页大小为4
//        PageInfo<Employ> pageInfo = new PageInfo<Employ>(employList,4);
//        System.out.println("前一页："+pageInfo.getPrePage());
//        System.out.println("后一页："+pageInfo.getNextPage());
//        System.out.println("是否为最后一页："+pageInfo.isIsLastPage());
//        System.out.println("是否为第一页："+pageInfo.isIsFirstPage());
//        int nums[] = pageInfo.getNavigatepageNums();
//        for (int i = 0; i < nums.length; i++) {
//            System.out.println(nums[i]);
//        }

    }

    /**
     * 打印员工数据列表
     *
     * @param employList
     */
    public void showEmployList(List<Employ> employList) {
        for (Employ employ : employList) {
            System.out.println(JSON.toJSONString(employ));
        }
    }



}
