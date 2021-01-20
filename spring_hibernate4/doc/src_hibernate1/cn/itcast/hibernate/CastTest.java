package cn.itcast.hibernate;

import java.util.HashSet;
import java.util.Set;

public class CastTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set<String> ss = new HashSet<String>();
		ss.add("111");
		ss.add("222");

		HashSet<String> hs = (HashSet<String>) ss;
		System.out.println(hs);
	}

}
