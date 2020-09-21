package base.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;
import org.springframework.data.neo4j.support.Neo4jTemplate;


@Configuration
@EnableNeo4jRepositories(basePackages="base.dao")
public class Neo4jConfig extends Neo4jConfiguration{
	public Neo4jConfig(){
		setBasePackage("base");
	}
	
//	@Bean
//	public Neo4jTemplate neo4jTemplate(){
//		return new Neo4jTemplate(graphDatabaseService()); 
//	}
	
	@Bean(destroyMethod="shutdown")
	public GraphDatabaseService graphDatabaseService(){
		return new SpringRestGraphDatabase("http://localhost:7474/db/data", "neo4j", "1234");
	}
	
}
