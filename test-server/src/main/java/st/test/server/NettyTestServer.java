package st.test.server;

import st.rpc.api.HelloService;
import st.rpc.core.netty.server.NettyServer;
import st.rpc.core.registry.DefaultServiceRegistry;
import st.rpc.core.registry.ServiceRegistry;
import st.rpc.core.RpcServer;

/**
 * 测试中的服务端（提供注册服务）
 * @author sutian
 * @Date 2020/9/5
 */

public class NettyTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        NettyServer rpcServer = new NettyServer();
        rpcServer.start(9000);
    }
}
