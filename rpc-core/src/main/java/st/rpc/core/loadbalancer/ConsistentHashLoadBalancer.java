package st.rpc.core.loadbalancer;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConsistentHashLoadBalancer implements LoadBalancer {

    private final ConcurrentMap<String, ConsistentHashSelector> selectors =
            new ConcurrentHashMap<String, ConsistentHashSelector>();

    @Override
    public Instance select(String serviceName, List<Instance> instances) {

        int identityHashCode = System.identityHashCode(instances);
        ConsistentHashSelector selector = (ConsistentHashSelector) selectors.get(serviceName);
        if (selector == null || selector.identityHashCode != identityHashCode) {
            selectors.put(serviceName, new ConsistentHashSelector(serviceName, instances, identityHashCode));
            selector = (ConsistentHashSelector) selectors.get(serviceName);
        }

        return selector.selectInvoke(serviceName);
    }

    private static final class ConsistentHashSelector {

        private final TreeMap<Integer, Instance> virtualInvokers;

        //虚拟节点的数目，这里写死，一个真实结点对应5个虚拟节点
        private static final int VIRTUAL_NODES = 5;

        private final int identityHashCode;

        ConsistentHashSelector(String serviceName, List<Instance> instances, int identityHashCode) {
            this.virtualInvokers = new TreeMap<>();
            this.identityHashCode = identityHashCode;

            for (Instance instance : instances) {
                String address = instance.getIp();
                for (int i = 0; i < VIRTUAL_NODES; i++) {
                    int hash = getHash(address + "&&VN" + String.valueOf(i));
                    virtualInvokers.put(hash, instance);
                }
            }
        }

        private Instance selectInvoke(String serviceName) {
            int hash = getHash(serviceName);

            Map.Entry<Integer, Instance> entry = virtualInvokers.tailMap(hash, true).firstEntry();

            if (entry == null) {
                entry = virtualInvokers.firstEntry();
            }

            return entry.getValue();
        }

        //使用FNV1_32_HASH算法计算服务器的Hash值
        private static int getHash(String str){
            final int p = 16777619;
            int hash = (int)2166136261L;
            for (int i = 0; i < str.length(); i++)
                hash = (hash ^ str.charAt(i)) * p;
            hash += hash << 13;
            hash ^= hash >> 7;
            hash += hash << 3;
            hash ^= hash >> 17;
            hash += hash << 5;

            // 如果算出来的值为负数则取其绝对值
            if (hash < 0)
                hash = Math.abs(hash);
            return hash;
        }


    }
}
