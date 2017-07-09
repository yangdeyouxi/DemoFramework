package demoframework.yangmo.com.testioc.ioc.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yangjh on 2017/7/9.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnClickEvent(listenerType = View.OnClickListener.class,listenerSetter = "setOnClickListener",methodName = "onClick")
public @interface InjectOnClick {
    int[] value();
}