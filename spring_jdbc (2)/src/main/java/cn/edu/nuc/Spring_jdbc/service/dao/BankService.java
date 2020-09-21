package cn.edu.nuc.Spring_jdbc.service.dao;

public interface BankService {
	/**
	 * 
	 * @param a
	 * @param a_pass
	 * @param b
	 * @param money
	 * @return
	 * @throws Exception
	 */
	boolean transfer(String a,String a_pass,String b, int money) throws Exception;
}
