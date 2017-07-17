package demoframework.yangmo.com.injectionlibrary.viewinjection;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangjh on 2017/7/9.
 * 继承了动态代理的辅助类
 */
public class MyInvocationHandler implements InvocationHandler {
    private Map<String, Method> methodMap = new HashMap<String, Method>(1);
    private WeakReference<Object> handlerRef;


    public MyInvocationHandler(Object object) {
        this.handlerRef = new WeakReference<Object>(object);
    }

    public void setMethodMap(String name, Method method) {
        this.methodMap.put(name, method);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object object = handlerRef.get();
        if (object != null) {
            String name = method.getName();
            method= this.methodMap.get(name);
            if (method != null) {
                try {
                    return method.invoke(object, args);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
