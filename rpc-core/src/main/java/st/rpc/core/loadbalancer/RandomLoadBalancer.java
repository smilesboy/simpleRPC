package st.rpc.core.loadbalancer;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;
import java.util.Random;


/**
 * 随机负载均衡算法
 * @author sutian
 * @Date 2020/9/7
 */
public class RandomLoadBalancer implements LoadBalancer {
    @Override
    public Instance select(String serviceName, List<Instance> instances) {
        return instances.get(new Random().nextInt(instances.size()));
    }
}
