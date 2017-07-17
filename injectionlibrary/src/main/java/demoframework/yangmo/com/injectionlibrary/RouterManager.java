package demoframework.yangmo.com.injectionlibrary;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demoframework.yangmo.com.injectionlibrary.router.RouterResponse;
import demoframework.yangmo.com.injectionlibrary.router.annotation.Destination;
import demoframework.yangmo.com.injectionlibrary.router.annotation.IntentExtres;

/**
 * Created by yangjh on 2017/7/17.
 * 用于路由的入口类
 */

public class RouterManager {


    /**
     * 创建一个用于执行页面跳转的服务
     * @param service
     * @param <T>
     * @return
     */
    public <T> T createJumpService(final Class<T> service){
        if(null == mContext){
            return null;
        }
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
            //调用动态代理的方法时执行
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                RouterResponse response = new RouterResponse();


                Destination destination = method.getAnnotation(Destination.class);

                if(null == destination){
                    throw new IllegalArgumentException("Destination Value Can't be Null");
                }
                String activityName = destination.activityName();
//                Annotation[] parameterAnnotations = method.getDeclaredAnnotations();//方法中的参数
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();//方法中的参数
                HashMap<String, Object> serializedParams = new HashMap<>();//需要传递的数据的键值对

                for (int i = 0; i < parameterAnnotations.length; i++) {
                    Annotation[] annotations = parameterAnnotations[i];
                    if (annotations == null || annotations.length == 0)
                        break;

                    Annotation annotation = annotations[0];
                    if (annotation instanceof IntentExtres) {
                        IntentExtres uriParam = (IntentExtres) annotation;
                        serializedParams.put(uriParam.value(),objects[i]);
                    }
                }

                //执行Activity跳转操作
                performJump(activityName, serializedParams);
                return null;
            }
        });
    }


    /**
     * 执行Activity跳转操作
     *
     * @param activityName Intent跳转URI
     */
    private void performJump(String activityName, HashMap<String, Object> serializedParams) {
        if(mContext == null)return;

        Class activityClass = null;
        try {
            activityClass = Class.forName(activityName);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return;
        }
        Intent intent = new Intent(mContext, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Bundle bundle = new Bundle();
        for (Map.Entry<String, Object> entry : serializedParams.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Integer) {
                bundle.putInt(key, Integer.parseInt(value.toString()));
            } else if (value instanceof Long) {
                bundle.putLong(key, Long.parseLong(value.toString()));
            } else if (value instanceof Double) {
                bundle.putDouble(key, Double.parseDouble(value.toString()));
            } else if (value instanceof Short) {
                bundle.putShort(key, Short.parseShort(value.toString()));
            } else if (value instanceof Float) {
                bundle.putFloat(key, Float.parseFloat(value.toString()));
            } else if (value instanceof String) {
                bundle.putString(key, (String) value);
            } else if (value instanceof CharSequence) {
                bundle.putCharSequence(key, (CharSequence) value);
            } else if (value.getClass().isArray()) {
                if (int[].class.isInstance(value)) {
                    bundle.putIntArray(key, (int[]) value);
                } else if (long[].class.isInstance(value)) {
                    bundle.putLongArray(key, (long[]) value);
                } else if (double[].class.isInstance(value)) {
                    bundle.putDoubleArray(key, (double[]) value);
                } else if (short[].class.isInstance(value)) {
                    bundle.putShortArray(key, (short[]) value);
                } else if (float[].class.isInstance(value)) {
                    bundle.putFloatArray(key, (float[]) value);
                } else if (String[].class.isInstance(value)) {
                    bundle.putStringArray(key, (String[]) value);
                } else if (CharSequence[].class.isInstance(value)) {
                    bundle.putCharSequenceArray(key, (CharSequence[]) value);
                } else if (Parcelable[].class.isInstance(value)) {
                    bundle.putParcelableArray(key, (Parcelable[]) value);
                }
            } else if (value instanceof ArrayList && !((ArrayList) value).isEmpty()) {
                ArrayList list = (ArrayList) value;
                if (list.get(0) instanceof Integer) {
                    bundle.putIntegerArrayList(key, (ArrayList<Integer>) value);
                } else if (list.get(0) instanceof String) {
                    bundle.putStringArrayList(key, (ArrayList<String>) value);
                } else if (list.get(0) instanceof CharSequence) {
                    bundle.putCharSequenceArrayList(key, (ArrayList<CharSequence>) value);
                } else if (list.get(0) instanceof Parcelable) {
                    bundle.putParcelableArrayList(key, (ArrayList<? extends Parcelable>) value);
                }
            } else if (value instanceof Parcelable) {
                bundle.putParcelable(key, (Parcelable) value);
            } else if (value instanceof Serializable) {
                bundle.putSerializable(key, (Serializable) value);
            } else {
                throw new IllegalArgumentException("不支持的参数类型！");
            }
        }
        intent.putExtras(bundle);
        PackageManager packageManager = mContext.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        if (!activities.isEmpty()) {
            mContext.startActivity(intent);
        }else{
            throw new IllegalArgumentException("activity未定义！");
        }
    }


    public void initContext(Context context){
        if(null != mContext || null == context)return;
        mContext = context.getApplicationContext();
    }

    private Context mContext;
    private static volatile RouterManager mInstance;
    private RouterManager(){}

    public static RouterManager getInstance(){
        if(null == mInstance){
            synchronized (RouterManager.class) {
                if(null == mInstance) {
                    mInstance = new RouterManager();
                }
            }
        }
        return mInstance;
    }
}
