package demoframework.yangmo.com.injectionlibrary.router;

/**
 * Created by yangjh on 2017/7/17.
 * 用于存储反馈信息，路由是成功了还是失败了，以及路由之后返回的一些信息的承载
 *
 */

public class RouterResponse {

    public static final int ROUTER_NOT_EXIT = -2;//路由的路径错误不存在
    public static final int ROUTER_FAIL = -1;//路由处理失败，被路由的一方返回的错误
    public static final int ROUTER_SUCCESS = 0;//路由成功并且处理成功

    public int code;//用于返回路由结果

    public String errorInfo;//错误信息

    public Object responseData;//路由的返回值

}
