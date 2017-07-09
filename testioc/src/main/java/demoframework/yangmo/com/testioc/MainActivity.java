package demoframework.yangmo.com.testioc;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Method;

import demoframework.yangmo.com.testioc.ioc.ViewInjectUtils;
import demoframework.yangmo.com.testioc.ioc.annotation.ContentView;
import demoframework.yangmo.com.testioc.ioc.annotation.ViewInject;

//@ContentView(value = R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    @ViewInject(value = R.id.helloText)
    private TextView helloText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ViewInjectUtils.inject(this);
//        helloText.setOnClickListener(this);


            try
            {
                Class playgame = Class.forName("demoframework.yangmo.com.demoframework.PlayGame");//获取Activity的类
                Method method = playgame.getMethod("saveLevel",
                        int.class);//通过反射获取方法
                Object playgameOne = playgame.newInstance();
                method.setAccessible(true);//setAccessible(true)可以访问private域,并且可以提升反射速度
                Object result = method.invoke(playgameOne, 5);//调用activity对象的METHOD_SET_CONTENTVIEW方法，传入的参数为contentViewLayoutId
                Log.d("demo","result:" + result);
            } catch (Exception e)
            {
                e.printStackTrace();

            }


    }

    @Override
    public void onClick(View view) {
        helloText.setText("i am clicked");
    }
}
