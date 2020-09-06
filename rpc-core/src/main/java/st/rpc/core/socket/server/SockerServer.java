package st.rpc.core.socket.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import st.rpc.core.RpcServer;
import st.rpc.core.registry.ServiceRegistry;
import st.rpc.core.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;


/**
 * Socket方式远程方法调用的提供者（服务端）
 * @author sutian
 * @Date 2020/8/29
 */
public class SockerServer implements RpcServer {

    private static final Logger logger = LoggerFactory.getLogger(SockerServer.class);

    private static final int corePoolSize = 5;
    private static final int maximumPoolSize = 50;
    private static final long keepAliveTime = 60;
    private static final int BLOCKING_QUEUE_CAPACITY = 100;
    private final ExecutorService threadPool;
    private RequestHandler requestHandler = new RequestHandler();
    private final ServiceRegistry serviceRegistry;

    public SockerServer(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_CAPACITY);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workingQueue, threadFactory);
    }

    @Override
    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("服务器正在启动...");
            Socket socket;
            while((socket = serverSocket.accept()) != null) {
                logger.info("客户端连接: {}:{}" + socket.getInetAddress(), socket.getPort());
                threadPool.execute(new RequestHandlerThread(socket, requestHandler, serviceRegistry));
            }
            threadPool.shutdown();
        } catch (IOException e) {
            logger.error("连接时有错误发生：", e);
        }
    }

}
