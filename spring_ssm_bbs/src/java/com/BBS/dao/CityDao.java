package com.BBS.dao;

import org.springframework.stereotype.Repository;

/**
 * Created by dyl on 2018/7/19.
 */
@Repository
public interface CityDao {
    String queryById(String id);
}
