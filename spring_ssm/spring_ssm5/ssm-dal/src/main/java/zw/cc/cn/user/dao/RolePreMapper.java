package zw.cc.cn.user.dao;

import org.springframework.stereotype.Repository;
import zw.cc.cn.user.module.RolePre;

@Repository
public interface RolePreMapper {
    int insert(RolePre record);

    int insertSelective(RolePre record);
}