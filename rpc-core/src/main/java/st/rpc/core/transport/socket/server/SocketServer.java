package st.rpc.core.transport.socket.server;

import st.rpc.common.factory.ThreadPoolFactory;
import st.rpc.core.handler.RequestHandler;
import st.rpc.core.hook.ShutdownHook;
import st.rpc.core.provider.ServiceProviderImpl;
import st.rpc.core.registry.NacosServiceRegistry;
import st.rpc.core.serializer.CommonSerializer;
import st.rpc.core.transport.AbstractRpcServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import static st.rpc.core.serializer.CommonSerializer.DEFAULT_SERIALIZER;


/**
 * Socket方式远程方法调用的提供者（服务端）
 * @author sutian
 * @Date 2020/8/29
 */
public class SocketServer extends AbstractRpcServer {

    private final ExecutorService threadPool;;
    private CommonSerializer serializer;
    private RequestHandler requestHandler = new RequestHandler();

    public SocketServer(String host, int port) {
        this(host, port, DEFAULT_SERIALIZER);
    }

    public SocketServer(String host, int port, Integer serializer) {
        this.host = host;
        this.port = port;
        threadPool = ThreadPoolFactory.createDefaultThreadPool("socket-rpc-server");
        this.serviceRegistry = new NacosServiceRegistry();
        this.serviceProvider = new ServiceProviderImpl();
        this.serializer = CommonSerializer.getByCode(serializer);
        scanServices();
    }


    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("服务器启动……");
            ShutdownHook.getShutdownHook().addClearAllHook();  // 关闭服务器时自动注销服务的钩子函数
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                logger.info("消费者连接: {}:{}", socket.getInetAddress(), socket.getPort());
                threadPool.execute(new RequestHandlerThread(socket, requestHandler,  serializer));
            }
            threadPool.shutdown();
        } catch (IOException e) {
            logger.error("服务器启动时有错误发生:", e);
        }
    }

    @Override
    public void setSerializer(CommonSerializer serializer) {
        this.serializer = serializer;
    }

}
