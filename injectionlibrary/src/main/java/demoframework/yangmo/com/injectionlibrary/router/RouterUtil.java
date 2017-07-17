package demoframework.yangmo.com.injectionlibrary.router;

import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by yangjh on 2017/7/15.
 * 这个对象是路由的主体,主要用来页面跳转
 */
public class RouterUtil {

    public static void startActivity(String activityPath, Bundle data){

        Intent intent = new Intent();
        intent.putExtras(data);

    }


}
