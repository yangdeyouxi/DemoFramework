package demoframework.yangmo.com.injectionlibrary.router.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yangjh on 2017/7/16.
 * 用于指定跳转页面Activity的名字
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Destination {

    String activityName()default "";

}
