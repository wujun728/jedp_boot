package cn.edu.nuc.Store.entity;

import cn.edu.nuc.Store.model.OrderList;

public interface OrderListMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(OrderList record);

    int insertSelective(OrderList record);

    OrderList selectByPrimaryKey(Integer orderid);

    int updateByPrimaryKeySelective(OrderList record);

    int updateByPrimaryKey(OrderList record);
}