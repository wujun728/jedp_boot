package com.BBS.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface GeneralDao {

    //获取表记录合计
    int getCount(Map<String,String> map);
}
