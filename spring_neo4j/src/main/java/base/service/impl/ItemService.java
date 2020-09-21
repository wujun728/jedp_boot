package base.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.dao.ItemRepository;
import base.dao.LineRepository;
import base.po.Item;
import base.po.Line;
import base.po.Order;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	LineRepository linerepository;
	
	public long getNumberOfItems() {
		return itemRepository.count();
	}
	
	public Item createItem(Item i) {
		return itemRepository.save(i);
	}
	
	public Result<Item> getAllItems() {
		return itemRepository.findAll();
	}
	
	public Item findItemById(Long id) {
		return itemRepository.findOne(id);
	}


	public void deleteall(){
		itemRepository.deleteAll();
	}
	
	public void addLine(Order order,Item item,int num){
		Line line=new Line();
		line.setItem(item);
		line.setOrder(order);
		line.setNumber(num);
		linerepository.save(line);
	}
	
}
