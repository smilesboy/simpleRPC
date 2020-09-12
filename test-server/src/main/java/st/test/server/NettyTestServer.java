package st.test.server;

import st.rpc.core.annotation.ServiceScan;
import st.rpc.core.serializer.CommonSerializer;
import st.rpc.core.transport.netty.server.NettyServer;

/**
 * 测试中的服务端（提供注册服务）
 * @author sutian
 * @Date 2020/9/5
 */
@ServiceScan
public class NettyTestServer {
    public static void main(String[] args) {
        NettyServer server = new NettyServer("127.0.0.1", 9999, CommonSerializer.DEFAULT_SERIALIZER);
        server.start();
    }
}
