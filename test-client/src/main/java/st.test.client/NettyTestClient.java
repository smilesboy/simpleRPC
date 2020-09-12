package st.test.client;


import st.rpc.api.HelloObject;
import st.rpc.api.HelloService;
import st.rpc.core.serializer.CommonSerializer;
import st.rpc.core.transport.RpcClient;
import st.rpc.core.transport.RpcClientProxy;
import st.rpc.core.transport.netty.client.NettyClient;

/**
 * 测试中的客户端
 * @author sutian
 * @Date 2020/9/5
 */
public class NettyTestClient {

    public static void main(String[] args) {
        RpcClient client = new NettyClient(CommonSerializer.DEFAULT_SERIALIZER);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(13, "This is a message");
        String res  = helloService.hello(object);
        System.out.println(res);
    }
}
