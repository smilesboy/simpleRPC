package st.test.server;

import st.rpc.api.HelloService;
import st.rpc.core.registry.DefaultServiceRegistry;
import st.rpc.core.registry.ServiceRegistry;
import st.rpc.core.server.RpcServer;

/**
 * 测试中的服务端（提供注册服务）
 * @author sutian
 * @Date 2020/8/19
 */

public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        rpcServer.start(9000);
    }
}
