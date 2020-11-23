package cn.kiwipeach.demo.mapper;


import cn.kiwipeach.demo.domain.Employ;
import cn.kiwipeach.demo.domain.dto.EmployDeptDTO;
import cn.kiwipeach.demo.framework.annotations.Datasource;
import cn.kiwipeach.demo.framework.response.Pageable;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * Create Date: 2017/10/31
 * Description: 员工操作接口
 *
 * @author Wujun
 */
@Datasource("oracleDatasource")
public interface EmployMapper {
    int deleteByPrimaryKey(BigDecimal empno);

    int insert(Employ record);

    int insertSelective(Employ record);

    Employ selectByPrimaryKey(BigDecimal empno);

    int updateByPrimaryKeySelective(Employ record);

    int updateByPrimaryKey(Employ record);

    List<Employ> selectByJobWithAnnotation(
            @Param("employ") Employ employ,
            @Param("pageNumKey") int pageNum,
            @Param("pageSizeKey") int pageSize
    );

    List<Employ> selectAll();

    /***
     * 查询员工部门信息
     * @param empno 员工编号
     * @return 返回员工部门信息
     */
    EmployDeptDTO selectEmpDeptInfo(String empno);

    /**
     * 分页情况1：单个属性
     * @param job 工作
     * @param pageable 分页对象参数
     * @return 分页对象结果
     */
    List<Employ> selectByJob1(@Param("job") String job, Pageable pageable);

    /**
     * 分页情况2：对象入参
     * @param employParam 对象入参
     * @param pageable 分页入参
     * @return 分页出参
     */
    List<Employ> selectByJob2(@Param("employ") Employ employParam, Pageable pageable);

    /**
     * 分页情况3：List<String>入参
     * @param jobIdList 对象入参
     * @param pageable 分页入参
     * @return 分页出参
     */
    List<Employ> selectByJob3(@Param("jobIdList") List<String> jobIdList, Pageable pageable);

    /**
     * 分页情况4：List<String>入参
     * @param jobIdList job的List类型入参
     * @param deptno 部门编号
     * @param pageable 分页入参
     * @return 分页出参
     */
    List<Employ> selectByJob4(@Param("jobIdList") List<String> jobIdList, @Param("deptno")String deptno, Pageable pageable);


    /**
     * 分页情况5：List<String>入参
     * @param jobIdList 对象入参
     * @param pageable 分页入参
     * @return 分页出参
     */
    @Datasource("oracleDataSource")
    List<Employ> selectByJob5(@Param("jobIdList") List<String> jobIdList, Pageable pageable);

    /**
     * 分页情况5：List<String>入参
     * @param jobIdList 对象入参
     * @param pageable 分页入参
     * @return 分页出参
     */
    @Datasource("mysqlDataSource")
    List<Employ> selectByJob6(@Param("jobIdList") List<String> jobIdList, Pageable pageable);


}