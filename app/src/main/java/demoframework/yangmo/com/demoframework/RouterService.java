package demoframework.yangmo.com.demoframework;

import demoframework.yangmo.com.injectionlibrary.router.annotation.Destination;
import demoframework.yangmo.com.injectionlibrary.router.annotation.IntentExtres;

/**
 * Created by yangjh on 2017/7/17.
 */

public interface RouterService {

    @Destination(activityName = "demoframework.yangmo.com.demoframework.SecondActivity")
    void startTiocMainActivity(@IntentExtres(value = "intentData") int data,@IntentExtres(value = "stringData") String datas);
}
