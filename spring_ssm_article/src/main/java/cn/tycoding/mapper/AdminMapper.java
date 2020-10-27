package cn.tycoding.mapper;

import cn.tycoding.pojo.Admin;

public interface AdminMapper {
    
    Admin login(String a_name);

    void insert(Admin admin);

    Admin findByName(String a_name);
}
