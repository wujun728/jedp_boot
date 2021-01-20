package cn.itcast.jpa;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestBean tb = new TestBean("abcd");
		Parser p = new Parser();

		p.parse(tb, "toString");
	}

}
