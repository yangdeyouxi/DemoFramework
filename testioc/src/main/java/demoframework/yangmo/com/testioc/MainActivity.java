package demoframework.yangmo.com.testioc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import demoframework.yangmo.com.testioc.ioc.ViewInjectUtils;
import demoframework.yangmo.com.testioc.ioc.annotation.ContentView;
import demoframework.yangmo.com.testioc.ioc.annotation.ViewInject;

@ContentView(value = R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewInject(value = R.id.helloText)
    private TextView helloText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ViewInjectUtils.inject(this);
        helloText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        helloText.setText("i am clicked");
    }
}
