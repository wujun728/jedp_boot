package base.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.dao.OrderRepository;
import base.po.Order;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public long getNumberOfOrders() {
		return orderRepository.count();
	}
	
	public Order createOrder(Order o) {
		return orderRepository.save(o);
	}
	
	public Iterable<Order> getAllOrders() {
		return orderRepository.findAll();
	}
	
	public Order findOrderById(Long id) {
		return orderRepository.findOne(id);
	}

	public void deleteall(){
		orderRepository.deleteAll();
	}
	
	public List<Order> findByCustomer(String customer){
		return orderRepository.findByCustomer(customer);
	}
	
}
