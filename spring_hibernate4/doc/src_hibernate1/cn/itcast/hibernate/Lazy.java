package cn.itcast.hibernate;

public class Lazy {
	private static Object bigObj = null;
	private static Object lock = new Object();

	public static Object getInstance() {
		if (bigObj == null) {
			synchronized (lock) {
				if (bigObj == null)
					bigObj = new Object();
			}
		}
		return bigObj;
	}
}
