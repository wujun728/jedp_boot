package cn.kiwipeach.service.front;

import cn.kiwipeach.modal.Employ;
import cn.kiwipeach.service.EmployService;

import java.math.BigDecimal;

/**
 * Create Date: 2017/10/31
 * Description: 员工前台Service服务接口
 *
 * @author kiwipeach [1099501218@qq.com]
 */
public abstract class EmployFrontService implements EmployService {
    /**
     * 查询员工信息
     * @param empno 员工编号
     * @return 员工信息
     */
    @Override
    public abstract Employ queryEmploy(BigDecimal empno);
}
