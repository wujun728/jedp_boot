package cn.itcast.jpa;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NewHelloWord {
	public String value() default "hello";	
	
}
