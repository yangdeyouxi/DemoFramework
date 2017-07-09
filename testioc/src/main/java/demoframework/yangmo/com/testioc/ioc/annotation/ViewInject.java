package demoframework.yangmo.com.testioc.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yangjh on 2017/7/9.
 */

@Target(ElementType.FIELD)//用于描述字段、枚举的常量
@Retention(RetentionPolicy.RUNTIME)//在运行时有效（即运行时保留）
public @interface ViewInject
{
    int value();
}
