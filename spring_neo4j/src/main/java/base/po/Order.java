package base.po;


import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Order {
	@GraphId
	private Long id;
	private String customer;
	private String type;
	
//	@RelatedTo(type="has_item")
//	Set<Item> items=new HashSet<Item>();
	public Order() {
	}

	public Order( String customer, String type) {
		this.customer = customer;
		this.type = type;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Set<Item> getItems() {
//		return items;
//	}
//
//	public void setItems(Set<Item> items) {
//		this.items = items;
//	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", type=" + type
				+ "]";
	}

	
}
