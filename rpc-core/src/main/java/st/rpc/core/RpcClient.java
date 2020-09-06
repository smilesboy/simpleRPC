package st.rpc.core;

import st.rpc.common.entity.RpcRequest;

/**
 * 客户端类通用接口
 * @author sutian
 * @Date 2020/8/28
 */
public interface RpcClient {

    Object sendRequest(RpcRequest rpcRequest);

}
