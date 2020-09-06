package st.test.client;


import st.rpc.api.HelloObject;
import st.rpc.api.HelloService;
import st.rpc.core.RpcClient;
import st.rpc.core.RpcClientProxy;
import st.rpc.core.netty.client.NettyClient;

/**
 * 测试中的客户端
 * @author sutian
 * @Date 2020/9/5
 */
public class NettyTestClient {

    public static void main(String[] args) {
        RpcClient client = new NettyClient("127.0.0.1", 9000);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(13, "This is a message");
        String res  = helloService.hello(object);
        System.out.println(res);
    }
}
