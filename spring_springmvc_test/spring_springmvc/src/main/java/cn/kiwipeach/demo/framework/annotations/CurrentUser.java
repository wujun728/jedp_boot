package cn.kiwipeach.demo.framework.annotations;

import java.lang.annotation.*;

/**
 * ${DESCRIPTION}
 *
 * @author Wujun
 * @create 2018/01/18
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
    /**
     * 当前用户在request中的名字
     *
     * @return
     */
    String value() default "loginUser";
}
