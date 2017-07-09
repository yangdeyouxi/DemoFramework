package demoframework.yangmo.com.testioc.TestInherited;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yangjh on 2017/7/9.
 */

@ParentInterface
public class ChildClass {

    public String isChild(){
        return "child";
    }

    public void test1(){
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                return null;
            }
        };


    }

}
