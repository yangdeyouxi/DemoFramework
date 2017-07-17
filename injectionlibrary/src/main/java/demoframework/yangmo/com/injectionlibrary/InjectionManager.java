package demoframework.yangmo.com.injectionlibrary;

import android.app.Activity;

import demoframework.yangmo.com.injectionlibrary.viewinjection.ViewInjectUtils;

/**
 * Created by yangjh on 2017/7/15.
 */

public class InjectionManager {

    private ViewInjectUtils mViewInjectUtils;

    /**
     * 注入View
     * @param activity
     */
    public void injectView(Activity activity){
        ViewInjectUtils.inject(activity);
    }

    /**
     * 注入需要被注入的值
     * @param object
     */
    public void injectClass(Object object){

    }






    private static volatile InjectionManager mInstance;
    private InjectionManager(){}

    public static InjectionManager getInstance(){
        if(null == mInstance){
            synchronized (InjectionManager.class) {
                if(null == mInstance) {
                    mInstance = new InjectionManager();
                }
            }
        }
        return mInstance;
    }
}
