package base.dao;

import org.springframework.data.neo4j.repository.GraphRepository;
import base.po.Line;

public interface LineRepository extends GraphRepository<Line>{

}
