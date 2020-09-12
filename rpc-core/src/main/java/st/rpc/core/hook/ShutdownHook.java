package st.rpc.core.hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import st.rpc.common.util.NacosUtil;
import st.rpc.common.factory.ThreadPoolFactory;

import java.util.concurrent.ExecutorService;

/**
 * 钩子函数，用于注销所有服务
 * @author sutian
 * @Date 2020/9/9
 */
public class ShutdownHook {

    private static final Logger logger = LoggerFactory.getLogger(ShutdownHook.class);

    private final ExecutorService threadPool = ThreadPoolFactory.createDefaultThreadPool("shutdown-hook");
    private static final ShutdownHook shutdownHook = new ShutdownHook();

    public static ShutdownHook getShutdownHook() {
        return shutdownHook;
    }

    public void addClearAllHook() {
        logger.info("关闭后将自动注销所有服务");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                NacosUtil.clearRegistry();
                threadPool.shutdown();
        }));
    }
}
