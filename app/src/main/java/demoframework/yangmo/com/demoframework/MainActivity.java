package demoframework.yangmo.com.demoframework;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;
import android.widget.TextView;

import demoframework.yangmo.com.injectionlibrary.InjectionManager;
import demoframework.yangmo.com.injectionlibrary.RouterManager;
import demoframework.yangmo.com.injectionlibrary.router.RouterUtil;
import demoframework.yangmo.com.injectionlibrary.viewinjection.annotation.ContentView;
import demoframework.yangmo.com.injectionlibrary.viewinjection.annotation.InjectOnClick;
import demoframework.yangmo.com.injectionlibrary.viewinjection.annotation.ViewInject;

@ContentView(value = R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @ViewInject(value =R.id.text)
    TextView text;

    RouterService mRouterService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectionManager.getInstance().injectView(this);

        RouterManager.getInstance().initContext(this);
        mRouterService = RouterManager.getInstance().createJumpService(RouterService.class);
    }

    @InjectOnClick(value = R.id.text)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text:
//                Intent intent = new Intent("testIntent");
//                ComponentName componentName = new ComponentName(MainActivity.this,"testIntent");
//                intent.setComponent(componentName);
//                startActivity(intent);

                mRouterService.startTiocMainActivity(5,"www");


//                Intent app = new Intent(getApplicationContext(), demoframework.yangmo.com.testioc.MainActivity.class);
//                app.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                getApplicationContext().startActivity(app);
                break;

//            RouterUtil.startActivity("aaaaa",5);

        }
    }
}
