package cn.itcast.jpa;

import java.lang.reflect.Method;

public class Parser {
	public void parse(Object obj, String methodName) {
		Method[] ms = obj.getClass().getMethods();
		for (Method m : ms) {
			if (m.getName().equals(methodName)) {
				if (m.isAnnotationPresent(NewHelloWord.class)) {
					NewHelloWord hw = m.getAnnotation(NewHelloWord.class);
					// System.out.println(hw.value());
					try {
						System.out.println(hw.value() + " before...");
						m.invoke(obj, new Object[] {});
						System.out.println(hw.value() + " after...");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
