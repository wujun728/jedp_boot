# SpringMVC
极速搭建SSM框架
### 项目功能特点
1. 异常统一处理
2. 前端信息统一返回
3. 集成Mybatis PageHelper插件
4. 集成分页插件，使用aop封装，支持智能分页
5. 含有mybatis generator代码自动生成器，基础代码一键生成。
6. 支持多数据源事务，支持多数据源分页查询
### 多数据源测试
 **Mapper接口代码** 
```java
    /**
     * 分页情况5：List<String>入参
     * @param jobIdList 对象入参
     * @param pageable 分页入参
     * @return 分页出参
     */
    @Datasource("oracleDataSource")
    Pageable<Employ> selectByJob5(@Param("jobIdList") List<String> jobIdList, Pageable pageable);

    /**
     * 分页情况6：List<String>入参
     * @param jobIdList 对象入参
     * @param pageable 分页入参
     * @return 分页出参
     */
    @Datasource("mysqlDataSource")
    Pageable<Employ> selectByJob6(@Param("jobIdList") List<String> jobIdList, Pageable pageable);
```
 **单元测试一代码** 
```java
    @Test
    public void testMulityDSPageQuery(){
        EmployMapper employMapper = applicationContext.getBean(EmployMapper.class);
        Pageable pageable = new Pageable(2,2);
        List<String> stringList = Arrays.asList("SALESMAN", "MANAGER");
        //oracle数据源查询
        Pageable<Employ> oracleEmpPage =  employMapper.selectByJob5(stringList,pageable);
        //mysql数据源查询
        Pageable<Employ> mysqlEmpPage =  employMapper.selectByJob6(stringList,pageable);
        Assert.assertNotEquals(0,oracleEmpPage.getTotalNum());
        Assert.assertNotEquals(0,oracleEmpPage.getPageData().size());
        Assert.assertNotEquals(0,mysqlEmpPage.getTotalNum());
        Assert.assertNotEquals(0,mysqlEmpPage.getPageData().size());
    }
```
 **_控制台日志_** 
