package st.rpc.core.registry;

import java.net.InetSocketAddress;

/**
 * 服务注册中心通用接口
 * @author sutian
 * @Date 2020/9/7
 */
public interface ServiceRegistry {

    /**
     * 将一个服务注册进注册表
     * @param serviceName 服务名称
     * @param inetSocketAddress 服务实体类型
     */
     void register(String serviceName, InetSocketAddress inetSocketAddress);

    /**
     * 根据服务名称查找服务实体
     * @param serviceName
     * @return 服务实体
     */
    InetSocketAddress  lookupService(String serviceName);

}
