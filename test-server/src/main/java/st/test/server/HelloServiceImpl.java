package st.test.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import st.rpc.api.HelloObject;
import st.rpc.api.HelloService;
import st.rpc.core.annotation.Service;

/**
 * @author sutian
 * @Date 2020/8/19
 */
@Service
public class HelloServiceImpl implements HelloService {

    private  static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(HelloObject object) {
        logger.info("接收到：{}", object.getMessage());
        return "本次服务来自Netty，这是调用的返回值,id=" + object.getId();
    }
}
