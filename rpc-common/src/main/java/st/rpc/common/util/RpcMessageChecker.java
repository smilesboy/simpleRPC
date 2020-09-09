package st.rpc.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import st.rpc.common.entity.RpcRequest;
import st.rpc.common.entity.RpcResponse;
import st.rpc.common.enumeration.ResponseCode;
import st.rpc.common.enumeration.RpcError;
import st.rpc.common.exception.RpcException;

/**
 * 检查响应与请求
 * @author sutian
 * @Date 2020/9/8
 */
public class RpcMessageChecker {

    private static final Logger logger = LoggerFactory.getLogger(RpcMessageChecker.class);

    private static final String INTERFACE_NAME = "interfaceName";

    private RpcMessageChecker() {}

    public static void check(RpcRequest rpcRequest, RpcResponse rpcResponse) {
        if (rpcResponse == null) {
            logger.error("调用服务失败,serviceName:{}", rpcRequest.getInterfaceName());
            throw new RpcException(RpcError.SERVICE_INVOCATION_FAILURE, INTERFACE_NAME + ":" + rpcRequest.getInterfaceName());
        }

        if (!rpcRequest.getRequestId().equals(rpcResponse.getRequestId())) {
            throw new RpcException(RpcError.RESPONSE_NOT_MATCH, INTERFACE_NAME + ":" + rpcRequest.getInterfaceName());
        }

        if (rpcResponse.getStatusCode() == null || !rpcResponse.getStatusCode().equals(ResponseCode.SUCCESS.getCode())) {
            logger.error("调用服务失败,serviceName:{},RpcResponse:{}", rpcRequest.getInterfaceName(), rpcResponse);
            throw new RpcException(RpcError.SERVICE_INVOCATION_FAILURE, INTERFACE_NAME + ":" + rpcRequest.getInterfaceName());
        }
    }

}
