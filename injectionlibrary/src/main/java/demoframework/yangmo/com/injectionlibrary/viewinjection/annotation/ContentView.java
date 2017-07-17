package demoframework.yangmo.com.injectionlibrary.viewinjection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yangjh on 2017/7/9.
 */

@Target(ElementType.TYPE)//用于描述类、接口(包括注解类型) 或enum声明
@Retention(RetentionPolicy.RUNTIME)//在运行时有效（即运行时保留）
public @interface ContentView {

    public String getClassName() default "className";

    int value();

}
