package st.rpc.core.transport;

import st.rpc.common.entity.RpcRequest;
import st.rpc.core.serializer.CommonSerializer;

/**
 * 客户端类通用接口
 * @author sutian
 * @Date 2020/9/8
 */
public interface RpcClient {

    Object sendRequest(RpcRequest rpcRequest);

    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;

}
