package cn.kiwipeach.demo.framework.annotations;

import java.lang.annotation.*;

/**
 * 数据源类型注解,支持到类和方法级别
 *
 * @author kiwipeach
 * @create 2018/01/15
 **/
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Datasource {

    String value() default "";

}
