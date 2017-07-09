package demoframework.yangmo.com.testioc.ioc;

import android.app.Activity;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import demoframework.yangmo.com.testioc.ioc.annotation.ContentView;
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


    private static final String METHOD_SET_CONTENTVIEW = "setContentView";
    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";
}
