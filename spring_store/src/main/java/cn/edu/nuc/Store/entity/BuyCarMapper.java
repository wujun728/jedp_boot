package cn.edu.nuc.Store.entity;

import cn.edu.nuc.Store.model.BuyCar;

public interface BuyCarMapper {
    int deleteByPrimaryKey(Integer carid);

    int insert(BuyCar record);

    int insertSelective(BuyCar record);

    BuyCar selectByPrimaryKey(Integer carid);

    int updateByPrimaryKeySelective(BuyCar record);

    int updateByPrimaryKey(BuyCar record);
}