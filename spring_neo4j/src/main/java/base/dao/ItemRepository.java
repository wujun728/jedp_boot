package base.dao;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;
import base.po.Item;

public interface ItemRepository extends GraphRepository<Item>{
	List<Item> findByProduct(String product);
}
