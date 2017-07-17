package demoframework.yangmo.com.injectionlibrary.router.annotation;

import android.os.Bundle;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yangjh on 2017/7/16.
 * 用于页面跳转时带的参数
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntentExtres {

    String value();
}
