package st.rpc.core.registry;

import java.net.InetSocketAddress;

/**
 * 服务发现接口
 * @author sutian
 * @Date 2020/9/9
 */
public interface ServiceDiscovery {
    /**
     * 根据服务名称查找服务实体
     * @param serviceName
     * @return 服务实体
     */
    InetSocketAddress lookupService(String serviceName);
}
