package st.test.server;

import st.rpc.api.HelloService;
import st.rpc.core.server.RpcServer;

/**
 * 测试中的服务端（提供注册服务）
 * @author sutian
 * @Date 2020/8/19
 */

public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(helloService, 9000);
    }
}
