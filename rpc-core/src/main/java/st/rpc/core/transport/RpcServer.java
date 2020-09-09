package st.rpc.core.transport;

import st.rpc.core.serializer.CommonSerializer;

/**
 * 服务器类通用接口
 * @author sutian
 * @Date 2020/9/8
 */
public interface RpcServer {

    void start();

    void setSerializer(CommonSerializer serializer);

    <T> void publishService(T service, Class<T> serviceClass);

}
