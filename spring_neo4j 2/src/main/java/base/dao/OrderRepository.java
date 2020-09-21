package base.dao;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;

import base.po.Order;

public interface OrderRepository extends GraphRepository<Order>{
	List<Order> findByCustomer(String customer);
	List<Order> findByCustomerAndType(String customer,String type);
}
