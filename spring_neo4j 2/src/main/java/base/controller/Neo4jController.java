package base.controller;



import java.util.HashSet;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.neo4j.conversion.Result;

import base.pagemodel.MSG;
import base.po.Item;
import base.po.Order;
import base.service.impl.ItemService;
import base.service.impl.OrderService;


@Controller
public class Neo4jController {
	
	
	@Autowired
	OrderService orderservice;
	
	@Autowired
	ItemService itemservice;
	
	@RequestMapping(value="/create")
	@ResponseBody
	public MSG showpic(){
		//创建中心节点
		Order o=new Order("mark","VIP"); 
        orderservice.createOrder(o);
        //创建外围节点
        for(int i=0;i<10;i++){
        	Item item=new Item("book"+String.valueOf(i),(double)i);
        	itemservice.createItem(item);
        }
        Order order=orderservice.findByCustomer("mark").get(0);
        //加边
        	Result<Item> items = itemservice.getAllItems();
        	Iterator<Item> it=items.iterator();
        	while(it.hasNext()){
        		Item t=it.next();
        		itemservice.addLine(order, t, 5);
        	}
		return new MSG("success");
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public MSG a(){
		itemservice.deleteall();
		orderservice.deleteall();
		return new MSG("success");
	}
	
}
