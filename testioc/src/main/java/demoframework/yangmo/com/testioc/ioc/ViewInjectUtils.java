package demoframework.yangmo.com.testioc.ioc;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

import demoframework.yangmo.com.testioc.ioc.annotation.ContentView;
import demoframework.yangmo.com.testioc.ioc.annotation.OnClickEvent;
import demoframework.yangmo.com.testioc.ioc.annotation.ViewInject;

/**
 * Created by yangjh on 2017/7/9.
 */

public class ViewInjectUtils {


    public static void inject(Activity activity){
        injectContentView(activity);
        injectViews(activity);
    }

    /**
     * 注入主布局文件
     *
     * @param activity
     */
    private static void injectContentView(Activity activity)
    {
        Class<? extends Activity> clazz = activity.getClass();//获取Activity的类
        // 查询类上是否存在ContentView注解
        ContentView contentView = clazz.getAnnotation(ContentView.class);//获取注解的类的实例化对象
        if (contentView != null)// 存在
        {
            int contentViewLayoutId = contentView.value();//获取赋予的值
            try
            {
                Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW,
                        int.class);//通过反射获取方法
                method.setAccessible(true);//setAccessible(true)可以访问private域,并且可以提升反射速度
                method.invoke(activity, contentViewLayoutId);//调用activity对象的METHOD_SET_CONTENTVIEW方法，传入的参数为contentViewLayoutId
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 注入所有的控件
     *
     * @param activity
     */
    private static void injectViews(Activity activity)
    {
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        // 遍历所有成员变量
        for (Field field : fields)
        {//获取所有成员变量

            ViewInject viewInjectAnnotation = field
                    .getAnnotation(ViewInject.class);
            if (viewInjectAnnotation != null)
            {
                int viewId = viewInjectAnnotation.value();
                if (viewId != -1)
                {
                    Log.e("TAG", viewId+"");
                    // 初始化View
                    try
                    {
                        Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID,
                                int.class);
                        Object resView = method.invoke(activity, viewId);//执行findViewById方法得到View
                        field.setAccessible(true);
                        field.set(activity, resView);//设置变量
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            }

        }

    }

    /**
     * 注入点击事件
     * @param activity
     */
    public static void injectOnClickListener(Activity activity){
        Class<? extends Activity> clazz = activity.getClass();
        Method[] methods= clazz.getMethods();//获取所有声明为公有的方法
        if(null == methods || methods.length <= 0)return;
        for (Method method:methods){//遍历所有公有方法
            Annotation[] annotations = method.getAnnotations();//获取该公有方法的所有注解
            if(null == annotations || annotations.length <= 0)return;
            for (Annotation annotation:annotations){//遍历所有注解
                Class<? extends Annotation> annotationType = annotation.annotationType();//获取具体的注解类
                OnClickEvent onClickEvent = annotationType.getAnnotation(OnClickEvent.class);//取出注解的onClickEvent注解
                if(onClickEvent!=null){//如果不为空
                    try {
                        Method valueMethod=annotationType.getDeclaredMethod("value");//获取注解InjectOnClick的value方法
                        int[] viewIds= (int[]) valueMethod.invoke(annotation,null);//获取控件值
                        Class<?> listenerType = onClickEvent.listenerType();//获取接口类型
                        String listenerSetter = onClickEvent.listenerSetter();//获取set方法
                        String methodName = onClickEvent.methodName();//获取接口需要实现的方法
                        MyInvocationHandler handler=new MyInvocationHandler(activity);//自己实现的代码，负责调用
                        handler.setMethodMap(methodName,method);//设置方法及设置方法
                        Object object= Proxy.newProxyInstance(listenerType.getClassLoader(),new Class<?>[]{listenerType},handler);//创建动态代理对象类
                        for (int viewId:viewIds){//遍历要设置监听的控件
                            View view=activity.findViewById(viewId);//获取该控件
                            Method m=view.getClass().getMethod(listenerSetter, listenerType);//获取方法
                            m.invoke(view,object);//调用方法
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    private static final String METHOD_SET_CONTENTVIEW = "setContentView";
    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";

}



