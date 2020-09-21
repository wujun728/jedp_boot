package cn.kiwipeach.service.front.impl;

import cn.kiwipeach.mapper.EmployMapper;
import cn.kiwipeach.modal.Employ;
import cn.kiwipeach.service.front.EmployFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Create Date: 2017/10/31
 * Description:EmployFrontService实现类
 *
 * @author kiwipeach [1099501218@qq.com]
 */
@Service
public class EmployFrontServiceImpl extends EmployFrontService {

    @Autowired
    private EmployMapper employMapper;

    @Override
    public Employ queryEmploy(BigDecimal empno) {
        return employMapper.selectByPrimaryKey(empno);
    }
}
