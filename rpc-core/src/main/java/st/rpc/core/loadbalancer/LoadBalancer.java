package st.rpc.core.loadbalancer;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

/**
 * 负载均衡接口
 * @author sutian
 * @Date 2020/9/10
 */
public interface LoadBalancer {

    Instance select(String serviceName, List<Instance> instances);

}