```
13:21:39.570 [main] INFO  cn.kiwipeach.demo.plugin.aspect.MybatisDatasourceAspect - 数据源将切换至[oracleDataSource]进行查询.
13:21:39.575 [main] DEBUG cn.kiwipeach.demo.plugin.aspect.MybatisDatasourceAspect - DatasourceAspect beforedMethod....
13:21:39.767 [main] DEBUG cn.kiwipeach.demo.plugin.aspect.MybatisPageableAspect - cn.kiwipeach.demo.mapper.EmployMapper.selectByJob5:开始设置分页参数==>["SALESMAN","MANAGER"]
13:21:39.768 [main] DEBUG cn.kiwipeach.demo.plugin.aspect.MybatisPageableAspect - cn.kiwipeach.demo.mapper.EmployMapper.selectByJob5:开始设置分页大小==>{pageNo:2,pageSize:2}
13:21:39.793 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
13:21:39.812 [main] DEBUG org.mybatis.spring.SqlSessionUtils - SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@23c767e6] was not registered for synchronization because synchronization is not active
13:21:39.869 [main] DEBUG SQL_CACHE - Cache Hit Ratio [SQL_CACHE]: 0.0
13:21:39.949 [main] DEBUG org.mybatis.spring.transaction.SpringManagedTransaction - JDBC Connection [com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@741ac284] will not be managed by Spring
13:21:39.979 [main] DEBUG cn.kiwipeach.demo.mapper.EmployMapper.selectByJob5_COUNT - ==>  Preparing: SELECT count(0) FROM EMP WHERE job IN (?, ?) 
13:21:40.326 [main] DEBUG cn.kiwipeach.demo.mapper.EmployMapper.selectByJob5_COUNT - ==> Parameters: SALESMAN(String), MANAGER(String)
13:21:40.353 [main] DEBUG cn.kiwipeach.demo.mapper.EmployMapper.selectByJob5_COUNT - <==      Total: 1
13:21:40.355 [main] DEBUG cn.kiwipeach.demo.mapper.EmployMapper.selectByJob5 - ==>  Preparing: SELECT * FROM ( SELECT TMP_PAGE.*, ROWNUM ROW_ID FROM ( select EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO from EMP WHERE job IN ( ? , ? ) ) TMP_PAGE WHERE ROWNUM <= 4 ) WHERE ROW_ID > 2 
13:21:40.369 [main] DEBUG cn.kiwipeach.demo.mapper.EmployMapper.selectByJob5 - ==> Parameters: SALESMAN(String), MANAGER(String)
13:21:40.384 [main] DEBUG cn.kiwipeach.demo.mapper.EmployMapper.selectByJob5 - <==      Total: 2
13:21:40.384 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@23c767e6]
13:21:40.386 [main] DEBUG cn.kiwipeach.demo.plugin.aspect.MybatisPageableAspect - MybatisPageableAspect afterMethod....
13:21:40.489 [main] DEBUG cn.kiwipeach.demo.plugin.aspect.MybatisPageableAspect - cn.kiwipeach.demo.mapper.EmployMapper.selectByJob5:分页结束数据返回==>[{"comm":500,"deptno":30,"empno":7521,"ename":"WARD","hiredate":351619200000,"job":"SALESMAN","mgr":7698,"sal":1250},{"comm":0,"deptno":20,"empno":7566,"ename":"JONES","hiredate":354988800000,"job":"MANAGER","mgr":7839,"sal":2975}]
13:21:40.489 [main] DEBUG cn.kiwipeach.demo.plugin.aspect.MybatisDatasourceAspect - DatasourceAspect afterMethod....
13:21:40.489 [main] DEBUG cn.kiwipeach.demo.plugin.aspect.MybatisDatasourceAspect - DatasourceAspect afterReturnMethod....
13:21:40.494 [main] INFO  cn.kiwipeach.demo.plugin.aspect.MybatisDatasourceAspect - 数据源将切换至[mysqlDataSource]进行查询.
13:21:40.494 [main] DEBUG cn.kiwipeach.demo.plugin.aspect.MybatisDatasourceAspect - DatasourceAspect beforedMethod....
13:21:40.494 [main] DEBUG cn.kiwipeach.demo.plugin.aspect.MybatisPageableAspect - cn.kiwipeach.demo.mapper.EmployMapper.selectByJob6:开始设置分页参数==>["SALESMAN","MANAGER"]
13:21:40.494 [main] DEBUG cn.kiwipeach.demo.plugin.aspect.MybatisPageableAspect - cn.kiwipeach.demo.mapper.EmployMapper.selectByJob6:开始设置分页大小==>{pageNo:2,pageSize:2}
13:21:40.495 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
13:21:40.496 [main] DEBUG org.mybatis.spring.SqlSessionUtils - SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@12fcb2c3] was not registered for synchronization because synchronization is not active
13:21:40.500 [main] DEBUG SQL_CACHE - Cache Hit Ratio [SQL_CACHE]: 0.0
13:21:40.503 [main] DEBUG org.mybatis.spring.transaction.SpringManagedTransaction - JDBC Connection [com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@517704] will not be managed by Spring
13:21:40.503 [main] DEBUG cn.kiwipeach.demo.mapper.EmployMapper.selectByJob6_COUNT - ==>  Preparing: SELECT count(0) FROM EMP WHERE job IN (?, ?) 
13:21:40.603 [main] DEBUG cn.kiwipeach.demo.mapper.EmployMapper.selectByJob6_COUNT - ==> Parameters: SALESMAN(String), MANAGER(String)
13:21:40.608 [main] DEBUG cn.kiwipeach.demo.mapper.EmployMapper.selectByJob6_COUNT - <==      Total: 1
13:21:40.610 [main] DEBUG cn.kiwipeach.demo.mapper.EmployMapper.selectByJob6 - ==>  Preparing: select EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO from EMP WHERE job IN ( ? , ? ) LIMIT 2,2 
13:21:40.615 [main] DEBUG cn.kiwipeach.demo.mapper.EmployMapper.selectByJob6 - ==> Parameters: SALESMAN(String), MANAGER(String)
13:21:40.622 [main] DEBUG cn.kiwipeach.demo.mapper.EmployMapper.selectByJob6 - <==      Total: 2
13:21:40.623 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@12fcb2c3]
13:21:40.623 [main] DEBUG cn.kiwipeach.demo.plugin.aspect.MybatisPageableAspect - MybatisPageableAspect afterMethod....
13:21:40.623 [main] DEBUG cn.kiwipeach.demo.plugin.aspect.MybatisPageableAspect - cn.kiwipeach.demo.mapper.EmployMapper.selectByJob6:分页结束数据返回==>[{"comm":0,"deptno":20,"empno":7566,"ename":"JONES","hiredate":354988800000,"job":"MANAGER","mgr":7839,"sal":2975},{"comm":1400,"deptno":30,"empno":7654,"ename":"MARTIN","hiredate":370454400000,"job":"SALESMAN","mgr":7698,"sal":1250}]
```