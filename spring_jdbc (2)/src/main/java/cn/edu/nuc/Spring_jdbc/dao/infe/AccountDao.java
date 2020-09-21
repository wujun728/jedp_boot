package cn.edu.nuc.Spring_jdbc.dao.infe;

public interface AccountDao {
	String getpass(String name);
	int transfer(String name,int monet);
}
