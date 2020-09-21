package cn.edu.nuc.Spring_jdbc.dao.infe;

import java.util.List;

import cn.edu.nuc.Spring_jdbc.model.Person;

public interface PersonDao extends BaseDao{
	
	<T> T findByName(Class<T> clazz, String name);
	
	Person find(Person person);

	List<Person> findlist(Person person);
	
	int insert(Person person);

	int delete(Person person);

	int update(Person person);
}
