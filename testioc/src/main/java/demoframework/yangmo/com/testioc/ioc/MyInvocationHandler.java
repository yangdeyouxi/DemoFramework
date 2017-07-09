package demoframework.yangmo.com.testioc.ioc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangjh on 2017/7/9.
 * 继承了动态代理的辅助类
 */
public class MyInvocationHandler implements InvocationHandler {
    private Object object;
    private Map<String, Method> methodMap = new HashMap<String, Method>(1);
    public MyInvocationHandler(Object object) {
        this.object = object;
    }
    public void setMethodMap(String name, Method method) {
        this.methodMap.put(name, method);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (object != null) {
            String name = method.getName();
            method= this.methodMap.get(name);
            if (method != null) {
                return method.invoke(object, args);
            }
        }
        return null;
    }
}
