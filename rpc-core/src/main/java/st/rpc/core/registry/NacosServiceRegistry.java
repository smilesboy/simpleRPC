package st.rpc.core.registry;

import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import st.rpc.common.enumeration.RpcError;
import st.rpc.common.exception.RpcException;
import st.rpc.common.util.NacosUtil;

import java.net.InetSocketAddress;


/**
 * Nacos服务注册中心
 * @author sutian
 * @Date 2020/9/7
 */
public class NacosServiceRegistry implements ServiceRegistry {

    private static final Logger logger = LoggerFactory.getLogger(NacosServiceRegistry.class);

    @Override
    public void register(String serviceName, InetSocketAddress inetSocketAddress) {
        try {
            NacosUtil.registerService(serviceName, inetSocketAddress);
        } catch (NacosException e) {
            logger.error("注册服务时有错误发生", e);
            throw new RpcException(RpcError.REGISTER_SERVICE_FAILED);
        }
    }


}
