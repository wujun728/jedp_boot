package cn.kiwipeach.service;

import cn.kiwipeach.modal.Employ;

import java.math.BigDecimal;

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

}
