package cn.kiwipeach.demo.service;


import cn.kiwipeach.demo.domain.Employ;
import cn.kiwipeach.demo.domain.dto.EmployDeptDTO;
import cn.kiwipeach.demo.framework.response.Pageable;

import java.math.BigDecimal;
import java.util.List;

/**
 * Create Date: 2017/11/01
 * Description: 员工管理相关接口服务
 *
 * @author kiwipeach [1099501218@qq.com]
 */
public interface EmployService {
    /**
     * 查询员工信息
     * @param empno 员工编号
     * @return 员工信息
     */
    Employ queryEmploy(BigDecimal empno);

    /**
     * 查询所有员工
     * @return 所有员工信息
     */
    List<Employ> queryAll();

    EmployDeptDTO queryEmployDeptMsg(String empno);

    /**
     * 添加或者修改接口
     * @param employ 员工信息
     * @return 返回修改结果
     */
    int saveOrUpdate(Employ employ);

    /**
     * 根据员工编号删除员工信息
     * @param empno 员工编号
     * @return 返回删除结果
     */
    int remove(String empno);


    /**
     * 分页查询员工信息
     * @param job 员工编号
     * @param pageable 分页对象
     * @return 返回分页结果
     */
    List<Employ> queryEmployInfo(String job, Pageable pageable);



    /**
     * 分页查询员工信息
     * @param jobs 员工编号
     * @param pageable 分页对象
     * @return 返回分页结果
     */
    Pageable<Employ> pqgeQueryEmployInfo(List<String> jobs, Pageable<Employ> pageable,String dbtype);

    /**
     * 测试多数据源事务是否可用
     * @param mysqlEmploy 员工信息
     * @param oracleEmploy 员工信息
     * @param isRollback 事务是否需要回滚
     * @return 返回影响记录行
     */
    int testMulityDTransactionService(Employ mysqlEmploy,Employ oracleEmploy,boolean isRollback);




}
