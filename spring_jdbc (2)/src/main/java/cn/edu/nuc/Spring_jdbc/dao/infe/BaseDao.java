package cn.edu.nuc.Spring_jdbc.dao.infe;

public interface BaseDao {

	<T> T findByName(Class<T> clazz, String name);
}
