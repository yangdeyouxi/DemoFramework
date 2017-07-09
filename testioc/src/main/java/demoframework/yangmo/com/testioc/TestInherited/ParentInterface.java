package demoframework.yangmo.com.testioc.TestInherited;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by yangjh on 2017/7/9.
 */

@Retention(RetentionPolicy.SOURCE)
@Inherited
public @interface ParentInterface {

    public String getDefaultName()default "defaultName";

    public int getDefaultAge()default 25;

    public int getDefaultHeight()default  170;

}
