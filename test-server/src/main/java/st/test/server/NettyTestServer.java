package st.test.server;

import st.rpc.api.HelloService;
import st.rpc.core.serializer.ProtostuffSerializer;
import st.rpc.core.transport.netty.server.NettyServer;

/**
 * 测试中的服务端（提供注册服务）
 * @author sutian
 * @Date 2020/9/5
 */

public class NettyTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        NettyServer server = new NettyServer("127.0.0.1", 9999);
        server.setSerializer(new ProtostuffSerializer());
        server.publishService(helloService, HelloService.class);
        server.start();
    }
}
