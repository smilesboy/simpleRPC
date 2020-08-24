package st.test.client;


import st.rpc.api.HelloObject;
import st.rpc.api.HelloService;
import st.rpc.core.client.RpcClientProxy;

/**
 * 测试客户端
 * @author sutian
 * @Date 2020/8/23
 */


public class TestClient {

    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1", 9000);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12,"This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }

}
